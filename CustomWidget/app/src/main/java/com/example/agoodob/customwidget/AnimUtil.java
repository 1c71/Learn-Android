package com.example.agoodob.customwidget;

import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * 2016/3/3
 * 这个类负责动画效果
 */
public class AnimUtil {
    public static void closeMenu(RelativeLayout rl){
        RotateAnimation anim = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 1);

        anim.setDuration(500);
        anim.setFillAfter(true); // 动画结束后保存当时的状态
        rl.startAnimation(anim);
    }

    public static void showMenu(RelativeLayout rl){
        RotateAnimation anim = new RotateAnimation(-180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 1);

        anim.setDuration(500);
        anim.setFillAfter(true); // 动画结束后保存当时的状态
        rl.startAnimation(anim);
    }

}
