package com.ligeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dev on 16-5-9.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/logining")
    public String logining(){
        return "index";
    }


}
