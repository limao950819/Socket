package com.newer.tcpfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	Socket socket;
	
	FileInputStream fileIn;
	OutputStream out;
	
	int port = 9000;
	String address = "127.0.0.1";
	
	public void start() {
		
		System.out.println("请输入上传的文件：");
		Scanner sc = new Scanner(System.in);
		String file = sc.next();
		
		try {
			socket = new Socket(address, port);
			//获得输出流
			out = socket.getOutputStream();
			//设置缓冲区
			byte[] buf = new byte[1024*4];
			int size;
			
			fileIn = new FileInputStream(file);
			while(-1 != (size = fileIn.read(buf))) {
				out.write(buf,0,size);
				out.flush();
			}
			
			System.out.println("发送完成");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileIn.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		Client client = new Client();
		client.start();
		
	}

}
