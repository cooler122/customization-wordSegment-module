package com.cooler.semantic.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Term implements Serializable {

    @JSONField(
        name = "idx"
    )
    private int index;                  //序列号
    @JSONField(
        name = "w"
    )
    private String word;                //分词段
    @JSONField(
        name = "pos"
    )
    private String partOfSpeech;        //词性
    @JSONField(
        name = "ne"
    )
    private String namedEntity;
    @JSONField(
        name = "p"
    )
    private int parent;
    @JSONField(
        name = "rel"
    )
    private String relate;
    @JSONField(
        name = "wt"
    )
    private double weight;             //权重
    @JSONField(
        name = "sem_p"
    )
    private int semanticParent;
    @JSONField(
        name = "sem_rel"
    )
    private String semanticRelate;

    public Term() {
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPartOfSpeech() {
        return this.partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getNamedEntity() {
        return this.namedEntity;
    }

    public void setNamedEntity(String namedEntity) {
        this.namedEntity = namedEntity;
    }

    public int getParent() {
        return this.parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getRelate() {
        return this.relate;
    }

    public void setRelate(String relate) {
        this.relate = relate;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSemanticParent() {
        return this.semanticParent;
    }

    public void setSemanticParent(int semanticParent) {
        this.semanticParent = semanticParent;
    }

    public String getSemanticRelate() {
        return this.semanticRelate;
    }

    public void setSemanticRelate(String semanticRelate) {
        this.semanticRelate = semanticRelate;
    }

    public String toString() {
        return "Term{index=" + this.index + ", word='" + this.word + '\'' + ", partOfSpeech='" + this.partOfSpeech + '\'' + ", namedEntity='" + this.namedEntity + '\'' + ", parent=" + this.parent + ", relate='" + this.relate + '\'' + ", weight=" + this.weight + ", semanticParent=" + this.semanticParent + ", semanticRelate='" + this.semanticRelate + '\'' + '}';
    }
}