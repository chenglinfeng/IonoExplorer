package com.example.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	static int port=33333;
	
    public static void main(String[] args) {  
        try {  
        	//建立服务端socket
            ServerSocket server = new ServerSocket();  
            System.out.println("服务端已创建，等待客户端连接。。。。。");
            server.bind(new InetSocketAddress(port)); 
            while(true){
                Socket socket = server.accept(); 
                System.out.println("已成功连接到客户端。。。。。。");

                MyThread thread=new MyThread(socket);
                thread.start();
            }

        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}