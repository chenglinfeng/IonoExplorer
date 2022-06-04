package com.example.anson.client;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

public class MyPaint extends View implements Runnable {

	private Paint paint = null;
	
	int X_MAX=1000;
	int Y_MAX=700;
	int[][] color = new int[X_MAX][Y_MAX];

	MainActivity2 activity;
    Bitmap bitmap = Bitmap.createBitmap(X_MAX,Y_MAX, Bitmap.Config.ARGB_8888);
	
	public MyPaint(Context context,int[][] color) {
		
		super(context);
		activity = (MainActivity2) context;
		paint=new Paint();
		
		this.color=color;
		X_MAX=color.length;
		Y_MAX=color[0].length;
		
		postInvalidate();
		//new Thread(this).start();
		// TODO Auto-generated constructor stub
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		float displayWidth = dm.widthPixels;
		float displayHeight = dm.heightPixels-200;

		System.out.println("displayWidth:"+displayWidth);
		System.out.println("displayHeight:"+displayHeight);
		
		System.out.println("X_MAX:"+X_MAX);
		System.out.println("Y_MAX:"+Y_MAX);
		
        Canvas mCanvas = new Canvas(bitmap);//�Զ���һ������������������Bitmap����
        
		for(int i=0;i<X_MAX;i++)
			for(int j=0;j<Y_MAX;j++)
			{
				paint.setColor(color[i][j]);
				mCanvas.drawPoint(i, Y_MAX-j,paint);
				
			}	

		Matrix matrix = new Matrix();
		matrix.setTranslate((displayWidth-X_MAX)/2, (displayHeight-Y_MAX)/2);
		float s=displayWidth/(float)X_MAX;
		matrix.postScale(s, s, displayWidth/2, displayHeight/2);
		
		canvas.drawBitmap(bitmap,matrix, null);
		
	}
	
	
	public void run(){
		while(!Thread.currentThread().isInterrupted())
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
	
				Thread.currentThread().interrupt();
			}
			
			postInvalidate();
		}
	}

}
