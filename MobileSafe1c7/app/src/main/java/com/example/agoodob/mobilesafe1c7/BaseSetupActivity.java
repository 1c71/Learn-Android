package com.example.agoodob.mobilesafe1c7;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * 2016/3/1
 * 设置引导页 的基类
 * 设置引导页就是 Step1Activity Step2Activity Step3Activity Step4Activity
 * 不需要在 Mainifest.xml 里注册，因为不需要界面展示
 *
 * 这里主要做的是手势识别
 *
 * 手势检测:
 * 写个 GestureDetector
 * 在 Activity 里写个 onTouchEvent, 然后 onTouchEvent 里调 GestureDetector
 */
public abstract class BaseSetupActivity extends Activity{

    private GestureDetector gd;
    SharedPreferences mPref;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        gd = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // e1 是滑动期间
                // e2 是滑动重点
                // vX 是水平速度
                // vY 是垂直速度

                // 这一步判断是为了防止斜对角的滑动
                if (Math.abs(e2.getRawY() - e1.getRawY()) > 100){
                    return true;
                }

                // 判断滑动速度是否太慢
                if (Math.abs(velocityX) < 100){
                    return true;
                }

                // getX 可能会拿到其它控件，基于它为0
                // 整个屏幕的xy体系就用 getRawX
                if(e2.getRawX() - e1.getRawX() > 200){
                    // 代表向右滑动, 要去上一页
                    showPrevPage();
                    return true;

                } else if (e1.getRawX() - e2.getRawX() > 200){
                    // 代表向左滑动, 要去下一页
                    showNextPage();
                    return true;
                }

                return false;
            }
        });
    }

    // 用于鼠标点击时间的响应
    public void next(){
        showNextPage();
    }

    public void prev(){
        showPrevPage();
    }

    // 实现具体的 跳转到下一页
    public abstract void showNextPage();

    // 实现具体的 跳转到上一页
    public abstract void showPrevPage();

    @Override
    public boolean onTouchEvent(MotionEvent e){
        gd.onTouchEvent(e); // 碰到手势事件就把这个事件交给我们自己写的手势识别器
        return super.onTouchEvent(e);
    }
}
