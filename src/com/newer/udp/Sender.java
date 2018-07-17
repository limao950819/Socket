package com.newer.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Sender {
	
	public static void main(String[] args) {
		
		DatagramSocket socket;
		DatagramPacket packet;
		
		try {
			//注意这里写端口会报错
			socket = new DatagramSocket();
			
			System.out.println("请输入文字：");
			Scanner sc = new Scanner(System.in);
			String msg = sc.next();
			
			byte[] data = msg.getBytes();
			
			//目标的主机名和端口
			InetAddress address = InetAddress.getByName("127.0.0.1");
			int port = 9000;
			
			packet = new DatagramPacket(data, data.length,address,port);
			
			socket.send(packet);
			System.out.println("发送完成");
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}
