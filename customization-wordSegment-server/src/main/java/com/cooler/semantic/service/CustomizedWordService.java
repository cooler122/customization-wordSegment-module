package com.cooler.semantic.service;

import com.cooler.semantic.entity.CustomizedWord;

public interface CustomizedWordService {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomizedWord record);

    int insertSelective(CustomizedWord record);

    CustomizedWord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomizedWord record);

    int updateByPrimaryKey(CustomizedWord record);
}
