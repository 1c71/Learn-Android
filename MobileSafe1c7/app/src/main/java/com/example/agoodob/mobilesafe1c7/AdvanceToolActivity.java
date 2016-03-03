package com.example.agoodob.mobilesafe1c7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 2016-3-2
 *
 * "高级工具"
 */
public class AdvanceToolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_tool);
    }

    public void numberAddressQuery(View v){
        startActivity(new Intent(this, AddressActivity.class));
    }
}
