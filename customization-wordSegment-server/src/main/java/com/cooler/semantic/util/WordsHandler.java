package com.cooler.semantic.util;

import com.hankcs.hanlp.utility.Word2VectorUtil;
import com.cooler.semantic.model.SemanticCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Slf4j
public class WordsHandler {
    private static Logger log = LoggerFactory.getLogger(WordsHandler.class.getName());
    /**
     * Word reference map.
     *
     * @param word   the word
     * @param refNum the ref num
     * @return the map
     */
    public static Map<String, Double> wordReference(String word, int refNum) {
        if (StringUtils.isBlank(word)) return null;
        log.info("请求word ref，word:" + word);
        // cache 中获取
        Map<String, Double> wordRefs;
        if ((wordRefs = SemanticCache.getWordRefs(word)) == null) {
            log.info("word ref by word2vec, word:" + word);
            wordRefs = new Word2VectorUtil().distance(word, refNum);
            SemanticCache.putWordRefs(word, wordRefs);
        }
        return wordRefs;
    }

    /**
     * Words distance double.
     *
     * @param srcWord  the src word
     * @param destWord the dest word
     * @return the double
     */
    public static Double wordsDistance(String srcWord, String destWord) {
        return StringUtils.isNoneBlank(srcWord, destWord) ? new Word2VectorUtil().wordDistance(srcWord, destWord) : null;
    }
}
