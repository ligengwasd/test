package com.ligeng.controller;

import com.ligeng.common.redis.pubsub.RedisPublish;
import com.ligeng.common.redis.pubsub.RedisPublishImpl;
import com.ligeng.common.validation.Person;
import com.ligeng.service.EmailService;
import com.ligeng.common.viewresolver.DemoObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dev on 16-4-11.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private EmailService service;
    @Autowired
    private RedisPublish redisPublish;

    @RequestMapping(value = "/demo")
    public ModelAndView index2(Person person,@ModelAttribute("msg") String msg,@ModelAttribute("auth") String auth){
        System.out.println("/index/demo");
        service.testMapper();
        return new ModelAndView("index");
    }

//    produces = "application/json; charset=utf-8"
    @RequestMapping(value = "/main",method = RequestMethod.POST)
    @ResponseBody
    public List<Person> index(HttpServletRequest request,@RequestBody List<Person> person){
        System.out.println("/index/main");
        String msg = "Hello, Redis!";
        redisPublish.sendMessage("java", msg); //发布字符串消息
        Integer[] values = new Integer[]{21341,123123,12323};
        redisPublish.sendMessage("java", values);  //发布一个数组消息
        return person;
    }

    //test jsonView
    @RequestMapping(value = "/jsonView",method = RequestMethod.GET)
    @ResponseBody
    public Person testJsonView(){
        Person person = new Person();
        person.setName("ligeng");
        person.setId("100");
        person.setPwd("1243546");
        return person;
    }

    @RequestMapping(value = "redirect")
    public String redirect(){
        return "forward:/index/demo";
    }

    @RequestMapping(value = "/getdemo",method = RequestMethod.GET)
    public String getDemo(Model model){
        DemoObj demoObj = new DemoObj(333l, "WYF");
        model.addAttribute("demoObj",demoObj);
        return "demoObj";
    }




}
