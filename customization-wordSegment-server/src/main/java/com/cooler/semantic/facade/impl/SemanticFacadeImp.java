package com.cooler.semantic.facade.impl;

import com.cooler.semantic.facade.SemanticFacade;
import com.cooler.semantic.model.BasisSemantic;
import com.cooler.semantic.model.Term;
import com.cooler.semantic.util.BasisSemanticHandler;
import com.cooler.semantic.util.WordsHandler;
import com.cooler.semantic.util.Channel;
import com.cooler.semantic.util.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础语义接口实现
 */
@Slf4j
@Component("semanticFacade")
public class SemanticFacadeImp implements SemanticFacade {
    private static Logger log = LoggerFactory.getLogger(SemanticFacadeImp.class.getName());
    @Override
    public BasisSemantic parseSemantic(String text, List<Pattern> patterns) {
        return this.parseSemantic(text, patterns, Channel.SYS, null);
    }

    @Override
    public BasisSemantic parseSemantic(String text, List<Pattern> patterns, Channel channel, String name) {
//        log.info(String.format("请求text：%s | patterns：%s | channel：%d | dictname：%s",
//                text, this.patterns2Str(patterns), channel.getIndex(), dictName));
        try {
            long t1 = System.currentTimeMillis();
            BasisSemantic semantic = BasisSemanticHandler.parseSemantic(text, patterns, channel, name);
            long t2 = System.currentTimeMillis();
            log.info(String.format("[%d ms]请求text：%s | patterns：%s | channel：%d | dictname：%s | data：%s",
                    t2 - t1, text, this.patterns2Str(patterns), channel.getIndex(), name, printSemantic(semantic)));
            return semantic;
        } catch (Exception e) {
            log.error(String.format("[error]请求text：%s | error：%s", text, e.getMessage()));
            return null;
        }
    }

    @Override
    public Map<String, Double> extractKeyWords(String text, Channel channel, String name, int size) {
        log.info("获取关键词");
        return BasisSemanticHandler.extractKeyWords(text, channel, name, size);
    }

    @Override
    public Map<String, Double> wordReference(String word, int refNum) {
        return WordsHandler.wordReference(word, refNum);
    }

    @Override
    public Double wordsDistance(String srcWord, String destWord) {
        return WordsHandler.wordsDistance(srcWord, destWord);
    }

    @Override
    public Map<String, Double> wordsDistance(String srcWord, List<String> destWords) {
        if (StringUtils.isBlank(srcWord) || destWords == null) return null;
        Map<String, Double> wordsMaps = new HashMap<String, Double>();
        for (String destWord : destWords) {
            wordsMaps.put(destWord, WordsHandler.wordsDistance(srcWord, destWord));
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

    private String printSemantic(BasisSemantic semantic) {
        if (semantic == null || semantic.getTermList() == null) return null;
        StringBuilder builder = new StringBuilder();
        for (Term term : semantic.getTermList()) {
            builder.append(term.getWord()).append("-").append(String.valueOf(term.getPartOfSpeech())).append("-")
                    .append(String.valueOf(term.getParent())).append("-").append(term.getRelate()).append("<br>");
        }
        return builder.toString();
    }
}
