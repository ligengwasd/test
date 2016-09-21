package com.ligeng.test.classparse.attribute;

import com.ligeng.test.classparse.Constant;
import lombok.Data;

import java.util.List;

/**
 * Created by dev on 16-7-1.
 */
@Data
public class Attribute {
    private Short nameIndex;
    private Integer length;

    public Attribute(Short nameIndex,Integer length){
        this.nameIndex = nameIndex;
        this.length = length;
    }

    public String getName(List<Constant> constantPool){
        return constantPool.get(nameIndex).getValue().toString();
    }
}
