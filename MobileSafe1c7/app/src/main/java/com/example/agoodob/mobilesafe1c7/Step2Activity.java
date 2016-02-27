package com.example.agoodob.mobilesafe1c7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class Step2Activity extends AppCompatActivity {

    private GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);
        Button prev = (Button) findViewById(R.id.Step2Previous);
        Button next = (Button) findViewById(R.id.Step2Next);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });


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
                return false;
            }
        });

    }


    public void showPrevPage(){

    }

    public void next(){
        Intent i = new Intent(Step2Activity.this, Step3Activity.class);
        startActivity(i);
        finish();
        // 两个界面切换的动画
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
    }

    public void prev(){
        Intent i = new Intent(Step2Activity.this, Step1Activity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        gd.onTouchEvent(e); // 碰到手势事件就把这个事件交给我们自己写的手势识别器
        return super.onTouchEvent(e);
    }
}
