package com.cooler.semantic.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;

@Slf4j
public class SemanticUtil {
    private static Logger log = LoggerFactory.getLogger(SemanticUtil.class.getName());

    /**
     * Gets md 5.
     *
     * @param str the str
     * @return the md 5
     */
    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            log.error("md5加密错误");
            return null;
        }
    }

    /**
     * Seg str 2 array string [ ] [ ].
     *
     * @param text the text
     * @param seg  the seg
     * @return the string [ ] [ ]
     */
    public static String[][] segStr2Array(String text, String seg) {
        String[][] segArr = str2Arr(seg);
        // 把保存的length转换为对应的text
        int start = 0;
        for (int i = 0; i < segArr[0].length; i++) {
            int end = start + Integer.valueOf(segArr[0][i]);
            //改为text
            segArr[0][i] = StringUtils.substring(text, start, end);
            start = end;
        }
        return segArr;
    }


    public static String segArr2Str(String[][] segArr) {
        String[][] arr = new String[2][segArr[0].length];
        // 转换text为length
        for (int i = 0; i < arr[0].length; i++) {
            arr[0][i] = String.valueOf(segArr[0][i].length());
            arr[1][i] = segArr[1][i];
        }
        return arr2Str(arr);
    }

    /**
     * arr 2 str string.
     *
     * @param arr the seg arr
     * @return the string
     */
    public static String arr2Str(String[][] arr) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr[0].length; i++) {
            if (builder.length() > 0) builder.append(" ");
            builder.append(arr[0][i]).append("/").append(arr[1][i]);
        }
        return builder.toString();
    }

    /**
     * Str 2 arr string [ ] [ ].
     *
     * @param str the str
     * @return the string [ ] [ ]
     */
    public static String[][] str2Arr(String str) {
        String[] strs = str.split(" ");
        String[][] arrs = new String[2][strs.length];
        for (int i = 0; i < strs.length; i++) {
            String[] item = strs[i].split("/");
            arrs[0][i] = item[0];
            arrs[1][i] = item[1];
        }
        return arrs;
    }
}
