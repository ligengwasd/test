package com.ligeng.service;

import com.ligeng.mapper.Mytb2Mapper;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Created by dev on 16-5-20.
 */

@Service
@Data
public class MyService{

    @Autowired
    private Mytb2Mapper mytb2Mapper;


    private String name = "${user}";
    private String[] age;
    private Class clazz;
    private File file;
    private Pattern pattern;
    private Date date;

    @Transactional
    public int updateStock() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(50);
        return mytb2Mapper.updateStock();

    }

}
