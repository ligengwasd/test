package com.ligeng.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.ClassPathResource;


/**
 * Created by dev on 16-4-6.
 */
public class MyBatisUtil {
//    private final static SqlSessionFactory sqlSessionFactory;
    static {
//        String resource = "/home/dev/projects/test/src/main/resources/mybatis-config.xml";
//        Reader reader = null;
//        InputStream inputStream = null;
//        try {
//            reader = Resources.getResourceAsReader(resource);
//            inputStream = new FileInputStream(resource);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);




    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return null;
    }

    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.openSession();
        System.out.println(1);
    }
}
