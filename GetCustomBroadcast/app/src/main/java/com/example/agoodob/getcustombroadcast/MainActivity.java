package com.example.agoodob.getcustombroadcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 2016-2-28
 * 接收自定义广播
 * 测试成功
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "接收到了广播", Toast.LENGTH_SHORT).show();
    }
}
