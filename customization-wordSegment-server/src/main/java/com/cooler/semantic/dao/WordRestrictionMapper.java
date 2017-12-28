package com.cooler.semantic.dao;

import com.cooler.semantic.entity.WordRestriction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WordRestrictionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WordRestriction record);

    int insertSelective(WordRestriction record);

    WordRestriction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordRestriction record);

    int updateByPrimaryKey(WordRestriction record);

    List<WordRestriction> selectByWordIds(@Param("wordIds") List<Integer> hitCustomizedWordIds, @Param("accountId") Integer accountId);
}