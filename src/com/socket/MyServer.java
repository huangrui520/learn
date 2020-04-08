package com.socket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8888);
            Socket client = server.accept();
            System.out.println("客户端来了");
            OutputStream out = client.getOutputStream();
            InputStream in = new FileInputStream("F:\\编程\\web1\\learn1\\练习2\\1.html");
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
                out.flush();
            }
            in.close();
            out.close();
            client.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
