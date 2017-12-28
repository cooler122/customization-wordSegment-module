package com.cooler.semantic.service.external.impl;

import com.cooler.semantic.service.external.BasisSemanticService;
import com.cooler.semantic.enumeration.*;
import com.hankcs.hanlp.summary.TfIdfKeyword;
import com.cooler.semantic.model.SemanticInfo;
import com.cooler.semantic.model.NamedEntityRecognition;
import com.cooler.semantic.model.Term;
import com.cooler.semantic.model.SemanticCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("basisSemanticService")
public class BasisSemanticServiceImpl implements BasisSemanticService {
    private static Logger log = LoggerFactory.getLogger(BasisSemanticServiceImpl.class.getName());

    public SemanticInfo parseSemantic(String sentence, List<Pattern> patterns, Channel channel, String name) {
        if (StringUtils.isBlank(sentence)) {
            return null;
        }
        long[] timeSpends = {-1, -1, -1, -1};
        boolean[] exists = {true, true, true, true};
        SemanticInfo semantic = new SemanticInfo();
        //判断pattern
        //默认需要分词
        long t1 = System.currentTimeMillis();
        String[][] segArr = segment(sentence, channel, name);
        long t2 = System.currentTimeMillis();
        timeSpends[0] = t2-t1;

        if (segArr == null) {
            log.error("分词结果为null，text：", sentence);
            return null;
        }
        updateWithSeg(semantic, segArr);

        //依存句法
        if (patterns.contains(Pattern.DEP) || patterns.contains(Pattern.ALL)) {
            t1 = System.currentTimeMillis();
            String[][] depArr = dependency(sentence, channel, name, segArr);
            updateWithDependency(semantic, depArr);
            timeSpends[1] = System.currentTimeMillis() - t1;
            exists[1] = depArr != null;
        }
        //语义依存 （语义依存需要显式传进来）
        if (patterns.contains(Pattern.SDEP)) {
            t1 = System.currentTimeMillis();
            String[][] sDepArr = semanticDep(sentence, channel, name, segArr);
            updateWithSemanticDep(semantic, sDepArr);
            timeSpends[2] = System.currentTimeMillis() - t1;
            exists[2] = sDepArr != null;
        }
        //NER
        if (patterns.contains(Pattern.NER) || patterns.contains(Pattern.ALL)) {
            t1 = System.currentTimeMillis();
            String[][] nerArr = ner(sentence, channel, name, segArr);
            updateWithNer(semantic, nerArr);
            timeSpends[3] = System.currentTimeMillis() - t1;
            exists[3] = nerArr != null;
        }

        log.info(String.format("text：%s | 各模块耗时：[seg-%s] %d ms [dep-%s] %d ms [sdep-%s] %d ms [ner-%s] %d ms",
                sentence, exists[0], timeSpends[0], exists[1], timeSpends[1],
                exists[2], timeSpends[2], exists[3], timeSpends[3]));
        return semantic;
    }

    public Map<String, Double> extractKeyWords(String text, Channel channel, String name, int size) {
        //暂时使用原来的方式，默认的分词+权重计算
        return StringUtils.isNoneBlank(text) ? new TfIdfKeyword().getTermAndRank(text, size) : null;
    }

    public String[][] segment(String text, Channel channel, String name) {
        String[][] segArr;
        if ((segArr = SemanticCache.getSegment(text, channel, name)) == null) {
            //请求分词信息
            segArr = Seg.segment(text, channel, name);
            if (segArr != null) {
                SemanticCache.putSegment(text, channel, name, segArr);
            }
        }
        return segArr;
    }

    public String[][] dependency(String text, Channel channel, String name, String[][] segArr) {
        log.debug("请求依存句法");
        String[][] depArr;
        if ((depArr = SemanticCache.getDependency(text, channel, name)) == null) {
            //请求依存句法
            depArr = Dependency.dependency(segArr);
            //保存
            if (depArr != null) {
                SemanticCache.putDependency(text, channel, name, depArr);
            }
        }
        return depArr;
    }

