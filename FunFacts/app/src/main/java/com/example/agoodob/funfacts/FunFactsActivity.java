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
    // Declear our View variables
    private static final String TAG = FunFactsActivity.class.getSimpleName();
    // 与其写死 FunfactsActitivy, 写成一个字符串 "FunfactActivity"
    // 我们可以让它跟着 类名变
    // 重构工具会认得这个名字

    private FactBook mFactBook = new FactBook();
    private ColorWheel mColorWheel = new ColorWheel();
    private TextView mFactTextView;
    private Button mShowFactButton;
    private RelativeLayout rl;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        // 1. TextView xxx 声明好一个变量用于待会存
        // 2. findViewById 找到对应的元素然后赋值给 xxx
        // 3. new 一个 OnClickListener 变量, 比如赋值到 listner 里
        // 4. xxx.setOnClickListener(listener)  像这样把 listener 传到 setOnClickListener 里

        mFactTextView = (TextView) findViewById(R.id.faceTextView);
        mShowFactButton = (Button) findViewById(R.id.button);
        rl = (RelativeLayout) findViewById(R.id.RLayout);

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String fact = mFactBook.getFact();
                int color = mColorWheel.getColor();
                mFactTextView.setText(fact);
                rl.setBackgroundColor(color);
                mShowFactButton.setTextColor(color);

                
            }
        };
        mShowFactButton.setOnClickListener(listener);

        // Toast 显示一段时间后会自动消失，不是常驻的
//        Toast.makeText(FunFactsActivity.this, "白开水", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "We are login??");
        
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "FunFacts Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.agoodob.funfacts/http/host/path")
        );
        AppIndex.AppIndexApi.start(mClient, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "FunFacts Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.agoodob.funfacts/http/host/path")
        );
        AppIndex.AppIndexApi.end(mClient, viewAction);
        mClient.disconnect();
    }
}



















