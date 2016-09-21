package com.ligeng.test.classparse;

import lombok.Data;

import java.util.List;

/**
 * Created by dev on 16-7-1.
 */
@Data
public class Constant {
    private Integer tag;
    private Object value;// 可能的值  List<SHort>,Integer,Long,Double,Float
    private String desc;

    public Constant(Integer tag, Object value){
        this.tag = tag;
        this.value = value;
        switch (this.tag) {
            case 1:
                this.desc = "Utf8";
                break;
            case 3:
                this.desc = "Integer";
                break;
            case 7:
                this.desc = "Class";
                break;
            case 8:
                this.desc = "String";
                break;
            case 9:
                this.desc = "Fieldref";
                break;
            case 10:
                this.desc = "Methodref";
                break;
            case 12:
                this.desc = "NameAndType";
                break;
            default:
                throw new RuntimeException("错误类型 tag = " + tag);
        }
    }

    public static void print(List<Constant> list){
        for (int i=0; i<list.size(); i++){
            if (i==0){
                continue;
            }
            String str= "";
            if (list.get(i).getValue() instanceof List){
                List<Short> indexs = (List<Short>) list.get(i).getValue();
                if (indexs != null && indexs.size()>0){
                    for (Short index: indexs){
                        str += "#"+index.toString()+",";
                    }
                }
            }else {
                str = list.get(i).getValue().toString();
            }
            System.out.printf(" %-20s  %s",("#"+i)+" "+ list.get(i).desc,str);
            System.out.println();
        }
    }

//    public
}
