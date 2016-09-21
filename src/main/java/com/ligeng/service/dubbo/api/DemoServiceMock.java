package com.ligeng.service.dubbo.api;

/**
 * Created by dev on 16-8-22.
 */
public class DemoServiceMock implements DemoService{
    @Override
    public String sayHello(String name) {
        System.out.println("DemoServiceMock is running");
        return "rpc exception";
    }
}
