package com.ligeng.test.beanwrapper;

/**
 * Created by dev on 16-4-13.
 */
public class Company {
    private String name;
    private Employee managingDirector;

    public String getName()	{
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Employee getManagingDirector() {
        return this.managingDirector;
    }
    public void setManagingDirector(Employee managingDirector) {
        this.managingDirector = managingDirector;
    }
}
