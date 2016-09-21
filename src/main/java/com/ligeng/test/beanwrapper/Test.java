package com.ligeng.test.beanwrapper;


import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Created by dev on 16-4-13.
 */
public class Test {
    public static void main(String[] args){
        Employee employee = new Employee();
        BeanWrapper companyBeanWrapper = new BeanWrapperImpl(employee);
//        companyBeanWrapper.
        companyBeanWrapper.setPropertyValue("age", "1223");

        System.out.println(employee.getAge());

    }


}
