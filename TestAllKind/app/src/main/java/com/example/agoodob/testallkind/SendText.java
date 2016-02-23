package com.example.agoodob.testallkind;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
* 发短信
* */
public class SendText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_text);
        Button b = (Button) findViewById(R.id.sendTextButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_phone = (EditText) findViewById(R.id.sendTextNumber);
                String phone = et_phone.getText().toString();

                EditText et_content = (EditText) findViewById(R.id.sendTextContent);
                String content = et_content.getText().toString();

                SmsManager sm = SmsManager.getDefault();
                sm.sendTextMessage(phone, null, content, null, null);
                // 第二个参数是 SMSC 短信中心，不用管它，不设置才对
                // 第四个参数是 pendingIntent
                // 第五个参数是 deliveryIntent
            }
        });
    }
}
