package com.cooler.semantic.service.internal.impl;

import com.cooler.semantic.dao.CustomizedWordMapper;
import com.cooler.semantic.entity.CustomizedWord;
import com.cooler.semantic.service.internal.CustomizedWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("customizedWordService")
public class CustomizedWordServiceImpl implements CustomizedWordService {

    @Autowired
    private CustomizedWordMapper customizedWordMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return customizedWordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CustomizedWord record) {
        return customizedWordMapper.insert(record);
    }

    @Override
    public int insertSelective(CustomizedWord record) {
        return customizedWordMapper.insertSelective(record);
    }

    @Override
    public CustomizedWord selectByPrimaryKey(Integer id) {
        return customizedWordMapper.selectByPrimaryKey(id);
    }



    @Override
    public int updateByPrimaryKeySelective(CustomizedWord record) {
        return customizedWordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CustomizedWord record) {
        return customizedWordMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CustomizedWord> selectByAccountId(Integer accountId) {
        return customizedWordMapper.selectByAccountId(accountId);
    }
}
