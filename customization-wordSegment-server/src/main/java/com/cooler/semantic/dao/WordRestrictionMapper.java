package com.cooler.semantic.dao;

import com.cooler.semantic.entity.WordRestriction;

public interface WordRestrictionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WordRestriction record);

    int insertSelective(WordRestriction record);

    WordRestriction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordRestriction record);

    int updateByPrimaryKey(WordRestriction record);
}