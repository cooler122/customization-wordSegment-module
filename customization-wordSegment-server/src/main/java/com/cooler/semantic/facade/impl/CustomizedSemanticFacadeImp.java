package com.cooler.semantic.facade.impl;

import com.alibaba.fastjson.JSON;
import com.cooler.semantic.entity.CustomizedWord;
import com.cooler.semantic.entity.WordRestriction;
import com.cooler.semantic.facade.CustomizedSemanticFacade;
import com.cooler.semantic.model.SemanticInfo;
import com.cooler.semantic.model.SentenceVector;
import com.cooler.semantic.model.Term;
import com.cooler.semantic.model.WordRestrictionParam;
import com.cooler.semantic.service.external.BasisSemanticService;
import com.cooler.semantic.service.external.WordService;
import com.cooler.semantic.enumeration.Channel;
import com.cooler.semantic.enumeration.Pattern;
import com.cooler.semantic.service.internal.CustomizedWordService;
import com.cooler.semantic.service.internal.WordRestrictionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 基础语义接口实现
 */
@Slf4j
@Component("customizedSemanticFacade")
public class CustomizedSemanticFacadeImp implements CustomizedSemanticFacade {
    private static Logger logger = LoggerFactory.getLogger(CustomizedSemanticFacadeImp.class.getName());

    @Autowired
    private BasisSemanticService basisSemanticService;
    @Autowired
    private WordService wordService;
    @Autowired
    private CustomizedWordService customizedWordService;
    @Autowired
    private WordRestrictionService wordRestrictionService;

    private static Map<Integer, Map<String, CustomizedWord>> globalCustomizedWordMap = new HashMap<>();         //TODO:(以后要用redis代替）存放用各个用户自定义的词语Map<accountId, Map<word, customizedWord>>

    private static Map<Integer, List<WordRestrictionParam>> globalWordRestrictionParamMap = new HashMap<>();    //TODO:(以后要用redis代替）存放用各个用户自定义的词语Map<wordId, List<WordRestrictionParam>>

    @Override
    public SemanticInfo semanticParse(String text, List<Pattern> patterns) {
        return this.semanticParse(text, patterns, Channel.SYS, null);
    }

    @Override
    public SemanticInfo semanticParse(String text, List<Pattern> patterns, Channel channel, String name) {
        try {
            long t1 = System.currentTimeMillis();
            SemanticInfo semantic = basisSemanticService.parseSemantic(text, patterns, channel, name);
            long t2 = System.currentTimeMillis();
            logger.info(String.format("[%d ms]请求text：%s | patterns：%s | channel：%d | dictname：%s | data：%s",t2 - t1, text, this.patterns2Str(patterns), channel.getIndex(), name, printSemantic(semantic)));
            return semantic;
        } catch (Exception e) {
            logger.error(String.format("[error]请求text：%s | error：%s", text, e.getMessage()));
            return null;
        }
    }

