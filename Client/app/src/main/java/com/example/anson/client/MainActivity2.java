package com.example.anson.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private ListView listView;
    MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myApp = (MyApp) getApplicationContext();

        String[] Pick = new String[] {"_20151114_130000_VS.amp","_20151114_131500_VS.amp","_20151114_133000_VS.amp",
                "_20151114_134500_VS.amp","_20151114_140000_VS.amp","_20151114_141500_VS.amp","_20151114_143000_VS.amp","_20151114_144500_VS.amp","_20151114_150000_VS.amp"};
        listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<String>(this,R.layout.adapter,Pick));
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                // Toast.makeText(getApplicationContext(),
                //((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                myApp.setdata(String.valueOf(((TextView)view).getText()));
                Log.i("info", "已发送数据");
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);

            }
        });
        setContentView(listView);
    }
}
