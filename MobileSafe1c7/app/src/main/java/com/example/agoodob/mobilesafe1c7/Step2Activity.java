package com.example.agoodob.mobilesafe1c7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;


/**
 * 2016-3-1 update
 *
 */
public class Step2Activity extends BaseSetupActivity {

    SettingsItemView settingItemView;
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

        Button prev = (Button) findViewById(R.id.Step2Previous);
        Button next = (Button) findViewById(R.id.Step2Next);
        settingItemView = (SettingsItemView) findViewById(R.id.settingItemView);
        mPref = getSharedPreferences("config", MODE_PRIVATE);
        // 判断之前有没有存 SIM 信息，然后把钩打上，不然切换页面之后你会看到钩没了
        String sim = mPref.getString("sim", "");
        if (!TextUtils.isEmpty(sim)){
            settingItemView.setChecked(true);
        }

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });

        settingItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settingItemView.isChecked()){
                    settingItemView.setChecked(false);

                    mPref.edit().remove("sim").apply();

                } else {
                    settingItemView.setChecked(true);
                    // SIM 卡有序列号，保存并比对这个序列号即可

                    TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                    String simNumber = tm.getSimSerialNumber();

                    mPref.edit().putString("sim", simNumber).apply();
                }

            }
        });

    }

    @Override
    public void showNextPage() {
        Intent i = new Intent(this, Step3Activity.class);
        startActivity(i);
        finish();
        // 两个界面切换的动画
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
    }

    @Override
    public void showPrevPage(){
        Intent i = new Intent(this, Step1Activity.class);
        startActivity(i);
        finish();
        // 两个界面切换的动画
        overridePendingTransition(R.anim.trans_prev_in, R.anim.trans_prev_out);
    }

}
