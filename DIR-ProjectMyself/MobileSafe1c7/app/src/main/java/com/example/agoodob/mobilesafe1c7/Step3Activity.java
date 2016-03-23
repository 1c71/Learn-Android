package com.example.agoodob.mobilesafe1c7;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * 2016-2-27
 *
 */
public class Step3Activity extends BaseSetupActivity {

    private EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);

        Button prev = (Button) findViewById(R.id.Step3Prev);
        Button next = (Button) findViewById(R.id.Step3Next);
        et_phone = (EditText) findViewById(R.id.et_phone);

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

    public void selectContact(View v){
        Intent i = new Intent(this, ContactActivity.class);
        startActivityForResult(i, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK){

        }

        String phone = data.getStringExtra("phone");
        Toast.makeText(Step3Activity.this, phone, Toast.LENGTH_SHORT).show();
        phone = phone.replaceAll("-","");
        et_phone.setText(phone);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
