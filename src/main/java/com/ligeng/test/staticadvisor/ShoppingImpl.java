package com.ligeng.test.staticadvisor;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by dev on 16-4-13.
 */
@Service("shoppingImpl")
public class ShoppingImpl implements Shopping {

    private Customer customer;

    @Bean(name = "customerInner")
//    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Customer createCustomer(){
        return new Customer(){{
            setName("sssss");
        }};
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String buySomething(String type) {
        System.out.println(this.getCustomer().getName()+" bye "+type+" success");
        return null;
    }

    public String buyAnything(String type) {
        System.out.println(this.getCustomer().getName()+" bye "+type+" success");
        return null;
    }
    public String sellAnything(String type) {
        System.out.println(this.getCustomer().getName()+" sell "+type+" success");
        return null;
    }
    public String sellSomething(String type) {
        System.out.println(this.getCustomer().getName()+" sell "+type+" success");
        return null;
    }

    public void test(){
        System.out.println("1");
    }


}

