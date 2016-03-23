package com.example.agoodob.testallkind;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 2016-2-24
 * View Pager
 */
public class ViewPagerTry extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerTitleStrip mPagerTitleStrip; // 标题
    private List<View> list; //存视图
    private List<String> titleList; //存标题

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerTitleStrip = (PagerTitleStrip) findViewById(R.id.pagerTitle);

        View view1 = LayoutInflater.from(ViewPagerTry.this).inflate(R.layout.tab1, null);
        View view2 = LayoutInflater.from(ViewPagerTry.this).inflate(R.layout.tab2, null);
        View view3 = LayoutInflater.from(ViewPagerTry.this).inflate(R.layout.tab3, null);

        list = new ArrayList<View>();
        list.add(view1);
        list.add(view2);
        list.add(view3);

        titleList = new ArrayList<String>();
        titleList.add("title1");
        titleList.add("title2");
        titleList.add("title3");

        mViewPager.setAdapter(new MyAdapter());
    }


    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(list.get(position)); //..
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            ((ViewPager)container).removeView(list.get(position)); //..
        }
    }
}
