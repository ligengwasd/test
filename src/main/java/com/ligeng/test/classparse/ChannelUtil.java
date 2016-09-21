package com.ligeng.test.classparse;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * Created by dev on 16-6-29.
 */
public class ChannelUtil {
    public static void main(String[] args){
        System.out.println("ACC_sss".substring(4, "ACC_sss".length()).toLowerCase());;
    }
    static void swithTag(int tag,short i,FileChannel channel) throws IOException {
        Constant constant;
        switch (tag){
            case 1:
                constant = ChannelUtil.getUtf8(tag, channel);
                break;
            case 3:
                constant = ChannelUtil.getInteger(tag, channel);
                break;
            case 7:
                constant = ChannelUtil.getOneIndex(tag, channel);
                break;
            case 8:
                constant = ChannelUtil.getOneIndex(tag, channel);
                break;
            case 9:
                constant = ChannelUtil.getTowIndex(tag, channel);
                break;
            case 10:
                constant = ChannelUtil.getTowIndex(tag, channel);
                break;
            case 12:
                constant = ChannelUtil.getTowIndex(tag, channel);
                break;
            default:
                throw new RuntimeException("错误类型 tag = "+tag);
        }
        Parse.CONSTANT_POOL.add(constant);

    }
    //tag = 1
    static Constant getUtf8(int tag, FileChannel channel) throws IOException {
        Short length = getIndex(channel);
        return new Constant(tag,getString(channel, new Integer(length)));
    }
    //tag = 10
    static Constant getTowIndex(int tag, final FileChannel channel) throws IOException {
        return new Constant(tag,new ArrayList<Short>(){{
            add(getIndex(channel));
            add(getIndex(channel));
        }});
    }
    //tag = 7
    static Constant getOneIndex(int tag, final FileChannel channel) throws IOException {
        return new Constant(tag,new ArrayList<Short>(){{
            add(getIndex(channel));
        }});
    }
    //tag = 3
    static Constant getInteger(Integer tag, FileChannel channel) throws IOException {
        ByteBuffer byteBuffer = getByteBuffer(channel,4);
        return new Constant(tag,byteBuffer.getInt());
    }

    static ByteBuffer getByteBuffer(FileChannel channel,Integer len) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(len);
        channel.read(byteBuffer);
        byteBuffer.flip();
        return byteBuffer;
    }
    static Integer getTag(FileChannel channel) throws IOException {
        int tag = getByteBuffer(channel, 1).get() & 0xFF;
        return tag;
    }
    static Short getIndex(FileChannel channel) throws IOException {
        return getByteBuffer(channel, 2).getShort();
    }
    private static String getString(FileChannel channel,Integer len) throws IOException {
        ByteBuffer byteBuffer = getByteBuffer(channel, len);
        StringBuffer sb = new StringBuffer();
        while (byteBuffer.hasRemaining()){
            sb.append((char)byteBuffer.get());
        }
        return sb.toString();
    }
}
