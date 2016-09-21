package com.ligeng.test.webtest;

import org.apache.log4j.Logger;
import java.net.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by dev on 16-5-26.
 */
public class CallHttpRequest implements Runnable{

//    private static Logger log = Logger.getLogger(CallHttpRequest.class);
    public  static int successRequest = 0;
    public  static int failRequest = 0;
    public  static int timeOutRequest = 0;
    private final String hostString = "http://localhost:";
    private String port;
    private String puductPartURL;

    private CountDownLatch begin;
    private CountDownLatch end;

    CallHttpRequest(String port, String puductPartURL, CountDownLatch begin,
                    CountDownLatch end) {
        this.port = port;
        this.puductPartURL = puductPartURL;
        this.begin = begin;
        this.end = end;
    }

    private String makeFullURL() {
//        return hostString + port + puductPartURL;

        return "http://127.0.0.1:8088/test1/stock/decr";
    }
    private  static synchronized void incrementSuccessCount(){
        successRequest++;
    }

    private  static synchronized void incrementFailCount(){
        failRequest++;
    }

    private static synchronized void incrementTimeOutCount(){
        timeOutRequest++;
    }

    public void run() {
        String urlStr = makeFullURL();
        URL url;
        try {
            begin.await();
            url = new URL(urlStr);
            URLConnection URLconnection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) URLconnection;
            httpConnection.setConnectTimeout(100000);
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                incrementSuccessCount();
            } else {
                incrementFailCount();
            }
        } catch (SocketTimeoutException e) {
            incrementTimeOutCount();
//            log.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            end.countDown();
        }

    }
}
