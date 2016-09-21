package com.ligeng.test.rmi;

import java.rmi.Naming;

/**
 * Created by dev on 16-8-20.
 */
public class Client {
    public static void main(String[] args) {
        try {
//            GreetService greetService = (GreetService) Naming.lookup("rmi://10.108.1.138:1098/GreetService");
            GreetService greetService = (GreetService) Naming.lookup("rmi://127.0.0.1:1098/GreetService");
            System.out.println(greetService.sayHello("Jobs"));
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
