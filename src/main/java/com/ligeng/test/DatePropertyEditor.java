package com.ligeng.test;

import org.springframework.format.annotation.DateTimeFormat;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dev on 16-5-21.
 */
public class DatePropertyEditor extends PropertyEditorSupport {
    public void setAsText(String text){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            setValue(format.parse(text));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
