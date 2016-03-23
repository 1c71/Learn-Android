package com.example.agoodob.mobilesafe1c7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 2016-2-27
 * 第四个页面, 提示设置成功
 */
public class Step4Activity extends BaseSetupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step4);

        Button finish = (Button) findViewById(R.id.Step4Finish);
        SharedPreferences mPref = getSharedPreferences("config", MODE_PRIVATE);
        mPref.edit().putBoolean("configed", true).apply();

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrevPage();
            }
        });
    }

    @Override
    public void showNextPage() {

    }

    @Override
    public void showPrevPage() {
        Intent i = new Intent(Step4Activity.this, LostFindActivity.class);
        startActivity(i);
        finish();
        // 两个界面切换的动画
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
    }
}
