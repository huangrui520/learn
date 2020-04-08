package com.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author huangrui
 * @date   2020年2月22日
 * @description:Echo客户端
 */
public class EchoClientDemo1 {
	
	public static void main(String[] args) {
		//创建一个Socket对象，指定要连接的服务器
		try {
			Socket socket=new Socket("localhost", 6668);
			//获取输入流
			PrintStream ps=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
			//输出流
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//发送数据
			ps.println("hello,my name is client");
			ps.flush();
			//读取数据
			String get=br.readLine();
			System.out.println(get);
			br.close();
			ps.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
