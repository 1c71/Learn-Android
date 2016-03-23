package com.example.agoodob.mobilesafe1c7;


import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.TextView;

import com.example.agoodob.mobilesafe1c7.DB.Address;

/**
 * 2016-3-3
 * 归属地查询界面
 */
public class AddressActivity extends AppCompatActivity {

    private EditText etNumber;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        etNumber = (EditText) findViewById(R.id.et_number);
        tvResult = (TextView) findViewById(R.id.tv_result);

        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String address = Address.getAddress(s.toString());
                tvResult.setText(address);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public void query(View view){
        String number = etNumber.getText().toString().trim();
        if (!TextUtils.isEmpty(number)){
            String address = Address.getAddress(number);
            tvResult.setText(address);
        } else {
            // 动画的抖动效果
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
/*            shake.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float x) {
                    int y = 0;
                    return y;
                    //.....
                }
            });*/
            etNumber.startAnimation(shake);
            Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            //v.vibrate(2000);
            v.vibrate(new long[]{1000, 2000, 1000, 3000}, -1);
            // -1 表示只执行一次
        }
    }
}
