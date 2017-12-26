package com.cooler.semantic.client;

import com.cooler.semantic.facade.WordSegmentFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;

public class WordSegmentFacadeClient {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext-consumer.xml");
    private static WordSegmentFacade wordSegmentFacade = (WordSegmentFacade)context.getBean("wordSegmentFacade", WordSegmentFacade.class);
    private static Logger logger = LoggerFactory.getLogger(WordSegmentFacadeClient.class.getName());

    public static void main(String[] args) throws IOException {

        logger.info("客户端，开始访问...");
        wordSegmentFacade.wordSegment("", 1, null, null, true);
    }
}
