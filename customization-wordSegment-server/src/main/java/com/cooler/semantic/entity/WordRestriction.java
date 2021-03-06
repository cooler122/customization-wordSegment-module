package com.cooler.semantic.entity;

import java.util.Date;

public class WordRestriction {
    private Integer id;

    private Integer wordId;

    private Integer sceneId;

    private String sceneName;

    private Integer intentId;

    private String intentName;

    private Integer ruleId;

    private String ruleName;

    private Integer entityId;

    private String entityName;

    private Integer accountId;

    private Integer state;

    private Date createTime;

    public WordRestriction(Integer id, Integer wordId, Integer sceneId, String sceneName, Integer intentId, String intentName, Integer ruleId, String ruleName, Integer entityId, String entityName, Integer accountId, Integer state, Date createTime) {
        this.id = id;
        this.wordId = wordId;
        this.sceneId = sceneId;
        this.sceneName = sceneName;
        this.intentId = intentId;
        this.intentName = intentName;
        this.ruleId = ruleId;
        this.ruleName = ruleName;
        this.entityId = entityId;
        this.entityName = entityName;
        this.accountId = accountId;
        this.state = state;
        this.createTime = createTime;
    }

    public WordRestriction() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName == null ? null : sceneName.trim();
    }

    public Integer getIntentId() {
        return intentId;
    }

    public void setIntentId(Integer intentId) {
        this.intentId = intentId;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName == null ? null : intentName.trim();
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName == null ? null : entityName.trim();
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