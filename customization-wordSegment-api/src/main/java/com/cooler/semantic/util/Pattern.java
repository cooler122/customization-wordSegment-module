package com.cooler.semantic.util;

/**
 * 基础语义支持的解析模块
 *
 */
public enum Pattern {
    ALL("所有模块", 0),
    SEG("分词", 1),
    NER("命名实体识别", 2),
    DEP("依存句法分析", 3),
    SDEP("语义依存分析", 4);

    private String name;
    private int index;

    private Pattern(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
