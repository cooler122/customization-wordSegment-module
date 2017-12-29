package com.cooler.semantic.facade;


import com.cooler.semantic.model.SemanticInfo;
import com.cooler.semantic.enumeration.Channel;
import com.cooler.semantic.enumeration.Pattern;
import com.cooler.semantic.model.SentenceVectorParam;

import java.util.List;
import java.util.Map;


public interface CustomizedSemanticFacade {

    /**
     * 解析基础语义信息1，根据pattern list，返回对应的模块解析结果，没有则对应结果为空.
     *
     * @param text     the text
     * @param patterns the patterns
     * @return the basis semantic
     */
    SemanticInfo semanticParse(String text, List<Pattern> patterns);


    /**
     * 解析基础语义信息2，根据pattern list，返回对应的模块解析结果，没有则对应结果为空.
     *
     * @param text     the text
     * @param patterns the patterns
     * @param channel  the channel
     * @param id     the dict name
     * @return the basis semantic
     */
    SemanticInfo semanticParse(String text, List<Pattern> patterns, Channel channel, String id);

    /**
     * 多从自定义分词（不用带出各个词语的约束）
     * @param sentence  原始句子
     * @param accountId 账号ID（必须，如果在账号ID下有分词存在于句子中，则用）
     * @param selectorIds   选择的分词器IDs（用哪些分词器来分此句子）      ！！！！注意后面进行调参的时候可能会调节selectIds来提升准确率，或者调换分词器。
     * @param isDropPunctuation   是否需要去掉符号
     * @return  多个 分好词的词段集合
     */
    List<SentenceVectorParam> semanticParse(String sentence, Integer accountId, List<Integer> selectorIds, boolean isDropPunctuation);

    /**
     * 多从自定义分词（按照1.账号ID 2.场景ID 3.意图ID 4.规则ID 来检索自定义分词是否存在于句子中）
     * @param sentence  原始句子
     * @param accountId 账号ID（必须，如果在账号ID下有分词存在于句子中，则用）
     * @param domainIds 场景-意图-规则的IDs（细粒度，可选）
     * @param selectorIds   选择的分词器IDs（用哪些分词器来分此句子）      ！！！！注意后面进行调参的时候可能会调节selectIds来提升准确率，或者调换分词器。
     * @param isDropPunctuation   是否需要去掉符号
     * @return  多个 分好词的词段集合
     */
    List<SentenceVectorParam> semanticParse(String sentence, Integer accountId, List<Integer> domainIds, List<Integer> selectorIds, boolean isDropPunctuation);

    /**
     * 关键词提取
     *
     * @param text the text
     * @return map map
     */
    Map<String, Double> extractKeyWords(String text, Channel channel, String name, int size);

    /**
     * 单个词语义联想
     *
     * @param word   the word
     * @param refNum the reference num
     * @return map map
     */
    Map<String, Double> wordReference(String word, int refNum);

    /**
     * 计算两个词语相关度
     *
     * @param srcWord  the word 1
     * @param destWord the word 2
     * @return double double
     */
    Double wordsDistance(String srcWord, String destWord);

    /**
     * 计算词语与句子中每个词的相关度
     *
     * @param srcWord   the word
     * @param destWords the words
     * @return map map
     */
    Map<String, Double> wordsDistance(String srcWord, List<String> destWords);

}
