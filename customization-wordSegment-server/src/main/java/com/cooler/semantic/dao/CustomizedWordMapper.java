package com.cooler.semantic.dao;

import com.cooler.semantic.entity.CustomizedWord;

public interface CustomizedWordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomizedWord record);

    int insertSelective(CustomizedWord record);

    CustomizedWord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomizedWord record);

    int updateByPrimaryKey(CustomizedWord record);
}