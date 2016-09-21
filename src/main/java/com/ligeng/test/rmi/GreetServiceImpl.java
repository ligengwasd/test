package com.ligeng.test.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by dev on 16-8-20.
 */
public class GreetServiceImpl extends UnicastRemoteObject implements GreetService {
    protected GreetServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        System.out.println(name);
        return name+"2222";
    }
}
