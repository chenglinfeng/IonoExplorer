package com.example.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	static int port=33333;
	
    public static void main(String[] args) {  
        try {  
        	//���������socket
            ServerSocket server = new ServerSocket();  
            System.out.println("������Ѵ������ȴ��ͻ������ӡ���������");
            server.bind(new InetSocketAddress(port)); 
            while(true){
                Socket socket = server.accept(); 
                System.out.println("�ѳɹ����ӵ��ͻ��ˡ�����������");

                MyThread thread=new MyThread(socket);
                thread.start();
            }

        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}