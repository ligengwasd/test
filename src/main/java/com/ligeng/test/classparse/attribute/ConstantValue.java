package com.ligeng.test.classparse.attribute;

import com.ligeng.test.classparse.Constant;
import lombok.Data;

import java.util.List;

/**
 * Created by dev on 16-7-2.
 */
@Data
public class ConstantValue extends Attribute{
    private Short constantValueIndex;

    public  ConstantValue(Short nameIndex,Integer length,Short constantValueIndex){
        super(nameIndex, length);
        this.constantValueIndex = constantValueIndex;
    }

    public String getConstantValue(List<Constant> constantPool){
        List<Short> indexList = (List<Short>)constantPool.get(constantValueIndex).getValue();
        return constantPool.get(indexList.get(0)).getValue().toString();
    }
}
