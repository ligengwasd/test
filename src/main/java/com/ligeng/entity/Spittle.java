package com.ligeng.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by dev on 16-8-17.
 */
@Data
public class Spittle implements Serializable{
    private String msg;
    public Spittle(String msg){
        this.msg = msg;
    }
}
