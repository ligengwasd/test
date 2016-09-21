package com.ligeng.test.beanwrapper;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by dev on 16-4-13.
 */
@Data
public class Employee {
    private String name;
    private float salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private List<Integer> age;
    private Boolean isMarried;

}
