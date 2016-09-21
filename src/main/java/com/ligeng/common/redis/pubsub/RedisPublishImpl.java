package com.ligeng.common.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by dev on 16-5-28.
 */

@Repository
public class RedisPublishImpl implements RedisPublish{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }
}
