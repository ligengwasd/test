package com.ligeng.test.jdk;

/**
 * Created by dev on 16-7-6.
 */
public class Overload {
    public static String say(Long i,String name){
        return name+i;
    }
    public static void sayHello(int arg){
        System.out.println(arg);
    }
    public static void sayHello(char arg){
        System.out.println(arg);
    }

    public static void main(String[] args){
        sayHello('a');
        sayHello(1);
        say(1L, "ssss");
    }


}
