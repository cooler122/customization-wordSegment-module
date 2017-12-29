package com.cooler.semantic.service.internal;

import com.cooler.semantic.entity.WordRestriction;
import com.cooler.semantic.model.WordRestrictionParam;

import java.util.List;

public interface WordRestrictionService {
    int deleteByPrimaryKey(Integer id);

    int insert(WordRestriction record);

    int insertSelective(WordRestriction record);

    WordRestriction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordRestriction record);

    int updateByPrimaryKey(WordRestriction record);

    List<WordRestrictionParam> selectByWords(List<String> hitCustomizedWords, Integer accountId);
}
