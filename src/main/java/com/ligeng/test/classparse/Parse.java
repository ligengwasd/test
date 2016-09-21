package com.ligeng.test.classparse;

import com.ligeng.test.classparse.attribute.ConstantValue;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dev on 16-6-28.
 */
public class Parse {

    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("/home/dev/projects/test/src/main/java/com/ligeng/test/classparse/TestClass.class");
        FileChannel channel = in.getChannel();

        magic(channel);
        version(channel);
        constant(channel);
        classAccessFlags(channel);
        thisClass(channel);
        superClass(channel);
        interfaces(channel);
        fieldInfo(channel);
//        methodInfo(channel);
        System.out.println();
    }

    private static void magic(FileChannel channel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        channel.read(byteBuffer);
        byteBuffer.flip();
        byte[] magic = new byte[2];
        byteBuffer.get(magic);
        System.out.print("magic number: " + toHexString1(magic));
        byteBuffer.get(magic);
        System.out.println(" "+toHexString1(magic));
    }

    private static void version(FileChannel channel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        channel.read(byteBuffer);
        byteBuffer.flip();
        byte[] version = new byte[4];
        byteBuffer.get(version);
        System.out.println("version: "+String.valueOf((version[2] & 0xFF)+(version[3] & 0xFF))+"."+String.valueOf((version[0] & 0xFF) + (version[0] & 0xFF)+(version[1] & 0xFF)));
    }

    private static void constant(FileChannel channel) throws IOException {
        Short constantCount = ChannelUtil.getIndex(channel);
        System.out.println("constant pool count: "+constantCount);
        System.out.println("constant pool: ");
        for (Short i=1; i <= constantCount-1; i++){
            Integer tag = ChannelUtil.getTag(channel);
            ChannelUtil.swithTag(tag,i,channel);
        }
        Constant.print(CONSTANT_POOL);
    }

    private static void classAccessFlags(FileChannel channel) throws IOException {
        String accessFlags="";
        Short flags = ChannelUtil.getIndex(channel);
        for (Map.Entry<Integer,String> entry : CLASS_ACCESS_FLAGS_MAP.entrySet()){
            if ((flags & entry.getKey() ) > 0){
                accessFlags += entry.getValue()+", ";
            }
        }
        System.out.println("classAccessFlags: " + accessFlags);
    }

    private static void thisClass(FileChannel channel) throws IOException {
        Short thisClassIndex = ChannelUtil.getIndex(channel);
        System.out.println("thisClass: #"+thisClassIndex);
    }
    private static void superClass(FileChannel channel) throws IOException {
        Short thisClassIndex = ChannelUtil.getIndex(channel);
        System.out.println("superClass: #"+thisClassIndex);
    }

    private static void interfaces(FileChannel channel) throws IOException {
        Short count = ChannelUtil.getIndex(channel);
        System.out.println("interfaces count: "+count);
        System.out.print("interfaces: ");
        for (int i=0; i<count; i++){
            System.out.print("#"+ChannelUtil.getIndex(channel)+",");
        }
        System.out.println();
    }

    private static void fieldInfo(FileChannel channel) throws IOException {
        Short count = ChannelUtil.getIndex(channel);
        System.out.println("field count: "+ count);

        for (int i=0; i<3; i++){
            Short flags = ChannelUtil.getIndex(channel);
            Short nameIndex = ChannelUtil.getIndex(channel);
            Short descIndex = ChannelUtil.getIndex(channel);
            Short attrCount = ChannelUtil.getIndex(channel);
            Field field = new Field(flags,nameIndex,descIndex,attrCount);
            System.out.print("field" + (i + 1)+":  " +field.toString(CONSTANT_POOL));
            System.out.println(attrCount);
            if (attrCount >= 0){
                attrInfo(channel,attrCount);
            }
        }
    }

    private static void methodInfo(FileChannel channel) throws IOException {
        Short count = ChannelUtil.getIndex(channel);
        System.out.println("method count: "+ count);

        for (int i=0; i<count; i++){
//            String methodAccessFlag = "";
//            Short flags = ChannelUtil.getIndex(channel);
//            for (Map.Entry<Integer,String> entry : METHOD_ACCESS_FLAGS_MAP.entrySet()){
//                if ((flags & entry.getKey())>0){
//                   // methodAccessFlag += getAccessStr(entry.getValue())+" ";
//                }
//            }
//            System.out.print("method" + (i + 1) + ": access_flag=" + methodAccessFlag + ", ");
//            System.out.print("method_index=#" + ChannelUtil.getIndex(channel)+", ");
//            System.out.print("desc_index="+ChannelUtil.getIndex(channel)+", ");
//            Short attrCount = ChannelUtil.getIndex(channel);
//            System.out.print("attrCount="+attrCount+", ");
            Short flags = ChannelUtil.getIndex(channel);
            Short nameIndex = ChannelUtil.getIndex(channel);
            Short descIndex = ChannelUtil.getIndex(channel);
            Short attrCount = ChannelUtil.getIndex(channel);
            Field field = new Field(flags,nameIndex,descIndex,attrCount);
            System.out.print("method" + (i + 1)+":  " +field.toString(CONSTANT_POOL));
            if (attrCount >= 0){
                attrInfo(channel,attrCount);
            }
            System.out.println();
        }
    }

    private static void attrInfo(FileChannel channel,Short attrCount) throws IOException {
//        System.out.println();
        for (int j=0; j<attrCount; j++){
//            // attr_name_index
//            Short attrNameIndex = ChannelUtil.getIndex(channel);
//            System.out.println(CONSTANT_POOL.get(attrNameIndex).getValue());
//            // attr_length
//            ByteBuffer byteBuffer = ChannelUtil.getByteBuffer(channel,4);
//            Integer attrLength = byteBuffer.getInt();
//            // info todo
//            ByteBuffer byteBuffer2 = ChannelUtil.getByteBuffer(channel,attrLength);
            Short attrNameIndex = ChannelUtil.getIndex(channel);
//            ByteBuffer byteBuffer = ChannelUtil.getByteBuffer(channel,4);
            Integer attrLength = ChannelUtil.getByteBuffer(channel,4).getInt();
            ByteBuffer byteBuffer2 = ChannelUtil.getByteBuffer(channel,attrLength);
            ConstantValue constantValue = new ConstantValue(attrNameIndex,attrLength,byteBuffer2.getShort());
            System.out.println();
            System.out.println(constantValue.getName(CONSTANT_POOL) + ": " + constantValue.getConstantValue(CONSTANT_POOL));
        }
    }














    public static String toHexString1(byte[] b){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i){
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }
    public static String toHexString1(byte b){
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1){
            return "0" + s;
        }else{
            return s;
        }
    }
    public static int byteArrayToInt(byte[] b) {
        return
            b[3] & 0xFF |
            (b[2] & 0xFF) << 8 |
            (b[1] & 0xFF) << 16 |
            (b[0] & 0xFF) << 24;
    }
    public static int byteArrayToInt2(byte[] bytes) {
        int value= 0;
        //由高位到低位1
        for (int i = 0; i < bytes.length; i++) {
            int shift= (4 - 1 - i) * 8;
            value |=(bytes[i] & 0x000000FF) << shift;//往高位游
        }
        return value;
    }
    // 0000 0000 0000 0001
    // 0000 0000 0001 0000
    // 0000 0000 0010 0000
    // 0000 0010 0000 0000
    // 0000 0100 0000 0000
    // 0001 0000 0000 0000
    // 0010 0000 0000 0000
    // 0100 0000 0000 0000

    // 0000 0000 0010 0001
    public final static List<Constant> CONSTANT_POOL = new ArrayList<Constant>();
    final static Map<Integer,String> CLASS_ACCESS_FLAGS_MAP = new HashMap<Integer, String>();

    final static Map<Integer,String> METHOD_ACCESS_FLAGS_MAP = new HashMap<Integer, String>();

    static {
        CONSTANT_POOL.add(null);

        CLASS_ACCESS_FLAGS_MAP.put(1, "ACC_PUBLIC");
        CLASS_ACCESS_FLAGS_MAP.put(1 << 4, "ACC_FINAL");
        CLASS_ACCESS_FLAGS_MAP.put(2 * 1 << 4, "ACC_SUPER");
        CLASS_ACCESS_FLAGS_MAP.put(2 * 1 << 8, "ACC_INTERFACE");
        CLASS_ACCESS_FLAGS_MAP.put(4 * 1 << 8, "ACC_ABSTRACT");
        CLASS_ACCESS_FLAGS_MAP.put(1 << 12, "SYNTHETIC");
        CLASS_ACCESS_FLAGS_MAP.put(2 * 1 << 12, "ANNOTATION");
        CLASS_ACCESS_FLAGS_MAP.put(4 * 1 << 12, "ACC_ENUM");



        METHOD_ACCESS_FLAGS_MAP.put(1, "ACC_PUBLIC");
        METHOD_ACCESS_FLAGS_MAP.put(2, "ACC_PRIVATE");
        METHOD_ACCESS_FLAGS_MAP.put(4, "ACC_PROTECTED");
        METHOD_ACCESS_FLAGS_MAP.put(8, "ACC_STATIC");
        METHOD_ACCESS_FLAGS_MAP.put(1<<4, "ACC_FINAL");
        METHOD_ACCESS_FLAGS_MAP.put(2*1<<4, "ACC_SYNCHRONIZED");
        METHOD_ACCESS_FLAGS_MAP.put(4*1<<4, "ACC_BRIDGE");
        METHOD_ACCESS_FLAGS_MAP.put(8*1<<4, "ACC_VARARGS");
        METHOD_ACCESS_FLAGS_MAP.put(1*1<<8, "ACC_NATIVE");
        METHOD_ACCESS_FLAGS_MAP.put(4*1<<8, "ACC_ABSTRACT");
        METHOD_ACCESS_FLAGS_MAP.put(8*1<<8, "ACC_STRICTFP");
        METHOD_ACCESS_FLAGS_MAP.put(1<<12, "ACC_SYNTHETIC");


    }


}
