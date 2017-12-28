package com.cooler.semantic.service.external.impl;

import com.cooler.semantic.model.SemanticCache;
import com.cooler.semantic.service.external.WordService;
import com.hankcs.hanlp.utility.Word2VectorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("wordService")
public class WordServiceImpl implements WordService {
    private static Logger log = LoggerFactory.getLogger(WordServiceImpl.class.getName());
    /**
     * Word reference map.
     *
     * @param word   the word
     * @param refNum the ref num
     * @return the map
     */
    public Map<String, Double> wordReference(String word, int refNum) {
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
    public Double wordsDistance(String srcWord, String destWord) {
        return StringUtils.isNoneBlank(srcWord, destWord) ? new Word2VectorUtil().wordDistance(srcWord, destWord) : null;
    }
}
