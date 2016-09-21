package com.ligeng.controller;

import com.ligeng.utils.YuxiaorUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 16-5-25.
 */
public class Test3 {


    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//        ClassPathResource resource = new ClassPathResource("AuthController.java");
//        System.out.println(resource.getURL());
//
        JedisShardInfo jedis700 = new JedisShardInfo("127.0.0.1",7000,"redis-7000");
        JedisShardInfo jedis701 = new JedisShardInfo("127.0.0.1",7001,"redis-7001");

        List<JedisShardInfo> shardInfoList = new ArrayList<JedisShardInfo>();
        shardInfoList.add(jedis700);
        shardInfoList.add(jedis701);

        ShardedJedisPool sharedJedisPool = new ShardedJedisPool(new JedisPoolConfig(),shardInfoList);

        ShardedJedis shardedJedis = sharedJedisPool.getResource();

        shardedJedis.set("msg","hello111111");
        shardedJedis.get("msg");
    }
}
