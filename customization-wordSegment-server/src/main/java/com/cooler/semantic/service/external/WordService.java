package com.cooler.semantic.service.external;

import java.util.Map;

public interface WordService {

    /**
     * Word reference map.
     * @param word   the word
     * @param refNum the ref num
     * @return the map
     */
    Map<String, Double> wordReference(String word, int refNum);

    /**
     * Words distance double.
     * @param srcWord  the src word
     * @param destWord the dest word
     * @return the double
     */
    Double wordsDistance(String srcWord, String destWord) ;
}
