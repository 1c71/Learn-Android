package com.example.agoodob.mobilesafe1c7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * 2016-2-27
 *
 */
public class Step3Activity extends BaseSetupActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);
        Button prev = (Button) findViewById(R.id.Step3Prev);
        Button next = (Button) findViewById(R.id.Step3Next);
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
    }

    public void next(){
        showNextPage();
    }

    public void prev(){
        showPrevPage();
    }

    @Override
    public void showNextPage() {
        Intent i = new Intent(Step3Activity.this, Step4Activity.class);
        startActivity(i);
        finish();
        // 两个界面切换的动画
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
    }

    @Override
    public void showPrevPage() {
        Intent i = new Intent(Step3Activity.this, Step2Activity.class);
        startActivity(i);
        finish();
        // 两个界面切换的动画
        overridePendingTransition(R.anim.trans_prev_in, R.anim.trans_prev_out);
    }
}
