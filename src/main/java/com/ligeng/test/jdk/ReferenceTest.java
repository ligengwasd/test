package com.ligeng.test.jdk;

/**
 * Created by dev on 16-6-25.
 */
public class ReferenceTest {
    public Object instance = null;
    private static final int _1MB = 1024*1024;
    private byte[] bigSize = new byte[2*_1MB];
    public static void main(String[] args) {
        ReferenceTest a = new ReferenceTest();
        ReferenceTest b = new ReferenceTest();
        a.instance = b;
        b.instance = a;

        a = null;
        b = null;
        System.gc();
    }
}
