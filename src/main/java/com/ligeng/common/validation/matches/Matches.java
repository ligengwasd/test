package com.ligeng.common.validation.matches;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by dev on 16-4-12.
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchesValidator.class)
@Documented
public  @interface Matches {
    String message() default "{constraint.not.matches}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String field();
    String verifyField();
}
