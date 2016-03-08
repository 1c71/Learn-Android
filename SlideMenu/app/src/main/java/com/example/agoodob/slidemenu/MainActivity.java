package com.example.agoodob.slidemenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

/**
 * 2016-3-8
 * 侧滑菜单
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
