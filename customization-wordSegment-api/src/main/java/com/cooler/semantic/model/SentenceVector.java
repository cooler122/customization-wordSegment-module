package com.cooler.semantic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceVector implements Serializable, Cloneable {
    /**
     * 原始句子
     */
    private String sentence;

    /**
     * 分好词的字符串数组
     */
    private List<String> words = new ArrayList<>();

    /**
     * 每一个词语在数据库中对应的wordId
     */
    private List<Integer> wordIds = new ArrayList<>();

    /**
     * 分好词的词性数组
     */
    private List<String> natures = new ArrayList<>();

    /**
     * 实体类型集合（0为词语实体、1为字符串实体、2为正则实体、3为code实体）,默认全是0
     */
    private List<Integer> entityTypes = new ArrayList<>();

    /**
     * 此词语集合归属的实体ID集合
     */
    private List<Integer> entityIds = new ArrayList<>();

    /**
     * 此词语集合归属的实体名称集合
     */
    private List<String> entityNames = new ArrayList<>();

    /**
     * 设好权重的权重数值数组
     */
    private List<Double> weights = new ArrayList<>();

//    /**
//     * 实体归属Map(这个非常重要）
//     */
//    private Map<Integer, List<Entity>> attachedEntitysMap = new HashMap<>();

    /**
     * 自定义分词的约束Map（Map<wordId, List<WordRestrictionParam>>）
     */
    private  Map<Integer, List<WordRestrictionParam>> wordRestrictionParamMap = new HashMap<>();

//    /**
//     * 综合词段信息
//     */
//    private List<WordInfo> wordInfos;
//
//    /**
//     * 一对一关系Map，（为了多维度值计算相似度）
//     */
//    private Map<Integer, REntityWord> rEntityWordMap;
//
//    /**
//     * 一对多关系集合，（后续离散排布和转置，为了计算交集）
//     */
//    private List<R_Entities_Word> rEntitiesWords;


    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<Integer> getWordIds() {
        return wordIds;
    }

    public void setWordIds(List<Integer> wordIds) {
        this.wordIds = wordIds;
    }

    public List<String> getNatures() {
        return natures;
    }

    public void setNatures(List<String> natures) {
        this.natures = natures;
    }

    public List<Integer> getEntityTypes() {
        return entityTypes;
    }

    public void setEntityTypes(List<Integer> entityTypes) {
        this.entityTypes = entityTypes;
    }

    public List<Integer> getEntityIds() {
        return entityIds;
    }

    public void setEntityIds(List<Integer> entityIds) {
        this.entityIds = entityIds;
    }

    public List<String> getEntityNames() {
        return entityNames;
    }

    public void setEntityNames(List<String> entityNames) {
        this.entityNames = entityNames;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    public Map<Integer, List<WordRestrictionParam>> getWordRestrictionParamMap() {
        return wordRestrictionParamMap;
    }

    public void setWordRestrictionParamMap(Map<Integer, List<WordRestrictionParam>> wordRestrictionParamMap) {
        this.wordRestrictionParamMap = wordRestrictionParamMap;
    }
}
