package com.cooler.semantic.entity;

import java.util.Date;

public class CustomizedWord {
    private Integer id;

    private String word;

    private Integer entityId;

    private String entity;

    private Integer accountId;

    private Integer state;

    private Date createTime;

    public CustomizedWord(Integer id, String word, Integer entityId, String entity, Integer accountId, Integer state, Date createTime) {
        this.id = id;
        this.word = word;
        this.entityId = entityId;
        this.entity = entity;
        this.accountId = accountId;
        this.state = state;
        this.createTime = createTime;
    }

    public CustomizedWord() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity == null ? null : entity.trim();
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}