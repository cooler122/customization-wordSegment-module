package com.cooler.semantic.service.impl;

import com.cooler.semantic.dao.WordRestrictionMapper;
import com.cooler.semantic.entity.WordRestriction;
import com.cooler.semantic.service.WordRestrictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
