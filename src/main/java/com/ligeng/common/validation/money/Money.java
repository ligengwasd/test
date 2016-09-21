package com.ligeng.common.validation.money;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dev on 16-4-12.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=MoneyValidator.class)
public @interface Money {
//    boolean allowNull() default true;
    String message() default "不是金额形式";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
