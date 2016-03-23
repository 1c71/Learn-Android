package com.example.agoodob.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private Intent i;
    MyServiceConn conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent(this, MyService.class);
        conn = new MyServiceConn();
    }

    public void start(View v){
        startService(i);
    }

    public void stop(View v){
        stopService(i);
    }

    public void bind(View v){
        bindService(i, conn, BIND_AUTO_CREATE);
    }
    class MyServiceConn implements ServiceConnection{

        // 连接成功时调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        // 连接失败时调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
