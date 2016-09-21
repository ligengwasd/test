package com.ligeng.service.dubbo.api;

/**
 * Created by dev on 16-8-21.
 */
public class DemoServiceStub implements DemoService {
    private final DemoService demoService;

    public DemoServiceStub(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name) {
        System.out.println("DemoServiceStub开始执行");
        if (!"ligeng".equals(name)) {
            return "name 必须等于'ligeng'";
        }
        try{
            return demoService.sayHello(name);
        }catch (Exception e){
            return "出现异常";
        }
    }
}
