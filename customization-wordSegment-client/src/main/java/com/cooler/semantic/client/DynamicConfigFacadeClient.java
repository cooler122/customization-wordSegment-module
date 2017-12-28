package com.cooler.semantic.client;

import com.cooler.semantic.facade.CustomizedConfigureFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DynamicConfigFacadeClient {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext-consumer.xml");
    private static CustomizedConfigureFacade customizedConfigureFacade = (CustomizedConfigureFacade)context.getBean("customizedConfigureFacade", CustomizedConfigureFacade.class);
    private static Logger logger = LoggerFactory.getLogger(DynamicConfigFacadeClient.class.getName());

    private static String apiKey = "fff504e39436430da5ed50cf0da3ce45";

    public static void main(String[] args) throws IOException {

        logger.info("客户端，开始访问...");
        boolean addResult = customizedConfigureFacade.addCustomizedWord(apiKey, "我的天空");
        System.out.println(addResult);

        boolean deleteResult = customizedConfigureFacade.deleteCustomizedWord(apiKey, "北京天气");
        System.out.println(deleteResult);
    }
}
