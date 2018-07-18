package com.newer.tcpfile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用TCP套接字实现文件的传输
 * @author lenovo
 *
 */
public class FileServer {
	
    ServerSocket serverSocket;
	
	int port = 9000;
	
	//线程池
	ExecutorService pool;
	
	String filepath;
	
	public void start() {
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newCachedThreadPool();
			
			while(true) {
				Socket socket = serverSocket.accept();
				
				pool.execute(new Runnable() {
					
					@Override
					public void run() {
						
						ByteArrayOutputStream data = new ByteArrayOutputStream();
						
						try (InputStream in = socket.getInputStream()) {
							
							byte[] buf = new byte[1024*4];
							int size;
							while(-1 != (size = in.read(buf))) {
								data.write(buf, 0, size);
							}
							
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						byte[] info = data.toByteArray();
						String file = "";
						
						try {
							//获取文件的散列值
							
							byte[] hash = MessageDigest.getInstance("SHA-256").digest(info);
							file = new BigInteger(1,hash).toString(16);
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}
						
						try(FileOutputStream out = new FileOutputStream(new File(filepath,file))) {
							out.write(info);
							System.out.println("上传完成");
							
						} catch (Exception e) {
						System.out.println("上传失败");
						}
						
					}
				});
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FileServer server = new FileServer();
		server.start();
	}

}
