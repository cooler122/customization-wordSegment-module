package com.cooler.semantic.facade.impl;

import com.cooler.semantic.facade.WordSegmentFacade;
import com.cooler.semantic.model.SentenceVector;

import java.util.List;

public class WordSegmentFacadeImpl implements WordSegmentFacade
{
    public List<SentenceVector> wordSegregate(String sentence, Integer accountId, List<Integer> domainIds, List<Integer> selectorIds, boolean isDropPunctuation) {
        return null;
    }

    public static void main(String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
