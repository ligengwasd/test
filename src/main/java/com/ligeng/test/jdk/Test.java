package com.ligeng.test.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 16-4-6.
 */
public final class Test {
    private static final int _1MB = 1024*1024;
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10000000);
        testTenuringThreshold();
    }

    // -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
    static void  testBig(){
        byte[] a = new byte[5*_1MB];
    }

    //
    static void testTenuringThreshold(){
        byte[] a1,a2,a3,a4;
        a1 = new byte[_1MB/4];
        a2 = new byte[4*_1MB];
        a3 = null;
        a3 = new byte[4*_1MB];
    }

    // -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
    static void testAllocation(){
//        byte[] a1,a2,a3,a4;
//        a1 = new byte[2*_1MB];
//        a2 = new byte[2*_1MB];
//        a3 = new byte[2*_1MB];
//        a4 = new byte[4*_1MB];
    }


//    static void testHeap(){
//        System.out.println(2);
//        List<OOMObject> list = new ArrayList<OOMObject>();
//        while (true){
//            list.add(new OOMObject());
//        }
//    }
//
//    static class OOMObject{
//
//    }


}
