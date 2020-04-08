package com.socket.CS;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;

public class UserThread implements Runnable {

    private String name;//客户端的用户名称
    private Socket socket;
    private Vector<UserThread> vector;//用户端的线程集合
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private boolean flag = true;

    public UserThread(Socket socket, Vector<UserThread> vector) {
        this.socket = socket;
        this.vector = vector;
        vector.add(this);
    }

    @Override
    public void run() {
        System.out.println("客户端" + socket.getInetAddress().getHostAddress() + "已连接");
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            while (flag) {
                //读取消息对象
                Message message = (Message) ois.readObject();
                int type = message.getType();
                switch (type) {
                    case MessageType.TYPE_SEND:
                        String to = message.getTo();
                        UserThread user;
                        int size = vector.size();
                        for (int i = 0; i < size; i++) {
                            user = vector.get(i);
                            if (to.equals(user.name) && user != this) {
                                user.oos.writeObject(message);
                            }
                        }
                        break;
                    case MessageType.TYPE_LOGIN:
                        name = message.getFrom();
                        message.setInfo("欢迎你，");
                        oos.writeObject(message);
                        break;
                }

            }
            oos.close();
            ois.close();
            socket.close();
        } catch (SocketException e) {
            try {
                System.out.println(name + "已断开连接");
                if (ois!=null){
                    ois.close();
                    oos.close();
                }
                if (socket != null) {
                    socket.close();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
