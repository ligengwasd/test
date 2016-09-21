package com.ligeng.controller;

import com.ligeng.entity.Spittle;
import com.ligeng.service.jms.IAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dev on 16-8-17.
 */
@Controller
public class JmsController {
    @Autowired
    private IAlertService alertService;

    @RequestMapping(value = "jms")
    @ResponseBody
    public void send(){
        alertService.sendSpitterAlert(new Spittle("11112343576"));
    }
}
