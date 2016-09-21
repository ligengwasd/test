 package com.ligeng.test.staticadvisor;

import lombok.Data;

 /**
  * Created by dev on 16-4-13.
  */
 @Data
 public class Customer{
     private String name;
     private String age;



 //    public void setName(String name){
 //        System.out.println("setter");
 //        this.name = name;
 //    }
 //    public Customer(){
 //        System.out.println("constructor");
 //    }
 //    @PostConstruct
 //    private void init(){
 //        System.out.println("init");
 //    }
 //    @PreDestroy
 //    private void destroy(){
 //        System.out.println("destroy");
 //    }
 }
