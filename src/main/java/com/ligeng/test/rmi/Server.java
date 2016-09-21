package com.ligeng.test.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by dev on 16-8-20.
 */
public class Server {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1098);
//            LocateRegistry.getRegistry("127.0.0.1");
//            Naming.bind("rmi://10.108.1.138:1098/GreetService", new GreetServiceImpl());
//            Naming.bind("rmi://127.0.0.1:1098/GreetService", new GreetServiceImpl());
            Naming.bind("rmi://127.0.0.1:1098/GreetService", new GreetServiceImpl());
//            Naming.rebind("greet", new GreetServiceImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
