package com.example.agoodob.news1c7;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.agoodob.news1c7.fragment.ContentFragment;
import com.example.agoodob.news1c7.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * 2016-3-9
 * "智慧北京"
 *
 * 黑马52期安卓教程的"智慧北京"项目，实际上是个看新闻的 APP
 *
 * 注意 extends SlidingFragmentActivity
 */
public class MainActivity extends SlidingFragmentActivity {

    private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
    private static final String FRAGEMENT_CONTENT = "fragment_content";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.left_menu); // 设置侧边栏
        SlidingMenu slidingMenu = getSlidingMenu(); // 获取侧边栏对象
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN); // 设置全屏触摸滑动，而不是边缘滑动
        slidingMenu.setBehindOffset(200); // 预留屏幕的宽度

        initFragment();
    }

    /**
     * 初始化 Fragment,
     */
    public void initFragment(){
        android.support.v4.app.FragmentManager
                fm = getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction
                transation = fm.beginTransaction();

        transation.replace(R.id.fl_left_menu, new com.example.agoodob.news1c7.fragment.LeftMenuFragment(), FRAGMENT_LEFT_MENU);
        transation.replace(R.id.fl_left_menu, new ContentFragment(), FRAGEMENT_CONTENT);

        transation.commit();

//        fm.findFragmentByTag(FRAGEMENT_CONTENT);
    }

}
