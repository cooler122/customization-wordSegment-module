package com.cooler.semantic.enumeration;

public enum Channel {
    //系统渠道
    SYS(1),
    //自定义渠道
    CUSTOM(10),
//    USER(100),
    ;

    private int index;

    Channel(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
