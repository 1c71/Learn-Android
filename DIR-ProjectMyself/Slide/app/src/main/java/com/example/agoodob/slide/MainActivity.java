package com.example.agoodob.slide;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 2016-3-6
 * 广告轮播，其实就是幻灯片效果，非常基础的效果
 *
 * 用 ViewPager，像 ListView 一样也需要 Adapter
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView tv_intro;
    private LinearLayout dot_layout;
    private ArrayList<AD> list = new ArrayList<AD>(); // 数据对象

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1); // 下一个
            handler.sendEmptyMessageDelayed(0, 4000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDots();
        initListener();
        initData();
    }

    // view Pager 监听器
    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }

            /**
             * 换页面了就更新下介绍
             */
            @Override
            public void onPageSelected(int position) {
                updateIntroAndDots();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void updateIntroAndDots(){
        int currentPage = viewPager.getCurrentItem() % list.size();
        tv_intro.setText(list.get(currentPage).getIntro());

        for(int i=0; i<dot_layout.getChildCount(); i++){
            dot_layout.getChildAt(i).setEnabled(i==currentPage);

        }
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tv_intro = (TextView) findViewById(R.id.tv_intro);
        dot_layout = (LinearLayout) findViewById(R.id.dot_layout);
    }


    private void initData() {
        list.add(new AD(R.drawable.a, "2016年安卓程序员应该何去何从超过屏幕宽度,tebie tebie d chang asduhaosudhaoufhaouefhaefafe"));
        list.add(new AD(R.drawable.b, "哈勃望远镜调过来对着地球会有什么效果？"));
        list.add(new AD(R.drawable.c, "标题3介绍在此2222222222222222222222222222222222222 "));
        list.add(new AD(R.drawable.d, "标题4介绍在此"));
        list.add(new AD(R.drawable.e, "标题5介绍在此"));

        viewPager.setAdapter(new MyPageAdapter());

        int centerValue = Integer.MAX_VALUE / 2;
        int value = centerValue % list.size();
        viewPager.setCurrentItem(centerValue - value);

        updateIntroAndDots();

        handler.sendEmptyMessageDelayed(0, 4000);
    }

    /**
     * 初始化幻灯片下方的圆点
     */
    private void initDots(){
        for (int i=0; i<=list.size(); i++){
            Toast.makeText(MainActivity.this, "循环："+i, Toast.LENGTH_SHORT).show();
            View v = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(18,18);  // width,height. 宽高都是8
            if (i!=0){
                params.leftMargin = 5;
            }
            v.setLayoutParams(params);
            v.setBackgroundResource(R.drawable.selector_dot);
            dot_layout.addView(v);
        }
    }

    class MyPageAdapter extends PagerAdapter{

        /*
         * 有多少个 Page
         */
        @Override
        public int getCount() {
            return 100;
        }

        /*
            滑动的时候，
            如果滑到了下一个代表需要新的 view
            true: 表示不去创建，用缓存
            falst: 去重新创建
        */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
            // 一般都这样写，官方文档也这样建议
        }

        /**
         * 这个方法负责将数据设置给 view
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View v = View.inflate(MainActivity.this, R.layout.adapter_view, null);

            ImageView imageView = (ImageView) v.findViewById(R.id.image);

            AD ad = list.get(position % list.size());

            imageView.setImageResource(ad.getIconResID());

            container.addView(v); // 一定不能少

            return v;
        }

        /**
         * 销毁 page
         * @param container
         * @param position 当前需要销毁第几个 view
         * @param object 当前需要销毁的 view
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

}
