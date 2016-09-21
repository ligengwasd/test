package com.ligeng.test.jdk;

/**
 * Created by dev on 16-7-1.
 */
public class Test2 {
    public static void main(String[] args){
//        System.out.println(Demo.id);
//        System.out.println(Demo.age);
//        Demo demo = new Demo();
//        Demo demo2 = new Demo();
//        SuperClass[] demos = new SuperClass[10];
        System.out.println(SubClass.age);
    }
}
class SuperClass {
    final static int id = 1;
    static int age = 3;
    static {
        System.out.println("SuperClass 初始化");
    }

    SuperClass(){
        System.out.println("构造器");
    }
}
class SubClass extends SuperClass{
    static {
        System.out.println("SubClass 初始化");
    }
    {

    }
}
