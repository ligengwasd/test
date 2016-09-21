package com.ligeng.common.redis.pubsub;

import java.io.Serializable;

/**
 * Created by dev on 16-5-28.
 */
public interface RedisPublish {
    void sendMessage(String channel, Serializable message);
}
