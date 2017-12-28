package com.cooler.semantic.facade.impl;

import com.cooler.semantic.service.external.CustomizedConfigureService;
import com.cooler.semantic.facade.CustomizedConfigureFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("customizedConfigureFacade")
public class CustomizedConfigureFacadeImp implements CustomizedConfigureFacade {

    @Autowired
    private CustomizedConfigureService customizedConfigureService;

    @Override
    public boolean saveCustomizedDict(String name) {
        return StringUtils.isNoneBlank(name) && customizedConfigureService.saveCustomizedDict(name);
    }

    @Override
    public boolean loadCustomizedDict(String name) {
        return StringUtils.isNoneBlank(name) && customizedConfigureService.loadCustomizedDict(name);
    }

    @Override
    public boolean deleteCustomizedTrie(String name) {
        return StringUtils.isNoneBlank(name) && customizedConfigureService.deleteCustomizedTrie(name);
    }



    @Override
    public boolean addCustomizedWord(String name, String term) {
        return StringUtils.isNoneBlank(name, term) && customizedConfigureService.addCustomizedWord(name, term);
    }

    @Override
    public boolean addCustomizedWords(String name, List<String> terms) {
        return StringUtils.isNoneBlank(name) && terms != null && customizedConfigureService.addCustomizedWords(name, terms);
    }

    @Override
    public boolean deleteCustomizedWord(String name, String word) {
        return StringUtils.isNoneBlank(name, word) && customizedConfigureService.deleteCustomizedWord(name, word);
    }

    @Override
    public boolean deleteCustomizedWords(String name, List<String> words) {
        return StringUtils.isNoneBlank(name) && words != null && customizedConfigureService.deleteCustomizedWords(name, words);
    }

    @Override
    public boolean alterCustomizedWord(String name, String term) {
        return StringUtils.isNoneBlank(name, term) && customizedConfigureService.alterCustomizedWord(name, term);
    }

    @Override
    public boolean containCustomizedWord(String name, String word) {
        return StringUtils.isNoneBlank(name, word) && customizedConfigureService.containCustomizedWord(name, word);
    }


}
