package com.example.anson.client;

import android.app.Application;

/**
 * Created by Anson on 2017-03-04.
 */

public class MyApp extends Application {

    private String Data ;
    private String IP;
    void MyAPP(){

    }
    String  getdata()
    {
        return Data;
    }
    void setdata(String s){
        Data = s;
    }

    String getIP(){
        return IP;
    }

    void setIP(String ip){
        IP = ip;
    }
}
