package com.ligeng.test.redis;

import com.ligeng.common.validation.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.*;

/**
 * Created by dev on 16-4-22.
 */
public class RedidsTest {
    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        final RedisTemplate redisTemplate = (RedisTemplate)ctx.getBean("redisTemplate");

        final Person person = new Person();
        person.setName("ligeng1");

        ListOperations<String,Person> listOperations = redisTemplate.opsForList();
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Person>(Person.class));
        listOperations.rightPush("plist",person);
//        Person p = listOperations.leftPop("ligeng");
//        System.out.println(p);
//        System.out.printf(listOperations.range("ligeng", 0, -1).toString());
//
//
//        ListOperations<String,Person> listOperations2 = redisTemplate.opsForList();
//        listOperations2.rightPush("ligeng",person);

//        List<Object> res = (List<Object>) redisTemplate.execute(new SessionCallback<List<Object>>() {
//            @Override
//            public List<Object>  execute(RedisOperations operations) throws DataAccessException {
//                operations.multi();
//                operations.opsForValue().set("name1", "name11");
//                operations.opsForValue().set("name2", "name21");
//                operations.opsForValue().set("name3", "name33");
//                operations.opsForHash().put("nam1","11","11");
//                operations.opsForHash().put("nam2","11","11");
//                return operations.exec();
//            }
//        });
//        System.out.println(res);


    }
}
