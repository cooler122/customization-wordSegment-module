package com.cooler.semantic.enumeration;

import edu.fudan.nlp.Fudan;
import edu.fudan.nlp.parser.dep.DependencyTree;

import java.util.List;

public class Dependency {

    /**
     * 依存句法解析
     *
     * @param segArr the seg arr
     * @return the string
     */
    public static String[][] dependency(String[][] segArr) {
        String[][] fudanStrs = Fudan.posTag.tag2Array(segArr[0]);
        DependencyTree tree = Fudan.parser.parse2T(fudanStrs[0], fudanStrs[1]);
        return depTree2Arr(tree);
    }

    private static String[][] depTree2Arr(DependencyTree tree) {
        if (tree == null) return null;
        String[][] depArr = new String[2][tree.size()];
        int index = 0;
        for (List<String> list : tree.toList()) {
            depArr[0][index] = list.get(2);
            depArr[1][index] = SemanticApiUtil.relateCn2En(list.get(3));
            index++;
        }
        return depArr;
    }
}
