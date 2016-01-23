package com.example.agoodob.funfacts;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class FunFactsActivity extends AppCompatActivity {

    private static final String TAG = FunFactsActivity.class.getSimpleName();
    private static final String KEY_FACT = "KEY_FACT";
    private static final String KEY_COLOR = "KEY_COLOR";
    // 与其写死 FunfactsActitivy, 写成一个字符串 "FunfactActivity"
    // 我们可以让它跟着 类名变
    // 重构工具会认得这个名字

    private FactBook mFactBook = new FactBook();
    private ColorWheel mColorWheel = new ColorWheel();
    private TextView mFactTextView;
    private Button mShowFactButton;
    private RelativeLayout rl;
    private String mFact;
    private int mColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        mFactTextView = (TextView) findViewById(R.id.faceTextView);
        mShowFactButton = (Button) findViewById(R.id.button);
        rl = (RelativeLayout) findViewById(R.id.RLayout);

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mFact = mFactBook.getFact();
                mColor = mColorWheel.getColor();

                mFactTextView.setText(mFact);
                rl.setBackgroundColor(mColor);
                mShowFactButton.setTextColor(mColor);
            }
        };
        mShowFactButton.setOnClickListener(listener);
    }

    // 切换横屏的时候保存状态
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_FACT, mFact);
        outState.putInt(KEY_COLOR, mColor);
    }

    // 恢复状态
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mFact = savedInstanceState.getString(KEY_FACT);
        mColor = savedInstanceState.getInt(KEY_COLOR);

        mFactTextView.setText(mFact);
        rl.setBackgroundColor(mColor);
        mShowFactButton.setTextColor(mColor);
    }
}



















