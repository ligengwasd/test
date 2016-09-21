package com.ligeng.test.nio;

import org.apache.http.conn.HttpInetSocketAddress;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by dev on 16-6-23.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8189));

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true){
            buffer.clear();
            int r =  socketChannel.read(buffer);
//                System.out.println(new String(buffer.get));
            while (buffer.hasRemaining()){
                System.out.println((char)buffer.get());
            }
            if (r==-1){
                break;
            }
        }
    }
}
