package com.ligeng.controller;

import com.ligeng.entity.nochange.Mytb2;
import com.ligeng.mapper.Mytb2Mapper;
import com.ligeng.service.MyService;
import com.ligeng.test.DatePropertyEditor;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 16-4-13.
 */
public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {

//        ClassPathResource resource = new ClassPathResource("spring-context.xml");
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        reader.loadBeanDefinitions(resource);
//
//        //
//        PropertyPlaceholderConfigurer propertyPostProcessor = new PropertyPlaceholderConfigurer();
//        propertyPostProcessor.setLocation(new ClassPathResource("properties/jdbc.properties"));
//        propertyPostProcessor.postProcessBeanFactory(factory);
//        //
//        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
//        Map map = new HashMap();
//        map.put(java.util.Date.class,DatePropertyEditor.class);
//        customEditorConfigurer.setCustomEditors(map);
//        customEditorConfigurer.postProcessBeanFactory(factory);
//
//        MyService service = (MyService)factory.getBean("myService2");
//        System.out.println(service.getName());
//
//        System.out.println(Arrays.toString(service.getAge()));


        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        final Mytb2Mapper mapper = (Mytb2Mapper)ctx.getBean(Mytb2Mapper.class);

        Integer count = 1000;
        ExecutorService exec = Executors.newFixedThreadPool(100);
        for (int i=0; i<count; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    mapper.updateStock();
                }
            });
        }

        TimeUnit.MILLISECONDS.sleep(30000);
        exec.shutdown();


    }

}

