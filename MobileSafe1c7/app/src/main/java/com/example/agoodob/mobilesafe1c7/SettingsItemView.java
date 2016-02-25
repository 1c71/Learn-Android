package com.example.agoodob.mobilesafe1c7;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 2016/2/25.
 */
public class SettingsItemView extends RelativeLayout {

    public SettingsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SettingsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public SettingsItemView(Context context) {
        super(context);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView(){
        View.inflate(getContext(), R.layout.view_setting_item, null);
    }
}
