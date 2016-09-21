package com.ligeng.test.ioc;

import com.ligeng.test.ioc.replace.MyValueCalculator;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;

/**
 * Created by dev on 16-4-23.
 */
public class Test {
    public static void main(String[]
                                    args) throws IOException {
//        System.out.printf("sss");
//        BeanDefinition

//        ListableBeanFactory
//        HierarchicalBeanFactory
//        AutowireCapableBeanFactory
//        ConfigurableBeanFactory

//        MessageSource
//        ResourceLoader
//        ApplicationEventPublisher

//        DefaultListableBeanFactory
//        ConfigurableBeanFactory factory = new Classpab

//        ApplicationContextAware
//        BeanPostProcessor
//  ApplicationContextAwarePro
//  BeanDefinition

        FileSystemXmlApplicationContext factory = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
//        factory.addBeanFactoryPostProcessor(new MyFactoryPostProcessor());
//        Customer customer = (Customer)factory.getBean("customer");
//        System.out.println(customer.getName());
        MyValueCalculator calculator = (MyValueCalculator)factory.getBean("myValueCalculator");
        System.out.println(calculator.computeValue("sss"));

        System.out.println("sssssssss");
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
////        factory.addBeanPostProcessor(new InstantiationTracingBeanPostProcessor());
//        ClassPathResource resource = new ClassPathResource("spring-context2.xml");
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        reader.loadBeanDefinitions(resource);
//
//        Customer customer = (Customer)factory.getBean("customer");
//
//        System.out.println(customer.getName());


//        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring-context.xml"));
//        PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
//        cfg.setLocation(new ClassPathResource("users.properties"));
//        cfg.postProcessBeanFactory(factory);
//
//        Customer customer = (Customer)factory.getBean("customer");
//        System.out.println(customer.getName());



    }

}
