package com.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MuServerDemo {

    public static void main(String[] args) {

        ExecutorService es=Executors.newFixedThreadPool(3);
        try {
            ServerSocket server=new ServerSocket(6669);
            System.out.println("ready...");
            while(true) {
                Socket s=server.accept();
                System.out.println(s.getInetAddress().getHostAddress());
                es.execute(new UserThread(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

