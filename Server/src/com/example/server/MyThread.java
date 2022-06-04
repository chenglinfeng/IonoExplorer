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

        	System.out.println("����������socket");
        	
			KEY = dis.readInt();
			
			switch (KEY) {
			case 1:
			{
				File file = new File("C:\\Users\\User\\Desktop\\���������\\AMP");  
		        String[] fileList = file.list();
		        
	            dos.writeInt(fileList.length);  
	            
		        for(int i=0;i<fileList.length;i++)
		        {
                    File readfile = new File(file + "\\" + fileList[i]);
                    //д���ļ�����
		        	dos.writeDouble(readfile.length()/1024);
		        	//д���ļ���  
	                dos.writeUTF(fileList[i]);  
	                //���������  
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
		    	File f = new File("C:\\Users\\User\\Desktop\\���������\\AMP",fileName);
                System.out.println("�����ļ�"+fileName);
		    	FileInputStream fis = new FileInputStream(f);  
		    	
		    	dos.writeUTF(fileName);
		    	dos.flush();
			    System.out.println("�ѷ����ļ���");
			    
				int size = fis.available();  
				byte[] data = new byte[size];  
				fis.read(data); 
				System.out.println("�Ѷ����ļ�"); 		
				dos.writeInt(size);  
				dos.write(data);
				dos.flush();  
				System.out.println("������ѳɹ�����.amp�ļ�����.");
				
				String paraName = dis.readUTF();
		    	File f2 = new File("C:\\Users\\User\\Desktop\\���������\\PARA",paraName);
                System.out.println("�����ļ�"+paraName);
		    	FileInputStream fis2 = new FileInputStream(f2);  
		    	
		    	dos.writeUTF(paraName);
		    	dos.flush();
			    System.out.println("�ѷ����ļ���");
			    
				int sizePara = fis2.available();  
				byte[] dataPara = new byte[sizePara];  
				fis2.read(dataPara); 
				System.out.println("�Ѷ����ļ�"); 		
				dos.writeInt(sizePara);  
				dos.write(dataPara);
				dos.flush();  
				System.out.println("������ѳɹ�����.para�ļ�����.");
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
//	        String fileName = "22.7��N-101.05��E_20151114_130000_VS.amp";
//            File f = new File("C:\\Users\\User\\Desktop\\���������",fileName);
//	        FileInputStream fis = new FileInputStream(f);  
//
//			byte[] nameByte = fileName.getBytes();
//			int nameSize = nameByte.length;
//			dos.writeInt(nameSize);
//			dos.write(nameByte);
//			System.out.println("�ѷ����ļ���");
//			    
//		    int size = fis.available();  
//		    byte[] data = new byte[size];  
//			fis.read(data); 
//			System.out.println("�Ѷ����ļ�"); 		
//			dos.writeInt(size);  
//			dos.write(data);
//			dos.flush();  
//			System.out.println("������ѳɹ������ļ�����.");
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


