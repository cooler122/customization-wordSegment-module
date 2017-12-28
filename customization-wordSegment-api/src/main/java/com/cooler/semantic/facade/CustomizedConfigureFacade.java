package com.cooler.semantic.facade;

import java.util.List;

public interface CustomizedConfigureFacade {


    //******************************************************针对词典的操作
    /**
     * 保存自定义词典
     *
     * @param name 用户id
     * @return boolean boolean
     */
    boolean saveCustomizedDict(String name);

    /**
     * 加载自定义词典
     *
     * @param name 用户id
     * @return boolean boolean
     */
    boolean loadCustomizedDict(String name);

    /**
     * 删除自定义动态词典
     *
     * @param name 用户id
     * @return boolean boolean
     */
    boolean deleteCustomizedTrie(String name);

    //******************************************************针对自定义词语的操作
    /**
     * 增加自定义动态词典中词语
     *
     * @param name 用户id
     * @param term the term
     * @return boolean boolean
     */
    boolean addCustomizedWord(String name, String term);

    /**
     * 增加大量自定义动态词典中词语
     *
     * @param name  用户id
     * @param terms the terms
     * @return boolean boolean
     */
    boolean addCustomizedWords(String name, List<String> terms);

    /**
     * 删除大量自定义动态词典中词语
     *
     * @param name  用户id
     * @param words the words
     * @return boolean boolean
     */
    boolean deleteCustomizedWords(String name, List<String> words);

    /**
     * 删除自定义动态词典中词语
     *
     * @param name 用户id
     * @param word the word
     * @return boolean boolean
     */
    boolean deleteCustomizedWord(String name, String word);

    /**
     * 修改自定义动态词典中词语
     *
     * @param name 用户id
     * @param term the term
     * @return boolean boolean
     */
    boolean alterCustomizedWord(String name, String term);

    /**
     * 自定义动态词典中是否包含指定词语
     *
     * @param name 用户id
     * @param word the word
     * @return boolean boolean
     */
    boolean containCustomizedWord(String name, String word);


}
