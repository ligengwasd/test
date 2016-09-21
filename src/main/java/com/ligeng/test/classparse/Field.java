package com.ligeng.test.classparse;

import com.ligeng.test.classparse.attribute.Attribute;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dev on 16-7-1.
 */
@Data
public class Field {
    private Short accessFlags;
    private Short nameIndex;
    private Short descIndex;
    private Short attrCount;
    private List<Attribute> attrList;

    private String access = "";

    public Field(Short accessFlags,Short nameIndex,Short descIndex,Short attrCount){
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descIndex = descIndex;
        this.attrCount = attrCount;

        for (Map.Entry<Integer,String> entry : FIELD_ACCESS_FLAGS_MAP.entrySet()){
            if ((this.accessFlags & entry.getKey())>0){
                access += getAccessStr(entry.getValue())+" ";
            }
        }
    }

    public String toString(List<Constant> constantPool){
        return this.getAccess()+" "+this.getDesc(constantPool)+" "+this.getName(constantPool);
    }

    public String getName(List<Constant> constantPool){
        return constantPool.get(this.nameIndex).getValue().toString();
    }

    public String getDesc(List<Constant> constantPool){
        String desc;
        String constantValue =  constantPool.get(this.descIndex).getValue().toString();
        if (constantValue.startsWith("L")){
            desc = constantValue.substring(1,constantValue.length()).substring(0,constantValue.length()-2).replace('/','.');
        }else {
            desc =  DESC_MAP.get(constantValue);
        }
        return desc;
    }


    final static Map<Integer,String> FIELD_ACCESS_FLAGS_MAP = new HashMap<Integer, String>();
    final static Map<String,String>  DESC_MAP = new HashMap<String, String>(); // 描述符
    static {
        FIELD_ACCESS_FLAGS_MAP.put(1, "ACC_PUBLIC");
        FIELD_ACCESS_FLAGS_MAP.put(2, "ACC_PRIVATE");
        FIELD_ACCESS_FLAGS_MAP.put(4, "ACC_PROTECTED");
        FIELD_ACCESS_FLAGS_MAP.put(8, "ACC_STATIC");
        FIELD_ACCESS_FLAGS_MAP.put(1<<4, "ACC_FINAL");
        FIELD_ACCESS_FLAGS_MAP.put(4*1<<4, "ACC_VOLATILE");
        FIELD_ACCESS_FLAGS_MAP.put(8*1<<4, "ACC_TRANSIENT");
        FIELD_ACCESS_FLAGS_MAP.put(1<<12, "ACC_SYNTHETIC");
        FIELD_ACCESS_FLAGS_MAP.put(4*1<<12, "ACC_ENUM");

        DESC_MAP.put("B","byte");
        DESC_MAP.put("C","char");
        DESC_MAP.put("D","double");
        DESC_MAP.put("F","float");
        DESC_MAP.put("I","int");
        DESC_MAP.put("J","long");
        DESC_MAP.put("S","short");
        DESC_MAP.put("Z","boolean");
        DESC_MAP.put("V","void");
    }

    private static String getAccessStr(String flag){
        if (flag.startsWith("ACC_")){
            return flag.substring(4,flag.length()).toLowerCase();
        }else {
            return flag.toLowerCase();
        }
    }

}
