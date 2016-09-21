package com.dubbo;

//import com.alibaba.dubbo.rpc.RpcContext;
import com.ligeng.service.dubbo.api.AsyncService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Future;

/**
 * Created by dev on 16-8-18.
 */
public class AsycServiceTest {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubboxml/consumer.xml");
        context.start();

//        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
//        String hello = demoService.sayHello("world111111"); // 执行远程方法
//        System.out.println( hello ); // 显示调用结果
//
//        System.out.println(RpcContext.getContext().getUrl().getParameter("registry"));

//        AsyncService asyncService = (AsyncService)context.getBean("asyncService");
//        asyncService.findPersonList(3);
//        Future<Integer> future1 = RpcContext.getContext().getFuture();
//
//        asyncService.findPersonList(4);
//        Future<Integer> future2 = RpcContext.getContext().getFuture();
//
//        System.out.println(System.currentTimeMillis());
//        int res1 = future1.get();
//        System.out.println(System.currentTimeMillis());
//        int res2 = future2.get();
//        System.out.println(System.currentTimeMillis());
//
//        System.out.println(res1+" "+res2);

    }
}
