package com.example.anson.client;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


@SuppressLint("SdCardPath")
public class MyFile{
	float fl;
	


	static int FHL = 500; // file header length:�ļ�ͷ���ȣ��������ٸ�������

    
	public MyFile() {
		// TODO Auto-generated constructor stub
	}
	

	public String saveFile(InputStream in) throws IOException {
	
		DataInputStream dataInput = new DataInputStream(
				new BufferedInputStream(in));
		int nameSize =dataInput.readInt();
		byte[] nameByte = new byte[nameSize];
		int nameLen = 0;
		while (nameLen < nameSize) {
			nameLen += dataInput.read(nameByte, nameLen, nameSize - nameLen);
		}
		System.out.println("�ļ����Ѷ���");
		int size = dataInput.readInt();
		byte[] data = new byte[size];
		// dataInput.readFully(data);
		int len = 0; // why?
		while (len < size) {
			len += dataInput.read(data, len, size - len);
		}

	String fileName = new String(nameByte);
	File f = new File("/data/data/com.eis.client",fileName);
	FileOutputStream out = new FileOutputStream(f);

	out.write(data, 0, data.length);
	
		out.flush();
		out.close();
		Log.i("info", "�Ѿ�����");
		
		return fileName;

	}
	
	public Message readFL(String fileName)throws Exception
	{
		File f = new File("/data/data/com.eis.client",fileName);
	      InputStream input =new FileInputStream(f);
		  CppInputStream dis = new CppInputStream(new BufferedInputStream(input));
			
			System.out.println("��ȡ���Ƶ�㣺");
			Message msg = new Message();
			msg.what = 1;
			float fl1 = dis.readFloat();
			float fpath = dis.readFloat();
			float fh = dis.readFloat();
			float ftimes = dis.readFloat();
			int Y_MAX = (int) dis.readFloat();
			float dresolution = dis.readFloat();
			int X_MAX = (int) dis.readFloat();
			Bundle data1 = new Bundle();
			data1.putFloat("data", fl1);
			data1.putFloat("data1",fpath);
			data1.putFloat("data2", fh);
			data1.putFloat("data3", ftimes);
			data1.putInt("data4", Y_MAX);
			data1.putFloat("data5", dresolution);
			data1.putInt("data6", X_MAX);
			
			msg.setData(data1);
			Log.i("info", "��ȡ���Ƶ��"+fl1);
		return msg;
		
	}
	
	public int[][] readFile(String fileName) throws Exception{
			
		File f = new File("/data/data/com.eis.client",fileName);
      InputStream input =new FileInputStream(f);
	  CppInputStream dis = new CppInputStream(new BufferedInputStream(input));
		
		int count = 0;

		Log.i("info", "��ȡ���Ƶ��");
		System.out.println("��ȡ���Ƶ�㣺");
		 fl = dis.readFloat();
		Log.i("info","���Ƶ�㣺"+fl);
		
	
		
		count++;
		
		System.out.println("��ȡ����Ƶ�ʣ���");
		float fpath = dis.readFloat();
		Log.i("info","����Ƶ�ʣ�"+fpath);
		count++;
		
		System.out.println("��ȡ���Ƶ�㣺");
		float fh = dis.readFloat();
		Log.i("info","���Ƶ�㣺"+fh);
		count++;
		
		System.out.println("��ȡ��Ƶ��̽�������");
		float ftimes = dis.readFloat();
		count++;
		
		System.out.println("��ȡ̽���ܾ����ǣ�");
		int Y_MAX = (int) dis.readFloat();
		count++;
		
		System.out.println("��ȡ�ŷֱ��ʣ�");
		float dresolution = dis.readFloat();
		count++;
		
		System.out.println("��ȡ̽����Ƶ������");
		int X_MAX = (int) dis.readFloat();
		count++;
		

		
		while(count<FHL){
			System.out.println(4 * (count + 1) + ":" + dis.readFloat());
			count++;
		}

		float a[][] = new float[X_MAX][Y_MAX];
		int b[][] = new int[X_MAX][Y_MAX];

		for (int i = 0; i < X_MAX; i++) {
			for (int j = 0; j < Y_MAX; j++) {
				a[i][j] = dis.readFloat();

				b[i][j] = setColor(a[i][j]);
			}

		}
		
		
		return b;
		
	}
	


	static int setColor(float f) {
		int color;
		if (f >= -10 && f < 30)
			color = Color.BLUE;
		else if (f >= 30 && f < 60)
			color = Color.GREEN;
		else if (f >= 60 && f < 80)
			color = Color.YELLOW;
		else if (f >= 80 && f < 100)
			color = Color.RED;
		else
			color = Color.WHITE;

		return color;
	}
}
