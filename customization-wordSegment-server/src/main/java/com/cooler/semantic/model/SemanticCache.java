package com.cooler.semantic.model;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.cooler.semantic.util.Channel;
import com.cooler.semantic.util.SemanticUtil;
import org.apache.commons.lang3.StringUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.util.Map;

public class SemanticCache {
    private static CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .withCache("segment", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10000)))
            .withCache("dependency", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10000)))
            .withCache("semanticdep", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(1000)))
            .withCache("ner", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10000)))
            .withCache("wordref", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Map.class, ResourcePoolsBuilder.heap(10000)))
            .build();
    private static Cache<String, String> segCache;
    private static Cache<String, String> depCache;
    private static Cache<String, String> sDepCache;
    private static Cache<String, String> nerCache;
    private static Cache<String, Map> wordRefCache;

    static {
        cacheManager.init();
        segCache = cacheManager.getCache("segment", String.class, String.class);
        depCache = cacheManager.getCache("dependency", String.class, String.class);
        sDepCache = cacheManager.getCache("semanticdep", String.class, String.class);
        nerCache = cacheManager.getCache("ner", String.class, String.class);
        wordRefCache = cacheManager.getCache("wordref", String.class, Map.class);
    }

    public static String[][] getSegment(String text, Channel channel, String name) {
        if (!cacheEnabled(text, channel, name)) return null;
        String key = SemanticUtil.getMD5(text + name);
        String value = segCache.get(key);
        return value != null ? SemanticUtil.segStr2Array(text, value) : null;
    }

    public static void putSegment(String text, Channel channel, String name, String[][] segArr) {
        if (segArr == null || !cacheEnabled(text, channel, name)) return;
        String key = SemanticUtil.getMD5(text + name);
        String value = SemanticUtil.segArr2Str(segArr);
        segCache.put(key, value);
    }

    public static String[][] getDependency(String text, Channel channel, String name) {
        return cacheEnabled(text, channel, name)
                ? getStr2ArrFormat1(depCache, SemanticUtil.getMD5(text + name)) : null;
    }

    public static void putDependency(String text, Channel channel, String name, String[][] depArr) {
        if (depArr == null || !cacheEnabled(text, channel, name)) return;
        putArrFormat1(depCache, SemanticUtil.getMD5(text + name), depArr);
    }

    public static String[][] getSemanticDep(String text, Channel channel, String name) {
        return cacheEnabled(text, channel, name)
                ? getStr2ArrFormat1(sDepCache, SemanticUtil.getMD5(text + name)) : null;
    }

    public static void putSemanticDep(String text, Channel channel, String name, String[][] sDepArr) {
        if (sDepArr == null || !cacheEnabled(text, channel, name)) return;
        putArrFormat1(sDepCache, SemanticUtil.getMD5(text + name), sDepArr);
    }

    public static String[][] getNer(String text, Channel channel, String name) {
        return cacheEnabled(text, channel, name)
                ? getStr2ArrFormat1(nerCache, SemanticUtil.getMD5(text + name)) : null;
    }

    public static void putNer(String text, Channel channel, String name, String[][] nerArr) {
        if (nerArr == null || !cacheEnabled(text, channel, name)) return;
        putArrFormat1(nerCache, SemanticUtil.getMD5(text + name), nerArr);
    }

    public static Map<String, Double> getWordRefs(String word) {
        return StringUtils.isNoneBlank(word) ? (Map<String, Double>) (wordRefCache.get(word)) : null;
    }

    public static void putWordRefs(String word, Map<String, Double> wordRefs) {
        if (wordRefs == null) return;
        wordRefCache.put(word, wordRefs);
    }

    /**
     * 获取key对应的value值， 且value是"x/x x/x" 格式表示
     * 并转换为array格式.
     *
     * @param cache
     * @param key
     * @return
     */
    private static String[][] getStr2ArrFormat1(Cache<String, String> cache, String key) {
        String value = cache.get(key);
        return StringUtils.isNoneBlank(value) ? SemanticUtil.str2Arr(value) : null;
    }

    private static void putArrFormat1(Cache<String, String> cache, String key, String[][] arr) {
        String value = SemanticUtil.arr2Str(arr);
        cache.put(key, value);
    }

    private static boolean cacheEnabled(String text, Channel channel, String name) {
        // sys channel，和非动态分词的CUSTOM channel，支持缓存
        return channel.equals(Channel.SYS)
                || StringUtils.isNoneBlank(name) && CustomDictionary.getCustomDictionaryInt(name) > -1;
    }
}
