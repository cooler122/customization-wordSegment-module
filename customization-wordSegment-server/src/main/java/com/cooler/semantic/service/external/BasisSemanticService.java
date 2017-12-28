package com.cooler.semantic.service.external;

import com.cooler.semantic.model.SemanticInfo;
import com.cooler.semantic.enumeration.*;
import java.util.List;
import java.util.Map;

public interface BasisSemanticService {
    /**
     * Parse semantic basis semantic.
     *
     * @param text     the text
     * @param patterns the patterns
     * @param channel  the channel
     * @param name     the name
     * @return the basis semantic
     */
    SemanticInfo parseSemantic(String text, List<Pattern> patterns, Channel channel, String name);

    /**
     * Extract key words map.
     *
     * @param text    the text
     * @param channel the channel
     * @param name    the dict name
     * @param size    the size
     * @return the map
     */
    Map<String, Double> extractKeyWords(String text, Channel channel, String name, int size);

    /**
     * Segment string.
     *
     * @param text    the text
     * @param channel the channel
     * @param name    the dict name
     * @return the string
     */
    String[][] segment(String text, Channel channel, String name);

    /**
     * 依存句法分析.
     *
     * @param text    the text
     * @param channel the channel
     * @param name    the name
     * @param segArr  the seg arr
     * @return the string
     */
    String[][] dependency(String text, Channel channel, String name, String[][] segArr);

    /**
     * 语义依存分析.
     *
     * @param text    the text
     * @param channel the channel
     * @param name    the name
     * @param segArr  the seg arr
     * @return the string
     */
    String[][] semanticDep(String text, Channel channel, String name, String[][] segArr);

    /**
     * Ner 识别.
     *
     * @param text    the text
     * @param channel the channel
     * @param name    the name
     * @param segArr  the seg arr
     * @return the string
     */
    String[][] ner(String text, Channel channel, String name, String[][] segArr);
}
