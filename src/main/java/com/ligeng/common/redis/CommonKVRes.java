package com.ligeng.common.redis;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by thinkpad on 2015/9/1.
 */
@Data
public abstract class CommonKVRes implements Serializable{
    private String  id;
    private String  name;
}
