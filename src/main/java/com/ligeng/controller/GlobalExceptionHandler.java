package com.ligeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by dev on 16-4-27.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler
    @ResponseBody
    public String handle(HttpServletRequest request, HttpServletResponse response, Exception ex) {
//        System.out.println(messageSource.getMessage("1001",null,"Default",null));
        ex.printStackTrace();
        return "fetch exception";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        binder.registerCustomEditor(Date.class,new CustomDateEditor(format,false));
//        new SimpleDateFormat()
    }

    @ModelAttribute
    public void testModelAttr(Model model){
        model.addAttribute("auth","admin");
    }

}
