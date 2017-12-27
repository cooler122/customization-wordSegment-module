package com.cooler.semantic.facade;

import java.util.List;

public interface DynamicConfigFacade {

    /**
     * 删除自定义动态词典
     *
     * @param name 用户id
     * @return boolean boolean
     */
    boolean deleteDynamicTrie(String name);

    /**
     * 增加自定义动态词典中词语
     *
     * @param name 用户id
     * @param term the term
     * @return boolean boolean
     */
    boolean addDynamicWord(String name, String term);

    /**
     * 删除自定义动态词典中词语
     *
     * @param name 用户id
     * @param word the word
     * @return boolean boolean
     */
    boolean deleteDynamicWord(String name, String word);

    /**
     * 增加大量自定义动态词典中词语
     *
     * @param name  用户id
     * @param terms the terms
     * @return boolean boolean
     */
    boolean addDynamicWords(String name, List<String> terms);

    /**
     * 删除大量自定义动态词典中词语
     *
     * @param name  用户id
     * @param words the words
     * @return boolean boolean
     */
    boolean deleteDynamicWords(String name, List<String> words);

    /**
     * 修改自定义动态词典中词语
     *
     * @param name 用户id
     * @param term the term
     * @return boolean boolean
     */
    boolean alterDynamicWord(String name, String term);

    /**
     * 自定义动态词典中是否包含指定词语
     *
     * @param name 用户id
     * @param word the word
     * @return boolean boolean
     */
    boolean containDynamicWord(String name, String word);

    /**
     * 保存自定义词典
     *
     * @param name 用户id
     * @return boolean boolean
     */
    boolean saveDynamicDict(String name);

    /**
     * 加载自定义词典
     *
     * @param name 用户id
     * @return boolean boolean
     */
    boolean loadDynamicDict(String name);
}
