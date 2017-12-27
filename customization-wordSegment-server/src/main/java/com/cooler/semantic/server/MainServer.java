package com.cooler.semantic.server;

import com.cooler.semantic.model.SemanticCache;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.utility.Word2VectorUtil;

import edu.fudan.nlp.Fudan;
import edu.fudan.nlp.cn.tag.POSTagger;
import edu.fudan.nlp.parser.dep.JointParser;

import edu.stanford.nlp.StanfordParser;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

public class MainServer {
    private static Logger log = LoggerFactory.getLogger(MainServer.class.getName());
    public static void main(String[] args) {
        init();
        com.alibaba.dubbo.container.Main.main(args);
    }

    private static void init(){
        log.info("初始化模型...");
        try {
            log.info("初始化 Hanlp 分词词典");
            new CoreDictionary();
            new CustomDictionary();
            log.info("初始化word2vec");
            new Word2VectorUtil();
            log.info("初始化 Hanlp 动态自定义词典");
            File dirFile = new File(HanLP.Config.DynamicPath);
            File[] files = dirFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        CustomDictionary.loadDynamicDict(file.getName());
                    }
                }
            }


            log.info("初始化fudan parser");
            Fudan.parser = new JointParser(Fudan.Config.DepModelPath);
            log.info("初始化fudan postag");
            Fudan.posTag = new POSTagger(Fudan.Config.PosModelPath);


            log.info("初始化StanfordParser");
            StanfordParser.lParser = LexicalizedParser.loadModel(StanfordParser.Config.chineseFactored);


            log.info("初始化缓存");
            new SemanticCache();

            log.info("初始化完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
