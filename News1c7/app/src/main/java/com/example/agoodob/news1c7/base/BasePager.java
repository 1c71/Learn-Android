package com.example.agoodob.news1c7.base;


import android.app.Activity;

import com.example.agoodob.news1c7.R;
import android.view.View;
import android.widget.TextView;

/**
 * 主页下5个子页面的基类
 */
public class BasePager {

    public Activity mActivity;
    public View mRootView;  // 布局对象
    public TextView tvTitle;


    public BasePager(Activity a){
        mActivity = a;
        initView();
        initData();
    }

    public void initView(){
        mRootView = View.inflate(mActivity, R.layout.base_pager, null);

    }


    public void initData(){

    }

}
