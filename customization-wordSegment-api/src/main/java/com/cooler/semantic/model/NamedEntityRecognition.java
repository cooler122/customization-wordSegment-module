package com.cooler.semantic.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class NamedEntityRecognition implements Serializable {
    @JSONField(
        name = "n"
    )
    private String name;
    @JSONField(
        name = "head"
    )
    private int head;
    @JSONField(
        name = "tail"
    )
    private int tail;

    public NamedEntityRecognition() {
    }

    public String getName() {
        return this.name;
    }

    public NamedEntityRecognition setName(String name) {
        this.name = name;
        return this;
    }

    public int getHead() {
        return this.head;
    }

    public NamedEntityRecognition setHead(int head) {
        this.head = head;
        return this;
    }

    public int getTail() {
        return this.tail;
    }

    public NamedEntityRecognition setTail(int tail) {
        this.tail = tail;
        return this;
    }
}
