package com.example.agoodob.mobilesafe1c7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 2016-2-27
 * 手机防盗, 第1个设置向导页
 *
 */
public class Step1Activity extends BaseSetupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        Button step1 = (Button) findViewById(R.id.Step1);

        step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }

    public void next(){
        showNextPage();
    }

    @Override
    public void showNextPage() {
        Intent i = new Intent(Step1Activity.this, Step2Activity.class);
        startActivity(i);
        finish();

        // 两个界面切换的动画
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
    }

    @Override
    public void showPrevPage() {

    }
}
