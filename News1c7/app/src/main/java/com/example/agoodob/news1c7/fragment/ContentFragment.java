package com.example.agoodob.news1c7.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ViewUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;

import com.example.agoodob.news1c7.R;
import com.example.agoodob.news1c7.base.BasePager;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.rg_group)
    private RadioGroup rg_group;

    @ViewInject(R.id.vp_content)
    private ViewPager mViewPager;

    private ArrayList<BasePager> mPagerList;


    @Override
    public View initViews(){
        View  v = View.inflate(mActivity, R.layout.fragment_content, null);
        com.lidroid.xutils.ViewUtils.inject(this, v);
        return v;
    }


    @Override
    public void initData(){
        rg_group.check(R.id.rb_home); // 默认勾选首页

        // 初始化5个子页面
        mPagerList = new ArrayList<BasePager>();
        for (int i = 0; i < 5; i++) {
            BasePager bp = new BasePager(mActivity);
            mPagerList.add(bp);
        }

        mViewPager.setAdapter(new ContentAdapter());
    }

    class ContentAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mPagerList.get(position).mRootView);
            return mPagerList.get(position).mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
