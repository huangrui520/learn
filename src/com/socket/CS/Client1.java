package com.socket.CS;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        try {
            Socket socket=new Socket("localhost",9999);
            System.out.println("服务器连接成功");
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            //向服务器发送登录消息
            System.out.println("请输入昵称：");
            String name=scanner.nextLine();
            Message message=new Message(name,null,MessageType.TYPE_LOGIN,null);
            oos.writeObject(message);
            message= (Message) ois.readObject();
            System.out.println(message.getInfo()+message.getFrom());
            //启动读取消息的线程
            ExecutorService es = Executors.newSingleThreadExecutor();
            es.execute(new ReadInfoThread(ois));

            //使用主线程来发送消息
            boolean flag=true;
            while (flag){
                message=new Message();
                System.out.println("To: ");
                message.setTo(scanner.nextLine());
                message.setFrom(name);
                message.setType(MessageType.TYPE_SEND);
                System.out.println("Info: ");
                message.setInfo(scanner.nextLine());
                oos.writeObject(message);
            }
            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
