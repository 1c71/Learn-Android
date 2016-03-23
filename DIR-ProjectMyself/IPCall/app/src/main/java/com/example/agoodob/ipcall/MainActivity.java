package com.example.agoodob.ipcall;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 2016-2-27
 * IP 拨号器就是打电话的时候自动给你加前缀，给你省钱
 *
 * Android 6.0 的虚拟机测了没成功似乎是广播收不到的问题
 * 先不调试以后再说
 */
public class MainActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
    }

    public void click(View v){
        //Toast.makeText(this, "aasd", Toast.LENGTH_SHORT).show();
        String a = et.getText().toString();
        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
        SharedPreferences sp = getSharedPreferences("ip", MODE_PRIVATE);
        sp.edit().putString("ipNumber", a).apply();
    }
}
