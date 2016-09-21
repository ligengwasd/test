package com.ligeng.service.dubbo.impl.provider;

import com.ligeng.service.dubbo.api.DemoService;
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 16-8-18.
 */
public class DemoServiceImpl implements DemoService{
    public String sayHello(String name) {
//        throw new RpcException("ssss");
        System.out.println("DemoServiceImpl is running，"+Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name;
    }
}