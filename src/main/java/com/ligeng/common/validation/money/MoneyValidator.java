package com.ligeng.common.validation.money;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by dev on 16-4-12.
 */
public class MoneyValidator implements ConstraintValidator<Money,Double>{

    private String moneyReg = "^\\d+(\\.\\d{1,2})?$";//表示金额的正则表达式
    private Pattern moneyPattern = Pattern.compile(moneyReg);
    private Money money;
    @Override
    public void initialize(Money money) {
        this.money = money;
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null){
            return true;
        }
        return moneyPattern.matcher(value.toString()).matches();
    }
}
