package com.example.agoodob.appmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 2016-2-28
 * 监控手机上 APP 的安装，升级，卸载事件
 * 没测试过
 *
 * 什么安全卫士之类的会用到
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
