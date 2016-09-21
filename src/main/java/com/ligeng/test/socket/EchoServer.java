package com.ligeng.test.socket;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This program implements a simple server that listens to port 8189 and echoes back all client
 * input.
 * @version 1.20 2004-08-03
 * @author Cay Horstmann
 */
public class EchoServer
{
   public static void main(String[] args)
   {
      try
      {
            // establish server socket
          ServerSocket s = new ServerSocket(8189);
          ExecutorService exec = Executors.newCachedThreadPool();
          while (true) {
              //wait for client connection
              final Socket incoming = s.accept();
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    InputStream inStream = null;
                    OutputStream outStream = null;
                    try {
                        inStream = incoming.getInputStream();
                        outStream = incoming.getOutputStream();
                        Scanner in = new Scanner(inStream);
                        PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);

                        out.println("Hello! Enter BYE to exit.");

                        // echo client input
                        boolean done = false;
                        while (!done && in.hasNextLine()) {
                            String line = in.nextLine();
                            out.println("Echo: " + line);
                            if (line.trim().equals("BYE")) done = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            incoming.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
          }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}
