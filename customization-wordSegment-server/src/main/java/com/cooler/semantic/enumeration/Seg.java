package com.cooler.semantic.enumeration;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Seg {
    private static Segment segClient = HanLP.newSegment();

    public static String[][] segment(String text, Channel channel, String name) {
        List<Term> terms = null;
        if (channel.equals(Channel.SYS) || StringUtils.isBlank(name)) {
            terms = segClient.seg(text);
        } else if (channel.equals(Channel.CUSTOM)) {
            BinTrie tire = CustomDictionary.mTrie.get(name);
            terms = tire == null ? segClient.seg(text, name) : segClient.seg(text, tire);
        }
        return terms != null ? hanlpTerm2Array(terms) : null;
    }

    /**
     * Hanlp term 2 array string [ ] [ ].
     *
     * @param terms the terms
     * @return the string [ ] [ ]
     */
    private static String[][] hanlpTerm2Array(List<Term> terms) {
        String[][] segArr = new String[2][terms.size()];
        for (int i = 0; i < terms.size(); i++) {
            segArr[0][i] = terms.get(i).word;
            segArr[1][i] = terms.get(i).nature.name();
        }
        return segArr;
    }
}
