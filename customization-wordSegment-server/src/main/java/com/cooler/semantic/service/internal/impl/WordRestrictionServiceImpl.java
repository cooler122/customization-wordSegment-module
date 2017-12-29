package com.cooler.semantic.service.internal.impl;

import com.cooler.semantic.dao.WordRestrictionMapper;
import com.cooler.semantic.entity.WordRestriction;
import com.cooler.semantic.model.WordRestrictionParam;
import com.cooler.semantic.service.internal.WordRestrictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("wordRestrictionService")
public class WordRestrictionServiceImpl implements WordRestrictionService {

    @Autowired
    private WordRestrictionMapper wordRestrictionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return wordRestrictionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WordRestriction record) {
        return wordRestrictionMapper.insert(record);
    }

    @Override
    public int insertSelective(WordRestriction record) {
        return wordRestrictionMapper.insertSelective(record);
    }

    @Override
    public WordRestriction selectByPrimaryKey(Integer id) {
        return wordRestrictionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(WordRestriction record) {
        return wordRestrictionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WordRestriction record) {
        return wordRestrictionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<WordRestrictionParam> selectByWords(List<String> hitCustomizedWords, Integer accountId) {
        List<WordRestriction> wordRestrictions = wordRestrictionMapper.selectByWords(hitCustomizedWords, accountId);
        List<WordRestrictionParam> wordRestrictionParams = new ArrayList<>();
        for (WordRestriction wordRestriction : wordRestrictions) {
            wordRestrictionParams.add(convert(wordRestriction));
        }
        return wordRestrictionParams;
    }

    private WordRestrictionParam convert(WordRestriction wordRestriction){
        WordRestrictionParam wordRestrictionParam = new WordRestrictionParam();
        wordRestrictionParam.setWordId(wordRestriction.getWordId());
        wordRestrictionParam.setSceneId(wordRestriction.getSceneId());
        wordRestrictionParam.setSceneName(wordRestriction.getSceneName());
        wordRestrictionParam.setIntentId(wordRestriction.getIntentId());
        wordRestrictionParam.setIntentName(wordRestriction.getIntentName());
        wordRestrictionParam.setRuleId(wordRestriction.getRuleId());
        wordRestrictionParam.setRuleName(wordRestriction.getRuleName());
        wordRestrictionParam.setEntityId(wordRestriction.getEntityId());
        wordRestrictionParam.setEntityName(wordRestriction.getEntityName());
        wordRestrictionParam.setAccountId(wordRestriction.getAccountId());
        return wordRestrictionParam;
    }
}
