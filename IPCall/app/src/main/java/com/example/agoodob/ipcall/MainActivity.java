package com.example.agoodob.ipcall;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * 2016-2-27
 * IP 拨号器就是打电话的时候自动给你加前缀
 * 给你省钱
 *
 * 还没测，虚拟机没测，真机也没测
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click(){
        EditText et = (EditText) findViewById(R.id.editText);
        SharedPreferences sp = getSharedPreferences("ip", MODE_PRIVATE);
        sp.edit().putString("ipNumber", et.getText().toString()).apply();
    }
}
