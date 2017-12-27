package com.cooler.semantic.facade.impl;

import com.cooler.semantic.util.DynamicConfiger;
import com.cooler.semantic.facade.DynamicConfigFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dynamicConfigFacade")
public class DynamicConfigFacadeImp implements DynamicConfigFacade {
    @Override
    public boolean deleteDynamicTrie(String name) {
        return StringUtils.isNoneBlank(name) && DynamicConfiger.deleteDynamicTrie(name);
    }

    @Override
    public boolean addDynamicWord(String name, String term) {
        return StringUtils.isNoneBlank(name, term) && DynamicConfiger.addDynamicWord(name, term);
    }

    @Override
    public boolean deleteDynamicWord(String name, String word) {
        return StringUtils.isNoneBlank(name, word) && DynamicConfiger.deleteDynamicWord(name, word);
    }

    @Override
    public boolean addDynamicWords(String name, List<String> terms) {
        return StringUtils.isNoneBlank(name) && terms != null && DynamicConfiger.addDynamicWords(name, terms);
    }

    @Override
    public boolean deleteDynamicWords(String name, List<String> words) {
        return StringUtils.isNoneBlank(name) && words != null && DynamicConfiger.deleteDynamicWords(name, words);
    }

    @Override
    public boolean alterDynamicWord(String name, String term) {
        return StringUtils.isNoneBlank(name, term) && DynamicConfiger.alterDynamicWord(name, term);
    }

    @Override
    public boolean containDynamicWord(String name, String word) {
        return StringUtils.isNoneBlank(name, word) && DynamicConfiger.containDynamicWord(name, word);
    }

    @Override
    public boolean saveDynamicDict(String name) {
        return StringUtils.isNoneBlank(name) && DynamicConfiger.saveDynamicDict(name);
    }

    @Override
    public boolean loadDynamicDict(String name) {
        return StringUtils.isNoneBlank(name) && DynamicConfiger.loadDynamicDict(name);
    }
}
