package com.ligeng.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

/**
 * Created by dev on 16-5-27.
 */
@Configuration
public class RedisScriptFactory {

    @Bean(name = "tryGetHongBaoScript")
    public RedisScript tryGetHongBaoScript(){
        DefaultRedisScript<String> script = new DefaultRedisScript<String>();
        script.setLocation(new FileSystemResource("/home/dev/projects/test/src/main/resources/scripts/tryGetHongBao.lua"));
        script.setResultType(String.class);
        return script;
    }
}
