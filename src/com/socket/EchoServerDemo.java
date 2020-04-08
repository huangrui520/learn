package com.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author huangrui
 * @date   2020年2月22日
 * @description:Echo服务器端
 */
public class EchoServerDemo {
	
	public static void main(String[] args) {
		try {
			//创建一个服务器端的Socket（1024-65535）
			ServerSocket server=new ServerSocket(6668);
			System.out.println("服务器启动了,等待连接");
			
			//等待客户端的连接，造成阻塞，如果有客户连接成功，则立即返回一个Socket对象
			Socket socket=server.accept();
			System.out.println("客户端连接成功:"+server.getInetAddress().getHostAddress());
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//通过流，读取网络数据，没有数据，会阻塞
			String info=br.readLine();
			System.out.println(info);
			
			//获取输出流，向客户端返回消息
			PrintStream ps=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
			ps.println("echo:"+info);
			ps.flush();
			
			//关闭流
			br.close();
			ps.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
