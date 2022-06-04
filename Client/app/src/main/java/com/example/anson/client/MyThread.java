package com.example.anson.client;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Anson on 2017-03-04.
 */

public class MyThread extends Thread {
    Socket socket = null;
    String ip ;
    int port = 33339;
    MainActivity activity;

    public MyThread(MainActivity activity,String ip){
        this.activity = activity;
        this.ip = ip;
    }

    @Override
    public void run() {
        super.run();
        Message msg = new Message();
        Bundle bundle = new Bundle();
        msg.what = 1;
        try {
            socket = new Socket(ip,port);
            bundle.putString("info","连接成功");
            socket.close();
        } catch (IOException e) {
            bundle.putString("info","连接失败，请重试");
        }
        msg.setData(bundle);
        myHandler.sendMessage(msg);
    }

    private Handler myHandler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                Toast.makeText(activity, msg.getData().getString("info"), Toast.LENGTH_SHORT).show();
            }

        };
    };
}
