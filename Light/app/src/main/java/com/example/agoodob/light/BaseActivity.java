package com.example.agoodob.light;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by agoodob on 2016/2/4.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
