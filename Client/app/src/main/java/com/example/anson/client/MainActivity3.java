package com.example.anson.client;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static android.R.attr.data;

public class MainActivity3 extends AppCompatActivity {

    public Button btn2;
    public TextView tv1;
    public TextView tv2;
    public TextView tv3;
    public TextView tv4;
    public TextView tv5;
    public TextView tv6;
    public TextView tv7;
    public TextView tv8;
    Bundle bundle;
    float  fl;
    float  fpath;
    float  fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tv8=(TextView)findViewById(R.id.textView8);
        tv1 = (TextView)findViewById(R.id.textView);
        btn2=(Button)findViewById(R.id.button4);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);
        tv4 = (TextView)findViewById(R.id.textView4);
        tv5 = (TextView)findViewById(R.id.textView5);
        tv6 = (TextView)findViewById(R.id.textView6);
        tv7 = (TextView)findViewById(R.id.textView7);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent a1 = new Intent(MainActivity3.this,MainActivity.class);
                    startActivity(a1);
            }
        });

        MyThread3 t = new MyThread3(MainActivity3.this);
        t.start();
    }


    public class MyThread3 extends Thread {

        MainActivity3 activity3;
        public float fl1 ;

        Socket socket = null;
        String ip ="10.133.186.29";
        int port = 33339;

        public MyPaint myPaint;

        String fileName;
        MyThread3(MainActivity3 activity3){
            this.activity3 = activity3;
        }

        @Override
        public void run() {
            super.run();

            try {
                socket = new Socket(ip, port);
                Log.i("info", "实体2获取数据"+data);
                tv8.setText("22.7°N-101.05°E"+data);
                // Toast.makeText(getApplicationContext(), "已连接到服务器",
                // Toast.LENGTH_SHORT).show()
                OutputStream out = socket.getOutputStream();

                InputStream in = socket.getInputStream();
                Log.i("info", "工程已启动");
                out.write(fileName.getBytes());
                out.flush();
                Log.i("info","已向服务端发送数据");

                Log.i("info", "创建MyFile");
                MyFile mf = new MyFile();

                Log.i("info", "开始保存文件");
                String fileN = mf.saveFile(in);
                Log.i("info", "开始读取文件");


                int[][] color =mf.readFile(fileN);
                Log.i("info", "文件已读出");

                Log.i("info","fl1:"+fl1);

                myPaint = new MyPaint(MainActivity3.this, color);
                Log.i("info", "MyPaint已创建");
                socket.close();

                //myHandler.obtainMessage().sendToTarget();


                myHandler.sendMessage(mf.readFL(fileN));

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }


        }
        private Handler myHandler = new Handler(){
            public void handleMessage(Message msg) {

                if(msg.what == 1){
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    MainActivity3.this.addContentView(myPaint,params);
                    tv1.setText("探测起始频率："+msg.getData().getFloat("data")+"MHz");
                    tv2.setText("探测步进频率："+msg.getData().getFloat("data1")+"KHz");
                    tv3.setText("探测终止频率："+msg.getData().getFloat("data2")+"MHz");
                    tv4.setText("单频点探测次数:"+(int)msg.getData().getFloat("data3")+"次");
                    tv5.setText("探测总距离门:"+msg.getData().getInt("data4")+"个");
                    tv6.setText("距离门分辨率:"+msg.getData().getFloat("data5")+"km");
                    tv7.setText("探测总频点数:"+msg.getData().getInt("data6")+"个");


                }

            };
        };
    }

}
