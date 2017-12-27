package com.cooler.semantic.util;

import java.util.*;

public class MapUtil {

    public static Map sortByComparator(Map unSortMap, final boolean descend) {
        List list = new LinkedList(unSortMap.entrySet());
        // sort list based on comparator
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                int compare = ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
                return descend ? -compare : compare;
            }
        });
        // put sorted list into map again
        //LinkedHashMap make sure order in which keys were inserted
        Map sortedMap = new LinkedHashMap();
        for (Object aList : list) {
            Map.Entry entry = (Map.Entry) aList;
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
