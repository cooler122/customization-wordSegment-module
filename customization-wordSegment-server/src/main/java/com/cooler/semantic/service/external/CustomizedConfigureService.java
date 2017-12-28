package com.cooler.semantic.service.external;

import java.util.List;


public interface CustomizedConfigureService {

    /**
     * 保存词典
     * @param dictName
     * @return
     */
    boolean saveCustomizedDict(String dictName);

    /**
     * 加载词典
     * @param dictName
     * @return
     */
    boolean loadCustomizedDict(String dictName);

    /**
     * 删除词典
     * @param dictName
     * @return
     */
    boolean deleteCustomizedTrie(String dictName);


    /**
     * 添加某用户的词语
     * @param id
     * @param term
     * @return
     */
    boolean addCustomizedWord(String id, String term);

    /**
     * 批量某用户添加词语
     * @param id
     * @param terms
     * @return
     */
    boolean addCustomizedWords(String id, List<String> terms);

    /**
     * 删除某用户词语
     * @param id
     * @param word
     * @return
     */
    boolean deleteCustomizedWord(String id, String word);

    /**
     * 批量删除某用户词语
     * @param id
     * @param words
     * @return
     */
    boolean deleteCustomizedWords(String id, List<String> words);

    /**
     * 修改某用户词语
     * @param id
     * @param term
     * @return
     */
    boolean alterCustomizedWord(String id, String term) ;

    /**
     * 查询是否包含某用户词语
     * @param id
     * @param word
     * @return
     */
    boolean containCustomizedWord(String id, String word);

    
}
