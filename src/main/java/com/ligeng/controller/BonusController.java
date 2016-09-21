package com.ligeng.controller;

import com.ligeng.common.redis.RedisAPI;
import com.ligeng.utils.UUIDTools;
import com.ligeng.utils.YuxiaorUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 16-5-26.
 */
@Controller()
@RequestMapping("/bonus")
public class BonusController {
    @Autowired
    private RedisAPI redisAPI;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisScript<String> tryGetHongBaoScript;
    @Autowired
    RedisScript defaultRedisScript;

    @RequestMapping("/getbonus")
    @ResponseBody
    public void testTryGetHongBao(){
        String hongBaoList = "hongBaoList";
        String hongBaoConsumedList = "hongBaoConsumedList";
        String hongBaoConsumedMap = "hongBaoConsumedMap";
        if(redisAPI.llen(hongBaoList) == 0){
            System.out.println("红包已取完");
            return;
        }

        List<String> keys = new ArrayList<String>();
        keys.add(hongBaoList);
        keys.add(hongBaoConsumedList);
        keys.add(hongBaoConsumedMap);
        keys.add(UUIDTools.getUUidStr());

        String res = stringRedisTemplate.execute(tryGetHongBaoScript, keys);
        System.out.println(res);
    }

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        final RedisTemplate redisTemplate = (RedisTemplate)ctx.getBean("stringRedisTemplate");

        ListOperations<String,String> listOperations = redisTemplate.opsForList();
        Bonus bonus = null;
        for (int i=0; i<1000; i++){
            bonus = new Bonus();
            bonus.setId(i);
            bonus.setMoney(i);
            listOperations.rightPush("hongBaoList", YuxiaorUtils.objectToJson(bonus));
        }

    }
}

@Data
class Bonus implements Serializable{
    private static final long serialVersionUID = -1L;
    Integer id;
    Integer money;
    String userId;
}
