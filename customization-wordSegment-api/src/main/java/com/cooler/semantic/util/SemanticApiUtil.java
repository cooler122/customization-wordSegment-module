package com.cooler.semantic.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SemanticApiUtil {
    private static Map<String, String> posMap = new HashMap<String, String>();
    private static Map<String, String> antiPosMap = new HashMap<String, String>();
    private static Map<String, String> relateMap = new HashMap<String, String>();
    private static Map<String, String> antiRelateMap = new HashMap<String, String>();

    static {
        //初始化词性map
        initDictMap("pos.dict", posMap, antiPosMap);
        //初始化依存map
        initDictMap("relate.dict", relateMap, antiRelateMap);
    }

    private static void initDictMap(String dictName, Map<String, String> dictMap, Map<String, String> antiDictMap) {
        try {
            String line;
            BufferedReader br;
            InputStream path = SemanticApiUtil.class.getClassLoader().getResourceAsStream(dictName);
            br = new BufferedReader(new InputStreamReader(path));
            while ((line = br.readLine()) != null) {
                String[] list = line.split(" ");
                dictMap.put(list[0], list[1]);
                antiDictMap.put(list[1], list[0]);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 词性，句法成分的字母转成中文文字
     *
     * @param posEn the pos
     * @return string
     */
    public static String posEn2Cn(String posEn) {
        return StringUtils.isNoneBlank(posEn) ? posMap.get(posEn) : null;
    }

    /**
     * 词性，句法成分的字母转成英文
     *
     * @param posCn the cons
     * @return string
     */
    public static String posCn2En(String posCn) {
        return StringUtils.isNoneBlank(posCn) ? antiPosMap.get(posCn) : null;
    }

    public static String relateEn2Cn(String relateEn) {
        return StringUtils.isNoneBlank(relateEn) ? relateMap.get(relateEn) : null;
    }

    public static String relateCn2En(String relateCn) {
        return StringUtils.isNoneBlank(relateCn) ? antiRelateMap.get(relateCn) : null;
    }
}
