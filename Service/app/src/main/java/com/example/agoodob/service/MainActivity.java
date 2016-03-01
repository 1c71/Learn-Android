package com.example.agoodob.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 2016-2-28
 *
 * 第一篇学习 Service, 电话录音机
 *
 *
 * 和 Broadecast 一样，BroadeCast 是写个 class extends Broadecast
 * 然后去 AndroidManifest.xml 里去写 <receiver>....</receiver>
 *
 * Service 也是 写个 class extends Service
 * 然后去 AndroidManifest.xml 去写 <service> </service>
 */
public class MainActivity extends AppCompatActivity {

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View v){
        // 显式启动服务
        i = new Intent(this, MyService.class);
        startService(i);
    }

    public void stop(View v){
        stopService(i);
    }


}
