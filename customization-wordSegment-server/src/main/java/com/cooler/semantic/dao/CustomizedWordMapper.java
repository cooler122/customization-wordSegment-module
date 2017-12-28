package com.cooler.semantic.dao;

import com.cooler.semantic.entity.CustomizedWord;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomizedWordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomizedWord record);

    int insertSelective(CustomizedWord record);

    CustomizedWord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomizedWord record);

    int updateByPrimaryKey(CustomizedWord record);

    List<CustomizedWord> selectByAccountId(@Param("accountId") Integer accountId);
}