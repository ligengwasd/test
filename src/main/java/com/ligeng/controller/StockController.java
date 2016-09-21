package com.ligeng.controller;

import com.ligeng.mapper.Mytb2Mapper;
import com.ligeng.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 16-5-26.
 */

@Controller
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private MyService myService;

    @RequestMapping("/decr")
    @ResponseBody
    public void decr() throws InterruptedException {
        Integer res = myService.updateStock();
        System.out.println(res);
    }

    @RequestMapping("/test")
    public String test(){
        return "stock";
    }


}
