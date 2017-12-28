package com.cooler.semantic.enumeration;

import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.recognition.ns.PlaceRecognition;
import com.hankcs.hanlp.recognition.nt.OrganizationRecognition;
import com.hankcs.hanlp.recognition.ntime.TimeRecognition;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.utility.NameEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ner {

    public static String[][] ner(String[][] segArr) {
        //转为hanlp的Term处理
        List<Term> terms = segArr2HanlpTerms(segArr);
        // 记录ner对象
        List<NameEntity> nerEntities = new ArrayList<NameEntity>();
        updateNerEntities(nerEntities, TimeRecognition.timeRecognize(terms), "t");
        updateNerEntities(nerEntities, PlaceRecognition.Recognition(terms), "ns");
        updateNerEntities(nerEntities, OrganizationRecognition.Recognition(terms), "nt");
        updateNerEntities(nerEntities, personRecognize(terms), "nr");
        // 转换为string array
        return nerList2Arr(nerEntities);
    }

    private static List<Term> segArr2HanlpTerms(String[][] segArr) {
        List<Term> terms = new ArrayList<Term>();
        for (int i = 0; i < segArr[0].length; i++) {
            Term term = new Term(segArr[0][i], Nature.fromString(segArr[1][i]));
            terms.add(term);
        }
        return terms;
    }

    /**
     * 解析人名.
     *
     * @param terms the terms
     * @return list
     */
    private static List<NameEntity> personRecognize(List<Term> terms) {
        List<NameEntity> entities = new ArrayList<NameEntity>();
        for (int i = 0; i < terms.size(); i++) {
            //人名判断
            if (StringUtils.startsWith(terms.get(i).nature.name(), "nr")) {
                NameEntity entity = new NameEntity(i, i);
                entity.setName("nr");
                entities.add(entity);
            }
        }
        return entities;
    }

    /**
     * 更新ner的实体list
     *
     * @param nerEntities the ner entities
     * @param newEntities the new entities
     */
    private static void updateNerEntities(List<NameEntity> nerEntities, List<NameEntity> newEntities, String defaultNer) {
        if (newEntities == null) return;
        for (NameEntity entity : newEntities) {
            //判断是否冲突（对冲突的处理，目前直接丢弃后来的）
            if (!isEntityConflict(entity, nerEntities)) {
                if (StringUtils.isBlank(entity.getName())) {
                    entity.setName(defaultNer);
                }
                nerEntities.add(entity);
            }
        }
    }

    private static boolean isEntityConflict(NameEntity entity, List<NameEntity> nerEntities) {
        for (NameEntity ner : nerEntities) {
            // 判断位置是否冲突
            if (entity.getHead() >= ner.getHead() && entity.getHead() <= ner.getTail()
                    || entity.getTail() >= ner.getHead() && entity.getTail() <= ner.getTail()) {
                return true;
            }
        }
        return false;
    }

    private static String[][] nerList2Arr(List<NameEntity> nerEntities) {
        if (nerEntities.size() == 0) return null;
        String[][] nerArr = new String[2][nerEntities.size()];
        //排序
        Collections.sort(nerEntities, new Comparator<NameEntity>() {
            @Override
            public int compare(NameEntity o1, NameEntity o2) {
                return o1.getHead() - o2.getHead();
            }
        });

        for (int i = 0; i < nerEntities.size(); i++) {
            nerArr[0][i] = String.format("%d-%d", nerEntities.get(i).getHead(), nerEntities.get(i).getTail());
            nerArr[1][i] = nerEntities.get(i).getName();
        }
        return nerArr;
    }
}
