package com.cooler.semantic.service.internal;

import com.cooler.semantic.entity.CustomizedWord;

import java.util.List;
import java.util.Set;

public interface CustomizedWordService {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomizedWord record);

    int insertSelective(CustomizedWord record);

    CustomizedWord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomizedWord record);

    int updateByPrimaryKey(CustomizedWord record);

    /**
     * 按照accountId搜索用户自定义词语
     * @param accountId 用户AccountId
     * @return  自定义词语集合
     */
    List<CustomizedWord> selectByAccountId(Integer accountId);
}
