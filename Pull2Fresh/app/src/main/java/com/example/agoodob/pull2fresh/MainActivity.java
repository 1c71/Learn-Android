package com.example.agoodob.pull2fresh;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 2016-3-7
 * 下拉刷新 - 黑马52期 - day54自定义控件第二天
 *
 * 整个核心点就是用 ListView 的 addHeaderView 做下拉刷新的提示
 * 以及 负的paddingTop 来隐藏下拉刷新的提示
 * 下拉时就改变这个 -paddingTop 的值
 * 然后做几个状态常量，来表示不同状态。松手时根据状态做出响应
 *
 */
public class MainActivity extends AppCompatActivity {

    RefreshListView refreshListView;
    private ArrayList<String> list = new ArrayList<String>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //只改当前 activity
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        refreshListView = (RefreshListView) findViewById(R.id.refreshListView);
    }

    private void initData() {

        for(int i=0; i<=15; i++){
            list.add("listView 原来的数据: "+i);
        }

        // 这种做法是为了获得有效的高度，然后根据这个高度设置负边距
        final View headerView = View.inflate(this, R.layout.layout_header, null);
//        headerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
//                .OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int headerHeight = headerView.getHeight();
//                System.out.println("内部高度:  " + headerHeight);
//                headerView.setPadding(0, -headerHeight, 0, 0);// 通过负数 padding 的方式隐藏
//                // addHeaderView 一定要在 setAdapter 之前调用
//                refreshListView.addHeaderView(headerView);
//                // addHeaderView 之后这个视图会在列表上方显示，不是下拉才显示。
//            }
//        });

//        headerView.measure(0, 0); //  主动通知系统去测量
//        int headerHeight = headerView.getMeasuredHeight();
//        headerView.setPadding(0, -headerHeight, 0, 0);
//        refreshListView.addHeaderView(headerView);



        adapter = new MyAdapter();
        refreshListView.setAdapter(adapter);

    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(MainActivity.this);
            tv.setPadding(20, 20, 20, 20);
            tv.setTextSize(18);
            tv.setText(list.get(position));
            return tv;
            // 如果是 return null 会闪退，而且 android studio 不会提示
        }
    }


}
