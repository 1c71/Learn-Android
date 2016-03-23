package com.example.agoodob.testallkind.UI;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.agoodob.testallkind.R;

/**
 * 这次课是讲 TabHost, 反正就是 Tab，上面有Tab 点了不同的显示不同页面
 * 没讲完视频就没了..
 *
 */
public class TryTabHost extends TabActivity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_tab_host);

        tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("系统菜单").setIndicator("Tab1"));

    }
}