    public String[][] semanticDep(String text, Channel channel, String name, String[][] segArr) {
        log.debug("请求语义依存");
        String[][] sDepArr;
        if ((sDepArr = SemanticCache.getSemanticDep(text, channel, name)) == null) {
            //请求依存句法
            sDepArr = SemanticDep.semanticDepend(segArr);
            //保存
            if (sDepArr != null) {
                SemanticCache.putSemanticDep(text, channel, name, sDepArr);
            }
        }
        return sDepArr;
    }

    public String[][] ner(String text, Channel channel, String name, String[][] segArr) {
        log.debug("请求NER");
        String[][] nerArr;
        if ((nerArr = SemanticCache.getNer(text, channel, name)) == null) {
            //请求依存句法
            nerArr = Ner.ner(segArr);
            //保存
            if (nerArr != null) {
                SemanticCache.putNer(text, channel, name, nerArr);
            }
        }
        return nerArr;
    }

    /**
     * Update with seg boolean.
     * @param semantic the semantic
     * @param segArr   the seg arr
     * @return the boolean
     */
    private static boolean updateWithSeg(SemanticInfo semantic, String[][] segArr) {
        if (segArr == null) return false;
        List<Term> terms = getTermList(semantic);
        for (int i = 0; i < segArr[0].length; i++) {
            Term term = new Term();
            term.setIndex(i);
            term.setWord(segArr[0][i]);
            term.setPartOfSpeech(segArr[1][i]);
            terms.add(term);
        }
        return true;
    }

    /**
     * Update with dependency boolean.
     * @param semantic the semantic
     * @param depArr   the dep arr
     * @return the boolean
     */
    private static boolean updateWithDependency(SemanticInfo semantic, String[][] depArr) {
        if (depArr == null) return false;
        List<Term> terms = getTermList(semantic);
        for (int i = 0; i < depArr[0].length; i++) {
            terms.get(i).setParent(Integer.valueOf(depArr[0][i]));
            terms.get(i).setRelate(depArr[1][i]);
        }
        return true;
    }

    /**
     * Update with semantic dep boolean.
     * @param semantic the semantic
     * @param sDepArr  the s dep arr
     * @return the boolean
     */
    private static boolean updateWithSemanticDep(SemanticInfo semantic, String[][] sDepArr) {
        if (sDepArr == null) return false;
        List<Term> terms = getTermList(semantic);

        for (int i = 0; i < sDepArr[0].length; i++) {
            Term term = terms.get(i);
            term.setSemanticParent(Integer.valueOf(sDepArr[0][i]));
            term.setSemanticRelate(sDepArr[1][i]);
        }
        return true;
    }

    /**
     * Update with ner boolean.
     * @param semantic the semantic
     * @param nerArr   the ner arr
     * @return the boolean
     */
    private static boolean updateWithNer(SemanticInfo semantic, String[][] nerArr) {
        if (nerArr == null) return false;
        List<NamedEntityRecognition> ners = semantic.getNamedEntityRecognitionList();
        if (ners == null) {
            ners = new ArrayList<NamedEntityRecognition>();
            semantic.setNamedEntityRecognitionList(ners);
        }
        for (int i = 0; i < nerArr[0].length; i++) {
            String[] offset = nerArr[0][i].split("-");
            if (offset.length != 2) continue;
            NamedEntityRecognition ner = new NamedEntityRecognition();
            ner.setHead(Integer.valueOf(offset[0]));
            ner.setTail(Integer.valueOf(offset[1]));
            ner.setName(nerArr[1][i]);
            ners.add(ner);
        }
        return true;
    }

    private static List<Term> getTermList(SemanticInfo semantic) {
        List<Term> terms = semantic.getTermList();
        if (terms == null) {
            terms = new ArrayList<Term>();
            semantic.setTermList(terms);
        }
        return terms;
    }

}
