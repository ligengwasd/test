package com.ligeng.common.redis.pubsub;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by dev on 16-5-28.
 */

@Component("messageDelegateListener")
public class MessageDelegateListenerImpl {
    public void handleMessage(Serializable message) {
        //什么都不做,只输出
        if(message == null){
            System.out.println("null");
        } else if(message.getClass().isArray()){
            System.out.println(Arrays.toString((Object[]) message));
        } else if(message instanceof List<?>) {
            System.out.println(message);
        } else if(message instanceof Map<? , ?>) {
            System.out.println(message);
        } else {
            System.out.println(ToStringBuilder.reflectionToString(message));
        }
    }
}
