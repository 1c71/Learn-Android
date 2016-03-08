package com.example.agoodob.slidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by agoodob on 2016/3/8.
 */
public class SlideMenu extends ViewGroup {

    private View menuView, mainView;
    private int screenHeight = 0;

    public SlideMenu(Context context) {
        super(context);
        init();
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){

    }

    /**
     * 当1级的子 view 全部加载完后调用，这里获取引用
     * 注意这里无法获取宽高
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        menuView = getChildAt(0);
        mainView = getChildAt(1);
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

        // 测量所有子 view 的宽高
        menuView.measure(menuView.getLayoutParams().width, menuView.getLayoutParams().height);
        // 直接用slidemenu 的测量参数，因为 mainView 需要填充满整个窗体
        mainView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        menuView.layout(-menuView.getLayoutParams().width, 0, r,
                menuView.getMeasuredHeight());

    }
}















