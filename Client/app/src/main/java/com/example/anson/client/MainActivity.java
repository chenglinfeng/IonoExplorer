package com.example.anson.client;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;
    Button button3;
    EditText editText;
    View dialog;
//    MyApp myApp;
    Bundle bundle;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        LayoutInflater inflater = getLayoutInflater();
        dialog = inflater.inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dialog));
        editText = (EditText) dialog.findViewById(R.id.editText);

//        myApp = (MyApp)getApplicationContext();


    }

    //button（连接服务器）的点击函数
    public void showDialog(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请输入服务器ip:");
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();

//                if(editText.getText().toString()!=null) {
//                    myApp.setIP(editText.getText().toString());
//                    MyThread t = new MyThread();
//                    t.start();
//                }
//                if(editText.getText().toString()!=null) {
//                    bundle.putString("IP",editText.getText().toString());
//                    MyThread t = new MyThread();
//                    t.start();
//                }
               dialog.cancel();
            }
        });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setView(dialog);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.show();
    }

    //button2（选择文件）的点击函数
    public void chooseFile() {
        Intent intent1 = new Intent(this,MainActivity2.class);
//        intent1.putExtras(bundle);
        startActivity(intent1);
    }

    //button3(查询电离图)的点击函数
    public void displayPicture(){
        Intent intent2= new Intent(this, MainActivity3.class);
//        intent2.putExtras(bundle);
        startActivity(intent2);
    }


    public class MyThread extends Thread {

        Socket socket = null;
        String ip ;
        int port = 33339;

        public MyThread(){

        }

        @Override
        public void run() {
            super.run();
            ip = bundle.getString("IP");
            Message msg = new Message();
            Bundle mBundle = new Bundle();
            msg.what = 1;
            try {
                socket = new Socket(ip,port);
                mBundle.putString("info","连接成功");
                int TAG = 1;
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(TAG);
                dos.close();
                socket.close();
            } catch (IOException e) {
                mBundle.putString("info","连接失败，请重试");
            }
            msg.setData(mBundle);
            myHandler.sendMessage(msg);
        }

        private Handler myHandler = new Handler(){
            public void handleMessage(Message msg) {
                if(msg.what == 1){
                    Toast.makeText(MainActivity.this, msg.getData().getString("info"), Toast.LENGTH_SHORT).show();
                }

            };
        };
    }


}
