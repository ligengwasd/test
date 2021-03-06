package com.ligeng.common.validation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by dev on 16-4-13.
 */

//@Component()
//@Aspect
public class ValidAspect {

//    @Around("execution(* com.ligeng.controller.*.*(..))")
    public Object doTest(ProceedingJoinPoint pjp) throws Throwable{
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        Method method = signature.getMethod();

        Object[] args = pjp.getArgs();
        Annotation[][] annotations = method.getParameterAnnotations();
        for(int i = 0; i < annotations.length; i++){
            if(!hasValidAnnotation(annotations[i])){
                continue;
            }
            if(!(i < annotations.length-1 && args[i+1] instanceof BindingResult)){
                //验证对象后面没有跟bindingResult,事实上如果没有应该到不了这一步
                continue;
            }
            BindingResult result = (BindingResult) args[i+1];
            if(result.hasErrors()){
//                AjaxResponse ajaxResponse = new AjaxResponse();
//                ajaxResponse.setSuccess(false);
//                ajaxResponse.setHasErrors(true);
//                ajaxResponse.setData(processErrors(result));
//                return ajaxResponse;
                List<ObjectError> list = result.getAllErrors();
                for (ObjectError error:list){
                    return (error.getDefaultMessage());
                }
            }
        }
        return pjp.proceed();
    }
    /*
    private List<BindingError> processErrors(BindingResult result){
        if(result != null && result.hasErrors()){
            List<BindingError> list = new ArrayList<BindingError>();
            for(ObjectError error : result.getAllErrors()){
                BindingError be = new BindingError();
                be.setMessage(error.getDefaultMessage());
                be.setName(error.getObjectName());
                list.add(be);
            }
            return list;
        }
        return null;
    }
    */

    private boolean hasValidAnnotation(Annotation[] annotations){
        if(annotations == null){
            return false;
        }
        for(Annotation annotation : annotations){
            if(annotation instanceof Valid){
                return true;
            }
        }
        return false;
    }
}
