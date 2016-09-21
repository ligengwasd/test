package com.ligeng.common.validation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dev on 16-4-11.
 */
@Data
//@CommonValidatorAnno
//@GroupSequenceProvider()
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Person implements Serializable{

    @NotBlank(message = "name can not be blank")
    @Size(max = 20,min = 3,message = "name's length must between 3 to 20")
    private String name;

    private String unitPrice;
    private String id;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private String pwd;

//    @AssertTrue(message = "两次密码不一致")
//    public boolean getCheckPassWord(){
//        if (StringUtils.isNotBlank(pwd) && pwd.equals(pwd2)){
//            return true;
//        }
//        return false;
//    }
//    private List<Integer> age;
    /*
    @Min(value = 10,message = "age must bigger than 10")
    @Max(value = 100,message = "age must smaller than 100")
    private int age;

    @Past(message = "birth must be past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;


    @NotNull(message = "salary can not be null")
    @Money(message = "salary pattern is wrong")
    private Double salary;*/

//    @NotBlank(message = "密码不能为空")
//    String pwd;
//    @NotBlank(message = "重复密码不能为空")
//    String pwd2;

//    @Override
//    public String getCkeck() {
//        return null;
//    }
//
//    public String test(){
//        System.out.print("sss");
////        if (StringUtils.isNotBlank(pwd) && !pwd.equals(pwd2)){
//        if (StringUtils.isNotBlank(name) && name.equals("111"))
//        {  return "密码不一致";
//        }
//        return null;
//    }
//    @DateTimeFormat


}
