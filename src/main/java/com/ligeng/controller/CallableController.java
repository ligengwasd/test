package com.ligeng.controller;

import com.ligeng.service.AysncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.view.RedirectView;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 16-5-5.
 */
@Controller
@RequestMapping("/call")
public class CallableController {

    @Autowired
    private AysncService aysncService;
//    @Autowired
//    private ExecutorService taskExecutor;

    @RequestMapping(value = "/body",method = RequestMethod.GET)
    @ResponseBody
    public Callable<String> callable(){


        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(5000);
                return "callable result";
            }
        };
    }

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall() {
        //调用aysncService的getAsyncUpdate方法
        //deferredResult被计划任务每五秒钟更新一次
        return aysncService.getAsyncUpdate();
    }
}
