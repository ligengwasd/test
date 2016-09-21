package com.ligeng.test;

import com.ligeng.entity.nochange.Mytb2;
import com.ligeng.mapper.Mytb2Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Date;

/**
 * Created by dev on 16-4-6.
 */
public class TestMyBatis {
    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    public static void main(String[] args) {
        testAdd();
//        getUser();
    }

    public static void testAdd() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            User user = new User("lisi", new Integer(25));
//            userMapper.insertUser(user);
//            sqlSession.commit();// 这里一定要提交，不然数据进不去数据库中
//        } finally {
//            sqlSession.close();
//        }
        try{
            Mytb2Mapper mapper = sqlSession.getMapper(Mytb2Mapper.class);
            Mytb2 mytb2 = new Mytb2();
            mytb2.setName("ligeng3");
            mytb2.setWeight(60);
            mytb2.setCompanyName("寓小二");
            mytb2.setBirth(new Date());
//            mytb2.setSex("Y");
            mapper.insert(mytb2);
            sqlSession.commit();

        }finally {

            sqlSession.close();

        }

    }

    public static void getUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {

        } finally {
            sqlSession.close();
        }
    }
}
