package com.ligeng.test.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by dev on 16-6-23.
 */
public class CopyFile {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("/home/dev/projects/test/src/main/java/com/ligeng/test/nio/1.txt");
        FileOutputStream out = new FileOutputStream("/home/dev/projects/test/src/main/java/com/ligeng/test/nio/2.txt");

        FileChannel fin = in.getChannel();
        FileChannel fout = out.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true){
            buffer.clear();
            int r = fin.read(buffer);
            if (r==-1){
                break;
            }
            buffer.flip();
            fout.write(buffer);
        }

    }
}
