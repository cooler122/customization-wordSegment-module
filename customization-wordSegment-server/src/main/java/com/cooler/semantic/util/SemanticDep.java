package com.cooler.semantic.util;

import edu.stanford.nlp.StanfordParser;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.international.pennchinese.ChineseGrammaticalStructure;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Slf4j
public class SemanticDep {
    private static Logger log = LoggerFactory.getLogger(SemanticDep.class.getName());

    public static String[][] semanticDepend(String[][] segArr) {
        String[][] sDepArr = new String[2][segArr[0].length];
        String segStr = StringUtils.join(segArr[0], " ");
        try {
            Tree tree = StanfordParser.lParser.parse(segStr);
            ChineseGrammaticalStructure gs = new ChineseGrammaticalStructure(tree);
            Collection<TypedDependency> tdl = gs.typedDependenciesCollapsed();
            for (TypedDependency td : tdl) {
                int index = td.dep().get(CoreAnnotations.IndexAnnotation.class) - 1;
                //parent 位置
                sDepArr[0][index] = String.valueOf(td.gov().get(CoreAnnotations.IndexAnnotation.class) - 1);
                //relate信息
                sDepArr[1][index] = String.valueOf(td.reln());
            }
            //如果解析的语义有term缺失（符号会被去掉）的，parent设为-2
            if (sDepArr[0].length != tdl.size()) {
                for (int i = 0; i < sDepArr[0].length; i++) {
                    if (sDepArr[0][i] == null) sDepArr[0][i] = "-2";
                }
            }
        } catch (Exception e) {
            log.error("解析语义失败");
        }
        return sDepArr;
    }
}
