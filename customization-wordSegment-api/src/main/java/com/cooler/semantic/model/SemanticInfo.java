package com.cooler.semantic.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class SemanticInfo implements Serializable {
    @JSONField(
        name = "term_ls"
    )
    private List<Term> termList;
    @JSONField(
        name = "ners"
    )
    private List<NamedEntityRecognition> namedEntityRecognitionList;

    public SemanticInfo() {
    }

    public List<Term> getTermList() {
        return this.termList;
    }

    public SemanticInfo setTermList(List<Term> termList) {
        this.termList = termList;
        return this;
    }

    public List<NamedEntityRecognition> getNamedEntityRecognitionList() {
        return this.namedEntityRecognitionList;
    }

    public SemanticInfo setNamedEntityRecognitionList(List<NamedEntityRecognition> namedEntityRecognitionList) {
        this.namedEntityRecognitionList = namedEntityRecognitionList;
        return this;
    }
}