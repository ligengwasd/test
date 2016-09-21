package com.ligeng.controller;

import com.ligeng.common.validation.Person;
import com.ligeng.model.rep.BaseRepModel;
import com.ligeng.test.aoptest.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by dev on 16-4-11.
 */
@Controller()
@Service
@RequestMapping("/validate")
public class ValidatorController {

//    @Autowired
    private MyService myService;

    @RequestMapping(value = "/main",produces ="application/json;charset=UTF-8",method = RequestMethod.POST)
    @ResponseBody
    public BaseRepModel index(@Valid @RequestBody Person p,BindingResult result){
        BaseRepModel repModel = new BaseRepModel();
        repModel.setCode(100);
        repModel.setData(p);
        return repModel;
    }
}
