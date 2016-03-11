package com.example.agoodob.news1c7.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

import com.example.agoodob.news1c7.base.BasePager;

/**
 * Created by agoodob on 2016/3/10.
 */
public class HomePager extends BasePager {
    public HomePager(Activity a) {
        super(a);
    }

    @Override
    public void initData() {
        tvTitle.setText("智慧北京");
        TextView text = new TextView(mActivity);
        text.setText("首页");
        text.setTextColor(Color.RED);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);
        flContent.addView(text);
    }
}
