package com.example.agoodob.mobilesafe1c7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 2016-2-27
 * 手机防盗主页面
 *
 */
public class LostFindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences mPrefs = getSharedPreferences("config", MODE_PRIVATE);
        boolean configed = mPrefs.getBoolean("configed", false); // 判断是否进入过设置向导

        if (configed){
            setContentView(R.layout.activity_lost_find);
        } else {
            // 跳转到向导页面
            Intent i = new Intent(LostFindActivity.this, Step1Activity.class);
            startActivity(i);
        }

        RelativeLayout rl_reset = (RelativeLayout) findViewById(R.id.rl_reset);
        rl_reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(LostFindActivity.this, Step1Activity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
