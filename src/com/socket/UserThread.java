package com.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

//用来处理一个客户端的线程
public class UserThread implements Runnable {

    private Socket s;
    public UserThread(Socket s) {
        super();
        this.s = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintStream ps = new PrintStream(new BufferedOutputStream(s.getOutputStream()));
            String info = br.readLine();
            System.out.println(info);
            ps.println("echo:" + info);
            ps.flush();

            ps.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
