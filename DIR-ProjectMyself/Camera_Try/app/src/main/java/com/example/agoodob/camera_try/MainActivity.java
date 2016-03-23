package com.example.agoodob.camera_try;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.agoodob.camera_try.HiveOrWIFI.CheckNetworkType;


/**
 * ViewPager 动画
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startActivity(new Intent(this, CheckNetworkType.class));
    }
}
