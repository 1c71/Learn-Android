package com.example.agoodob.slidemenu;

import android.content.Context;
import android.os.Messenger;
import android.text.style.TtsSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Scroller;

/**
 * 2016-3-12
 *
 * 两个构造方法
 *
 *
 * onFinishInflate()
 * onMeasure()  定宽高
 * onLayout()   定位置
 *
 *
 *
 *
 * 平滑的菜单滚动效果 实现方式
 *  1 自己 extends Animation
 *  2 用 Scroller 对象 - 这种方式简单，代码少
 * 
 */
public class SlideMenu extends ViewGroup {

    private View caidanView, mainView;
    // menuView 和 mainView 太容易看混，所以变成了 caidanView, mainView

    private Scroller scroller;

    private int screenHeight = 0;
    int menuWidth = 0;

    public SlideMenu(Context context) {
        super(context);
        init();
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        scroller = new Scroller(getContext());
    }


    /**
     * 是否拦截事件 true 拦截， false 不拦截
     *
     * 这个函数的目的是为了让侧边栏可以上下，又可以左右滑动
     * 重点是：
     *  当左右移动超过 x 像素时候，拦截事件，使得左右滑动事件可以响应
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) ev.getX() - downX;

                if(Math.abs(deltaX)>8){
                    return true;
                }

                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 当1级的子 view 全部加载完后调用，这里获取引用
     * 注意  这里无法获取子view的宽高
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mainView = getChildAt(0);
        caidanView = getChildAt(1);
        menuWidth = caidanView.getLayoutParams().width;
    }

    /**
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     * 这两个参数是系统测量 SlideMenu 时传入的参数，系统传入的参数。
     * 测量出 SlideMenu 的宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureSpec = MeasureSpec.makeMeasureSpec(menuWidth, MeasureSpec.EXACTLY);


        // 测量所有子 view 的宽高
        caidanView.measure(measureSpec, heightMeasureSpec);

        // 直接用slidemenu 的测量参数，因为 mainView 需要填充满整个窗体
        mainView.measure(widthMeasureSpec, heightMeasureSpec);


    }

    /**
     *
     * @param changed
     * @param l 当前子 view 在父 view 坐标系的 x 坐标
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.e("MAIN", "L: " + l + " T:  " + t + "  R: " + r + "  B:  " + b);

        caidanView.layout(
                -menuWidth, 0, 0, caidanView.getMeasuredHeight());

        mainView.layout(0, 0, r, b);

    }

    private int downX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                System.out.println("SLIDEMENU:  D");
                Log.e("MAIN", "d");
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int deltaX = moveX - downX;

                int newScrollX = getScrollX() - deltaX;

                // 禁止菜单滑多出本身的宽度
                if(newScrollX < -menuWidth){
                    newScrollX = -menuWidth;
                }
                // 禁止手指向左滑动，拉出右边的空白区域
                if(newScrollX > 0){
                    newScrollX = 0;
                }

                scrollTo(newScrollX,0);
                System.out.println("SLIDEMENU:  MOVE");
                Log.e("MAIN", "delta: " + deltaX + "  newScroll: " + newScrollX);
                downX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                // 松开手指之后

                ScrollAnim scrollAnimation;

//                if (getScrollX() > -menuWidth / 2){
////                    scrollTo(0, 0); // 关闭菜单(瞬间)
//                    scrollAnimation = new ScrollAnim(this, 0);
//                } else {
////                    scrollTo(-menuWidth, 0); // 打开菜单
//                    scrollAnimation = new ScrollAnim(this, -menuWidth);
//                }
//                startAnimation(scrollAnimation);


                if (getScrollX() > -menuWidth / 2){
                    // 关闭菜单
                    scroller.startScroll(getScrollX(), 0, 0-getScrollX(), 0, 400);
                    invalidate();
                } else {
                    scroller.startScroll(getScrollX(), 0, -menuWidth-getScrollX(), 0, 400);
                    invalidate();
                }

                break;
        }
        return true; // 不能返回 super..... 一定要返回 true
        /*
        * Returns:
        True if the event was handled, false otherwise.
        */
    }

    /**
     * Scroll 不会主动调用这个方法
     * invalidate() 会调用 computeScroll
     */
    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()){ // true 代表动画没有结束
            scrollTo(scroller.getCurrX(), 0);
            invalidate(); // 递归
        }
    }

    /**
     * 切换菜单开关
     * 相当于一个 trigger
     */
    public void switchMenu() {
        if (getScrollX() == 0){
            // 需要打开
            openMenu();
        } else {
            // 需要关闭
            closeMenu();
        }
    }


    public void closeMenu(){
        scroller.startScroll(getScrollX(), 0, 0-getScrollX(), 0, 400);
        invalidate();
    }

    public void openMenu(){
        scroller.startScroll(getScrollX(), 0, -menuWidth-getScrollX(), 0, 400);
        invalidate();
    }




}


