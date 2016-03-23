package com.example.agoodob.nerdlauncher;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
    2016-3-21
    Android编程权威指南 - 第23章 深入学习 Intent 和任务
    项目4
    就是一个启动器
 */
public class NerdLauncherActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NerdLauncherFragment();
    }
}