    @Override
    public List<SentenceVector> semanticParse(String sentence, Integer accountId, List<Integer> domainIds, List<Integer> selectorIds, boolean isDropPunctuation) {
        List<Pattern> patterns = new ArrayList<>();
        patterns.add(Pattern.ALL);
        try {
            long t1 = System.currentTimeMillis();
            //1.用户自定义分词Channel.CUSTOM模式，得到各个词段
            SemanticInfo semantic = basisSemanticService.parseSemantic(sentence, patterns, Channel.CUSTOM, accountId + "");    //开始分词，使用用户渠道（默认使用hanlp）
            List<Term> terms = semantic.getTermList();                                                                  //分词结果中取出分词段

            Map<String, CustomizedWord> customizedWordMap = new HashMap<>();                                            //本次将要用到的自定义分词Map
            if(terms != null && terms.size() > 0){                                                                     //一般此处不用校验，如果没有词段，说明分词接口断了
                //2.查出此用户建立的所有的自定义分词（从全局Map或DB里面查）
                Map<String, CustomizedWord> customizedWordMapTemp = globalCustomizedWordMap.get(accountId);             //先尝试从全局缓存中取出这个accountId下的自定义分词Map
                if(customizedWordMapTemp != null && customizedWordMapTemp.size() > 0) {                                //如果成功取出，则将此分词Map赋予customizedWordMap
                    customizedWordMap = customizedWordMapTemp;
                }else{                                                                                                 //如果没有取出，则查数据库
                    List<CustomizedWord> customizedWords = customizedWordService.selectByAccountId(accountId);
                    if(customizedWords != null && customizedWords.size() > 0){
                        for (CustomizedWord customizedWord : customizedWords) {
                            customizedWordMap.put(customizedWord.getWord(), customizedWord);
                        }
                        globalCustomizedWordMap.put(accountId, customizedWordMap);                                      //如果查到此accountId有自定义分词集，则封装成Map并存放到大的全局Map中
                    }
                }

                //3.构建用来返回出去的句子向量，并向里面填充各个值；同时如果有自定义分词，则收集各个自定义分词的ID
                SentenceVector sentenceVector = new SentenceVector();                                                   //构建句子向量
                sentenceVector.setSentence(sentence);
                List<String> words = sentenceVector.getWords();
                List<String> natures = sentenceVector.getNatures();

                List<Integer> hitCustomizedWordIds = new ArrayList<>();                                                 //构建一个集合装载自定义分词ID
                for (int i = 0; i < terms.size(); i ++) {
                    Term term = terms.get(i);
                    String word = term.getWord();
                    String partOfSpeech = term.getPartOfSpeech();
                    words.set(i, word);                                                                                 //设置第i个word字符串
                    natures.set(i, partOfSpeech);                                                                       //设置第i个词性

                    CustomizedWord customizedWord = customizedWordMap.get(word);                                        //判断这个词是不是自定义分词，它存在于用户自定义分词中，就说明它是
                    if(customizedWord != null){                                                                        //如果此句子分词集合真的含有自定义分词，则将此词语Id保存到list中，查出其约束（自定义分词实体）
                        hitCustomizedWordIds.add(customizedWord.getId());
                    }
                }

                //4.如果有自定义分词，则将收集到的自定义分词的约束收集起来
                if(hitCustomizedWordIds.size() > 0){
                    List<Integer> neededHitCustomizedWordIds = new ArrayList<>();                                       //待收集词语ID列表
                    List<WordRestrictionParam> neededHitWordRestrictionParams = new ArrayList<>();                      //待收集约束的总列表
                    for (Integer hitCustomizedWordId : hitCustomizedWordIds) {
                        List<WordRestrictionParam> wordRestrictionParams = globalWordRestrictionParamMap.get(hitCustomizedWordId);
                        if(wordRestrictionParams == null || wordRestrictionParams.size() == 0){                        //从全局Map里面没有查出来，就从db里面查
                            neededHitCustomizedWordIds.add(hitCustomizedWordId);
                        }else{                                                                                         //如果查出来了，就放到总列表neededHitWordRestrictionParams里面
                            neededHitWordRestrictionParams.addAll(wordRestrictionParams);
                        }
                    }

                    List<WordRestrictionParam> hitWordRestrictionParams = wordRestrictionService.selectByWordIds(neededHitCustomizedWordIds, accountId);    //没查出来的，在DB里面查
                    neededHitWordRestrictionParams.addAll(hitWordRestrictionParams);                                    //把剩下的放到总列表里面

                    Map<Integer, List<WordRestrictionParam>> wordRestrictionParamMap = new HashMap<>();
                    for (WordRestrictionParam hitWordRestrictionParam : neededHitWordRestrictionParams) {               //遍历总列表，将约束按照规定格式Map<hitWordId, List<WordRestrictionParam>>存放
                        Integer hitWordId = hitWordRestrictionParam.getWordId();
                        List<WordRestrictionParam> wordRestrictionParams = wordRestrictionParamMap.get(hitWordId);
                        if(wordRestrictionParams == null){
                            wordRestrictionParams = new ArrayList<>();
                        }
                        wordRestrictionParams.add(hitWordRestrictionParam);
                        wordRestrictionParamMap.put(hitWordId, wordRestrictionParams);
                    }
                    globalWordRestrictionParamMap.putAll(wordRestrictionParamMap);                                      //将新查出的约束放到全局Map，以方便下次直接使用
                    sentenceVector.setWordRestrictionParamMap(wordRestrictionParamMap);
                }
                long t2 = System.currentTimeMillis();
                logger.info(String.format("[%d ms]请求text：%s | patterns：%s | channel：%d | dictname：%s | data：%s", t2 - t1, sentence, this.patterns2Str(patterns), Channel.SYS.getIndex(), accountId, printSemantic(semantic)));

                return Arrays.asList(sentenceVector);
            }
            return null;
        } catch (Exception e) {
            logger.error(String.format("[error]请求text：%s | error：%s", sentence, e.getMessage()));
            return null;
        }

    }

    @Override
    public Map<String, Double> extractKeyWords(String text, Channel channel, String name, int size) {
        logger.info("获取关键词");
        return basisSemanticService.extractKeyWords(text, channel, name, size);
    }

    @Override
    public Map<String, Double> wordReference(String word, int refNum) {
        return wordService.wordReference(word, refNum);
    }

    @Override
    public Double wordsDistance(String srcWord, String destWord) {
        return wordService.wordsDistance(srcWord, destWord);
    }

    @Override
    public Map<String, Double> wordsDistance(String srcWord, List<String> destWords) {
        if (StringUtils.isBlank(srcWord) || destWords == null) return null;
        Map<String, Double> wordsMaps = new HashMap<String, Double>();
        for (String destWord : destWords) {
            wordsMaps.put(destWord, wordService.wordsDistance(srcWord, destWord));
        }
        return wordsMaps;
    }

    //tools
    private String patterns2Str(List<Pattern> patterns) {
        if (patterns == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (Pattern pattern : patterns) {
            builder.append(String.valueOf(pattern.getIndex())).append('、');
        }
        return builder.toString();
    }

    private String printSemantic(SemanticInfo semantic) {
        if (semantic == null || semantic.getTermList() == null) return null;
        StringBuilder builder = new StringBuilder();
        for (Term term : semantic.getTermList()) {
            builder.append(term.getWord()).
                    append("-").
                    append(String.valueOf(term.getPartOfSpeech())).
                    append("-").
                    append(String.valueOf(term.getParent())).
                    append("-").
                    append(term.getRelate()).
                    append("<br>");
        }
        return builder.toString();
    }
}
