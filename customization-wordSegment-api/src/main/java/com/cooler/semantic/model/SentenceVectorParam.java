package com.cooler.semantic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceVectorParam implements Serializable {
    /**
     * 原始句子
     */
    private String sentence;

    /**
     * 分好词的字符串数组
     */
    private List<String> words = new ArrayList<>();

    /**
     * 分好词的词性数组
     */
    private List<String> natures = new ArrayList<>();

    /**
     * 设好权重的权重数值数组
     */
    private List<Double> weights = new ArrayList<>();

    /**
     * 各个词语的约束集合
     */
    private  Map<String, List<WordRestrictionParam>> wordRestrictionParamMap = new HashMap<>();


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

    public List<String> getNatures() {
        return natures;
    }

    public void setNatures(List<String> natures) {
        this.natures = natures;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    public Map<String, List<WordRestrictionParam>> getWordRestrictionParamMap() {
        return wordRestrictionParamMap;
    }

    public void setWordRestrictionParamMap(Map<String, List<WordRestrictionParam>> wordRestrictionParamMap) {
        this.wordRestrictionParamMap = wordRestrictionParamMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SentenceVectorParam that = (SentenceVectorParam) o;
        return words.equals(that.words);
    }

    @Override
    public int hashCode() {
        int result = sentence != null ? sentence.hashCode() : 0;
        result = 31 * result + words.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SentenceVectorParam{" +
                "sentence='" + sentence + '\'' +
                ", words=" + words +
                ", natures=" + natures +
                ", weights=" + weights +
                ", wordRestrictionParamMap=" + wordRestrictionParamMap +
                '}';
    }
}
