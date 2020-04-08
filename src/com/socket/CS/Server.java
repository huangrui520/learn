package com.socket.CS;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        //保存用户处理线程
        Vector<UserThread> vector=new Vector<>();
        //创建线程池
        ExecutorService es=Executors.newFixedThreadPool(5);
        //创建服务器socket
        try {
            ServerSocket server=new ServerSocket(9999);
            System.out.println("服务器已启动，等待连接。。。");
            while (true){
                Socket socket=server.accept();
                UserThread user=new UserThread(socket,vector);
                es.execute(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
