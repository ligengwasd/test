package com.ligeng.test.aoptest;

import org.springframework.stereotype.Service;

/**
 * Created by dev on 16-4-15.
 */
@Service("myService2")
//@IsAfter

public class MyService2 {
//    @Autowired()
//    @Scope

    public void service(int age){
        System.out.println("sss");
    }
}
