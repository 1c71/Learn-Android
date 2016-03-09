package com.example.agoodob.news1c7;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.agoodob.news1c7.utils.PrefTool;

public class SplashActivity extends Activity {

    RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);

        startAnim();
    }

    public void startAnim(){

        int animTime = 1100; // 动画持续时间

        AnimationSet animationSet = new AnimationSet(false);

        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(animTime);
        rotate.setFillAfter(true);

        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(animTime);
        scale.setFillAfter(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(animTime);
        alphaAnimation.setFillAfter(true);


        animationSet.addAnimation(rotate);
        animationSet.addAnimation(scale);
        animationSet.addAnimation(alphaAnimation);

        // 动画监听一定要写在动画开始前
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jumpNextPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rlRoot.startAnimation(animationSet);
    }

    /**
     * 判断有没有进入过引导页，从而决定跳到是跳到引导页还是主页面
     */
    private void jumpNextPage() {

        boolean been_guide = PrefTool.getBoolean(this, "guide_page", false);

        if(been_guide){
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
        } else {
            Intent i = new Intent(SplashActivity.this, GuideActivity.class);
            startActivity(i);
        }

        finish();
    }
}
