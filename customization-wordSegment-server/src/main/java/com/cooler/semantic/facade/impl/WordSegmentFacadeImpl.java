package com.cooler.semantic.facade.impl;

import com.cooler.semantic.facade.WordSegmentFacade;
import com.cooler.semantic.model.SentenceVector;
import com.cooler.semantic.service.CustomizedWordService;
import com.cooler.semantic.service.WordRestrictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("wordSegmentFacade")
public class WordSegmentFacadeImpl implements WordSegmentFacade{
    private Logger logger = LoggerFactory.getLogger(WordSegmentFacadeImpl.class.getName());

    @Autowired
    private CustomizedWordService customizedWordService;

    @Autowired
    private WordRestrictionService wordRestrictionService;

    public List<SentenceVector> wordSegment(String sentence, Integer accountId, List<Integer> domainIds, List<Integer> selectorIds, boolean isDropPunctuation) {
        logger.info("分词模块");
        System.out.println("分词....");

        System.out.println(customizedWordService != null);
        System.out.println(wordRestrictionService != null);

        return null;
    }
}
