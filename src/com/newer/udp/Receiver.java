package com.newer.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Receiver {
	
	public static void main(String[] args) {
		
		DatagramSocket socket;
		DatagramPacket packet;
		
		try {
			//需要明确端口
			socket = new DatagramSocket(9000);
			//缓冲区
			byte[] buf = new byte[1024];
			
			packet = new DatagramPacket(buf, buf.length);
			
			System.out.println("开始接收");
			socket.receive(packet);
			System.out.println("收到");
			
			byte[] data = packet.getData();
			int size = packet.getLength();
			String msg = new String(data,0,size,"utf-8");
			
			System.out.println(msg+","+packet.getAddress()+","+packet.getPort());
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
