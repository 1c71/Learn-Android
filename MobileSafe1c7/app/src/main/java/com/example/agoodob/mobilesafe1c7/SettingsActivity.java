package com.example.agoodob.mobilesafe1c7;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 设置中心的 Activity
 *
 *
 */
public class SettingsActivity extends AppCompatActivity {

    private SettingsItemView siv;
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mPref = getSharedPreferences("config", MODE_PRIVATE);
        siv = (SettingsItemView) findViewById(R.id.siv_update);

        // 第二个是默认值
        boolean autoUpdate = mPref.getBoolean("auto_update", true);

        if(autoUpdate){
            siv.setChecked(true);
        } else {
            siv.setChecked(false);
        }

        siv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断勾选状态
                if(siv.isChecked()){
                    siv.setChecked(false);
                    mPref.edit().putBoolean("auto_update", false).apply();
                } else {
                    siv.setChecked(true);
                    mPref.edit().putBoolean("auto_update", true).apply();
                }

            }
        });


    }
}
