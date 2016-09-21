package com.ligeng.test.aoptest;

import com.ligeng.common.validation.Person;
import org.springframework.stereotype.Service;

/**
 * Created by dev on 16-4-7.
 */
@Service("myService")
//@IsAfter
public class MyService {
    @IsAfter
    public void service(int age){
        System.out.println("service1111");
    }
    public void service2(){
        System.out.println("service2222");
    }


    public void service3(Person person){
        System.out.println("service3333");
    }

}
