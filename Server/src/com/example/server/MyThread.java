package com.example.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class MyThread extends Thread {

	Socket socket;
	int KEY;
    
    public MyThread(Socket socket){
		this.socket=socket;
	}
	

	public void run() {
		
    	try {
    		DataInputStream dis = new DataInputStream(socket.getInputStream()); 
    		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        	System.out.println("已连接输入socket");
        	
			KEY = dis.readInt();
			
			switch (KEY) {
			case 1:
			{
				File file = new File("C:\\Users\\User\\Desktop\\电离层数据\\AMP");  
		        String[] fileList = file.list();
		        
	            dos.writeInt(fileList.length);  
	            
		        for(int i=0;i<fileList.length;i++)
		        {
                    File readfile = new File(file + "\\" + fileList[i]);
                    //写出文件长度
		        	dos.writeDouble(readfile.length()/1024);
		        	//写出文件名  
	                dos.writeUTF(fileList[i]);  
	                //更新输出流  
	                dos.flush();  
		        }
		        dis.close();
		        dos.close();
		        socket.close();
				break;
			}		       
			case 2:
			{
		    	String fileName = dis.readUTF();
		    	File f = new File("C:\\Users\\User\\Desktop\\电离层数据\\AMP",fileName);
                System.out.println("发送文件"+fileName);
		    	FileInputStream fis = new FileInputStream(f);  
		    	
		    	dos.writeUTF(fileName);
		    	dos.flush();
			    System.out.println("已发送文件名");
			    
				int size = fis.available();  
				byte[] data = new byte[size];  
				fis.read(data); 
				System.out.println("已读出文件"); 		
				dos.writeInt(size);  
				dos.write(data);
				dos.flush();  
				System.out.println("服务端已成功发送.amp文件。。.");
				
				String paraName = dis.readUTF();
		    	File f2 = new File("C:\\Users\\User\\Desktop\\电离层数据\\PARA",paraName);
                System.out.println("发送文件"+paraName);
		    	FileInputStream fis2 = new FileInputStream(f2);  
		    	
		    	dos.writeUTF(paraName);
		    	dos.flush();
			    System.out.println("已发送文件名");
			    
				int sizePara = fis2.available();  
				byte[] dataPara = new byte[sizePara];  
				fis2.read(dataPara); 
				System.out.println("已读出文件"); 		
				dos.writeInt(sizePara);  
				dos.write(dataPara);
				dos.flush();  
				System.out.println("服务端已成功发送.para文件。。.");
				dos.close();  
				fis.close();  
				break;
			}
			
			default:
				break;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
//        try {
//	        String fileName = "22.7°N-101.05°E_20151114_130000_VS.amp";
//            File f = new File("C:\\Users\\User\\Desktop\\电离层数据",fileName);
//	        FileInputStream fis = new FileInputStream(f);  
//
//			byte[] nameByte = fileName.getBytes();
//			int nameSize = nameByte.length;
//			dos.writeInt(nameSize);
//			dos.write(nameByte);
//			System.out.println("已发送文件名");
//			    
//		    int size = fis.available();  
//		    byte[] data = new byte[size];  
//			fis.read(data); 
//			System.out.println("已读出文件"); 		
//			dos.writeInt(size);  
//			dos.write(data);
//			dos.flush();  
//			System.out.println("服务端已成功发送文件。。.");
//			dos.close();  
//			fis.close();  
//			socket.close();	
//		} 
//        catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	}
}


