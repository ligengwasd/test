package com.ligeng.test.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
/**
 * Created by dev on 16-6-24.
 */
public class ServerSocketChannelTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(1978));
        serverSocketChannel.configureBlocking(false);
        while (true){

            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println(1);
            if(socketChannel!=null){
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int r = socketChannel.read(buffer);
                if (r>0){
                    buffer.flip();
                    String str = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
                    System.out.println(str);
                    // 准备发送的文本
                    String sendString = "你好,客户端. @" + new Date().toString()+ "，已经收到你的信息" + str;
                    buffer = ByteBuffer.wrap(sendString.getBytes("UTF-8"));
                    socketChannel.write(buffer);
                }
            }
            System.out.println(2);
        }
    }
}
