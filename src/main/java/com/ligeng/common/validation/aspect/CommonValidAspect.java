package com.ligeng.common.validation.aspect;

import com.ligeng.model.rep.BaseRepModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by dev on 16-5-24.
 */

@Component
@Aspect
public class CommonValidAspect {

    @Around("execution(* com.ligeng.controller.*.*(..,@javax.validation.Valid (*), org.springframework.validation.BindingResult,..))")
    public BaseRepModel doValid(ProceedingJoinPoint pjp) throws Throwable {
        BaseRepModel repModel = new BaseRepModel();
        repModel.setCode(-99);

        Object[] args = pjp.getArgs();
        BindingResult result = null;
        for (Object arg : args){
            if (arg instanceof BindingResult){
                result = (BindingResult) arg;
            }
        }
        if (result != null && result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error:list){
                repModel.setCode(200);
                repModel.setMsg(error.getDefaultMessage());
                return repModel;
            }
        }
        return (BaseRepModel)pjp.proceed();
    }
}
