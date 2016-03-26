package com.example.agoodob.crashcourse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;



public class SplashActivity extends Activity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.splash_root);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);

        // 启动页动画
        AnimationSet animationSet = new AnimationSet(false);

        // 缩放
        ScaleAnimation scale = new ScaleAnimation(1.2f, 1, 1.2f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(SPLASH_TIME_OUT);
        scale.setFillAfter(true);

        // 透明度
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.6f, 1);
        alphaAnimation.setDuration(SPLASH_TIME_OUT);
        alphaAnimation.setFillAfter(true);

        animationSet.addAnimation(scale);
        animationSet.addAnimation(alphaAnimation);

        // 开始动画
        rl.startAnimation(animationSet);

    }
}
