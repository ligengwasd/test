package com.ligeng.test.nio.selector;

import com.alibaba.fastjson.serializer.InetAddressCodec;
import com.alibaba.fastjson.serializer.InetSocketAddressCodec;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by dev on 16-6-24.
 */
public class SelectorTest {

    public static void main(String[] args){

    }

    public SelectorTest() throws IOException {
        this.selector = getSelector();
    }

    final Integer port = 1987;
    final Selector selector;

    protected Selector getSelector() throws IOException {
        Selector sel = Selector.open();

        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);

        ServerSocket socket = server.socket();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",port);
        socket.bind(address);

        server.register(sel, SelectionKey.OP_ACCEPT);
        return  sel;
    }

    public void listen() {
        System.out.println("listen on "+port);
        try {
            while(true){
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()){
                    SelectionKey key = iter.next();
                    iter.remove();
                    process(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void process(SelectionKey key) throws IOException {
        if (key.isAcceptable()){
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel channel = server.accept();
            channel.configureBlocking(false);
            channel.register(selector,SelectionKey.OP_READ);
        }
        else if (key.isReadable()){
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count>0){
                buffer.flip();
            }
        }
    }
}
