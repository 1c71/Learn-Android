package com.example.agoodob.sendcustombroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 2016-2-28
 * 发送自定义广播
 * 6.0 下测试成功
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View v){
        Intent i = new Intent();
        i.setAction("zidingyi");
        sendBroadcast(i); // 无序广播
        Toast.makeText(this, "真的有发送广播", Toast.LENGTH_SHORT).show();

        sendOrderedBroadcast(i,null, null, null, 0, "每人发一百斤大米", null);
        // 发送有序广播
        // intent, 权限, 广播接收者, handler,
    }
}
