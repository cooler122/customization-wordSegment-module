package com.cooler.semantic.facade;


import com.cooler.semantic.model.BasisSemantic;
import com.cooler.semantic.util.Channel;
import com.cooler.semantic.util.Pattern;

import java.util.List;
import java.util.Map;


public interface SemanticFacade {

    /**
     * 解析基础语义信息，根据pattern list，返回对应的模块解析结果，没有则对应结果为空.
     *
     * @param text     the text
     * @param patterns the patterns
     * @return the basis semantic
     */
    BasisSemantic parseSemantic(String text, List<Pattern> patterns);


    /**
     * 解析基础语义信息，根据pattern list，返回对应的模块解析结果，没有则对应结果为空.
     *
     * @param text     the text
     * @param patterns the patterns
     * @param channel  the channel
     * @param name     the dict name
     * @return the basis semantic
     */
    BasisSemantic parseSemantic(String text, List<Pattern> patterns, Channel channel, String name);


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
