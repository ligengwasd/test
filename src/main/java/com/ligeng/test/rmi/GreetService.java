package com.ligeng.test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by dev on 16-8-20.
 */
public interface GreetService extends Remote {
    String sayHello(String name) throws RemoteException;
}
