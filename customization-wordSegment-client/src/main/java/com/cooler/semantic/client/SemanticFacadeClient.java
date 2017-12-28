package com.cooler.semantic.client;

import com.alibaba.fastjson.JSON;
import com.cooler.semantic.facade.CustomizedSemanticFacade;
import com.cooler.semantic.model.SemanticInfo;
import com.cooler.semantic.enumeration.Channel;
import com.cooler.semantic.enumeration.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SemanticFacadeClient {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext-consumer.xml");
    private static CustomizedSemanticFacade customizedSemanticFacade = (CustomizedSemanticFacade)context.getBean("customizedSemanticFacade", CustomizedSemanticFacade.class);
    private static Logger logger = LoggerFactory.getLogger(SemanticFacadeClient.class.getName());
    private static String apiKey = "fff504e39436430da5ed50cf0da3ce45";

    public static void main(String[] args) throws IOException {

        logger.info("客户端，开始访问...");
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(Pattern.ALL);

//        解析基础语义信息1
        SemanticInfo semanticInfo1 = customizedSemanticFacade.parseSemantic("今天北京天气怎么样？", patternList);
        System.out.println("解析基础语义信息1 --> " + JSON.toJSONString(semanticInfo1));
//        解析基础语义信息2(自定义解析)
        SemanticInfo semanticInfo2 = customizedSemanticFacade.parseSemantic("今天北京天气怎么样？", patternList, Channel.CUSTOM, apiKey);
        System.out.println("解析基础语义信息2 --> " + JSON.toJSONString(semanticInfo2));

////        抽取关键词
        Map<String, Double> keyWordsMap = customizedSemanticFacade.extractKeyWords("北京有哪些景点不收门票？", Channel.CUSTOM, apiKey, 4);
        System.out.println("抽取关键词 --> " + JSON.toJSONString(keyWordsMap));
//
//        单个词的语义联想
        Map<String, Double> referenceWordMap = customizedSemanticFacade.wordReference("智能", 10);
        System.out.println(JSON.toJSONString(referenceWordMap));

//        计算两个词之间的相关度1
        Double wordsDistance1 = customizedSemanticFacade.wordsDistance("企业", "公司");
        System.out.println(wordsDistance1);

//        计算两个词之间的相关度2
        Map<String, Double> wordsDistance2 = customizedSemanticFacade.wordsDistance("北京", Arrays.asList("帝都", "京城", "皇城", "京都"));
        System.out.println(wordsDistance2);
    }
}
