package com.example.agoodob.heixin;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * 2016-3-14
 *
 * day83_即时通讯之黑马微信项目
 */
public class MainActivity extends FragmentActivity {

    private FragmentTabHost tabhost;
    private final static String TAG_CHAT = "chat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  1 初始化
        tabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabhost.setup(this, getSupportFragmentManager(),
                R.id.activity_home_container);


        // 2 新建 TabSpec
        TabHost.TabSpec spec = tabhost.newTabSpec(TAG_CHAT);
        spec.setIndicator("消息");

        // 3 添加 TabSpec
        tabhost.addTab(spec, MyFragment.class, null);

    }
}
