package com.example.agoodob.pull2fresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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
 * 下拉刷新              黑马52期 - day54自定义控件第二天, 5集视频讲下拉刷新, 60,22,25,30,29 分钟
 *
 * 整个核心点是
 * 用 ListView 的 addHeaderView 做下拉刷新的提示
 * 以及 headerView 的 -paddingTop 来隐藏下拉刷新的提示
 * 下拉时改变 -paddingTop 的值
 * 然后做几个状态常量，来表示不同状态。松手时根据状态做出响应
 *
 * 2016-3-8 完成，samsung galaxy note5 测试没问题，只是动画效果不怎么好看
 * setPadding 瞬间就上去了
 */
public class MainActivity extends AppCompatActivity {

    RefreshListView refreshListView;
    private ArrayList<String> list = new ArrayList<String>();
    private MyAdapter adapter;

    private Handler handle = new Handler(){
        public void handleMessage(android.os.Message msg){
            // 更新 UI
            adapter.notifyDataSetChanged();
            refreshListView.completeRefresh();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        refreshListView = (RefreshListView) findViewById(R.id.refreshListView);
    }

    private void initData() {

        for(int i=0; i<=25; i++){
            list.add("ListView 原来的数据: "+i);
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
        refreshListView.setOnRefreshListener(new MyFreshListener());
    }

    // 刷新监听器
    class MyFreshListener implements RefreshListView.OnRefreshListener{
        @Override
        public void onPullRefresh() {
            requestData(false);
        }

        @Override
        public void onLoadingMore() {
            requestData(true);
        }
    }

    /**
     * 从服务器请求数据(模拟)
     * 上拉下拉都用这个方法
     *
     * @param isLoadingMore  false 代表是下拉(手指从上往下滑动, 试图去加载上方的数据), true 代表是上拉
     */
    private void requestData(final boolean isLoadingMore){
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(2000);

                if (isLoadingMore){
                    list.add("上拉刷新的数据 1");
                    list.add("上拉刷新的数据 2");
                    list.add("上拉刷新的数据 3");
                    list.add("上拉刷新的数据 4");
                } else {
                    list.add(0, "下拉刷新的数据 1");
                    list.add(0, "下拉刷新的数据 2");
                }

                // 在 UI 线程更新 UI
                handle.sendEmptyMessage(0);
            }
        }.start();
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
