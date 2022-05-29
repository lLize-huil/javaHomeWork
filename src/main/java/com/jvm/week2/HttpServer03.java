package com.jvm.week2;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Executable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer03 {

    public static void main(String[] args) throws IOException {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() + 2);
        ServerSocket serverSocket = new ServerSocket(8803);
        while(true){
            try{
                Socket socket = serverSocket.accept();
                //交给线程池处理
                executorService.execute(() -> service(socket));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket){
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            //报文头
            printWriter.println("Http/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello.nio3";
            //服务端要在OutputStream的报文头中，告诉客户端，整个报文体的长度是多少
            printWriter.println("Content-Length:" + body.getBytes().length);
            //报文头和报文体之间用空行隔开
            printWriter.println();
            //报文体
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
