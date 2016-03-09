package com.example.agoodob.news1c7;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.agoodob.news1c7.utils.PrefTool;

import java.util.ArrayList;

/**
 * 第一次打开 APP 看到的引导页
 */
public class GuideActivity extends Activity {

    private static final int[] mImageId = new int[]{
            R.drawable.guide_1,
            R.drawable.guide_2,
            R.drawable.guide_3
    };

    private ViewPager vp_guide;
    ArrayList<ImageView> mImageViewList;
    private LinearLayout llPointGroup; // 放圆点的控件
    int mPointDist;  // 圆点之间的距离
    private View redPoint; // 红点
    private Button btn_start; // 开始按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        llPointGroup = (LinearLayout) findViewById(R.id.point_group);
        redPoint = findViewById(R.id.red_point);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefTool.setBoolean(GuideActivity.this, "guide_page", true);
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });

        initView();
        vp_guide.setAdapter(new GuideAdapter());
        vp_guide.addOnPageChangeListener(new PageListener());
    }


    private void initView(){

        // 准备好数据，弄了三个 ImageView ，而且也设置好了
        mImageViewList = new ArrayList<ImageView>();

        for(int i=0; i<mImageId.length; i++){
            ImageView v = new ImageView(this);
            v.setBackgroundResource(mImageId[i]);
            mImageViewList.add(v);
        }

        // 初始化引导页的小圆点
        for(int i=0; i<mImageId.length; i++){
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);
            point.setLayoutParams(new LinearLayout.LayoutParams(100, 100)); // 设置圆点的大小
            // 这里单位不能设置 dp，怪麻烦的，适配问题后面会说

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(20, 20);
            if(i > 0){
                param.leftMargin = 120; // 设置圆点间隔
            }
            point.setLayoutParams(param);

            llPointGroup.addView(point);
        }


        // 获取视图树, 对layout结束事件进行监听
        // 这一段的主要目的是:  获得第2个点和第1个点之间的距离 mPointDist
        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener(){

                    // 当layout执行结束后回调此方法
                    @Override
                    public void onGlobalLayout() {
                        System.out.println("layout 结束");
                        llPointGroup.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        mPointDist = llPointGroup.getChildAt(1).getLeft()
                                - llPointGroup.getChildAt(0).getLeft();
                        System.out.println("圆点距离:" + mPointDist);
                    }
                });

    }

    class GuideAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mImageId.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 当滑动的时候，计算百分比，从而才能知道要移动圆点多少距离
     */
    class PageListener implements ViewPager.OnPageChangeListener{

        /**
         * 当你滑动时 这个方法会被调用多次
         * @param position
         * @param positionOffset  移动百分比
         * @param positionOffsetPixels  移动具体距离
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            int len = (int) (mPointDist * positionOffset) + position * mPointDist;
            System.out.println("position 是: " + position);
            System.out.println("positionOffset 是: " + positionOffset);
            System.out.println("positionOffsetPixels 是: " + positionOffsetPixels);
            System.out.println("红点要移动的距离是: " + len);

            RelativeLayout.LayoutParams para = (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
            // 获取当前红点的布局参数

            para.leftMargin = len; // 设置左边距

            redPoint.setLayoutParams(para);
        }

        @Override
        public void onPageSelected(int position) {
            if(position == mImageId.length - 1){ // 如果到了最后一个页面
                btn_start.setVisibility(View.VISIBLE);
            } else {
                btn_start.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
