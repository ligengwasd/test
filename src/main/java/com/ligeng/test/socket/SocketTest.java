package com.ligeng.test.socket;

import org.apache.http.conn.HttpInetSocketAddress;

import java.io.*;
import java.net.*;
import java.util.*;


/**
 * Created by dev on 16-6-23.
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("https://www.baidu.com/",80);
//        socket.connect(new HttpInetSocketAd/**/dress("",80));
        socket.setSoTimeout(1000);
        InputStream inputStream = socket.getInputStream();
        Scanner in = new Scanner(inputStream);
        while (in.hasNextLine()){
            String line = in.nextLine();
            System.out.println(line);
        }



    }
}
