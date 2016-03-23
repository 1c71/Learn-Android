package com.example.agoodob.slidemenu;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import android.widget.ImageView;
import android.view.View.OnClickListener;

/**
 * 2016-3-12
 * 侧滑菜单 自己实现
 * 黑马52 day54 自定义控件
 *
 * 实现重点：
 *  1 java: 做一个自定义控件，继承 ViewGroup
 *  2 xml: 做两个布局，一个是侧滑菜单，一个是主屏内容
 *  3 xml: 把自定义控件包含2个布局
 *  4 java: 写 onMeasure onLayout
 *  5 java: 写自定义控件的 onTouchEvent, 使得可以响应滑动手势
 *  6 没了
 *
 */
public class MainActivity extends AppCompatActivity {

    private ImageView btn_back;
    private SlideMenu slideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_back = (ImageView) findViewById(R.id.btn_back);
        slideMenu = (SlideMenu) findViewById(R.id.slideMenu);

        btn_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                slideMenu.switchMenu();
            }
        });
    }
}
