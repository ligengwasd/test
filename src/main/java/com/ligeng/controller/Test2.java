package com.ligeng.controller;

import com.ligeng.service.IMailService;
import com.ligeng.service.MyService;
import com.ligeng.utils.YuxiaorUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dev on 16-5-21.
 */
public class Test2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("spring-context.xml");
//
//        MyService service = (MyService)factory.getBean("myService2");
//        System.out.println(service.getName());

//        System.out.println(Arrays.toString(service.getAge()));

//        Resource re= new ClassPathResource("spring-context.xml");
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        reader.loadBeanDefinitions(re);
////        factory.getBean("")
//        System.out.println();
        IMailService mailService = (IMailService) factory.getBean(IMailService.class);
        File file = new File("/home/dev/temp/1.txt");
//        File[] files =new  File[1];files[0] = file;
        Boolean res = mailService.sendMailWithAttachment("主题", "内容", new File[]{file}, new String[]{"914076530@qq.com"});
        System.out.println(res);

        factory.close();
    }

}
