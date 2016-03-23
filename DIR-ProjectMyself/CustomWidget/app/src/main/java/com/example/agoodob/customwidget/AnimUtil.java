package com.example.agoodob.customwidget;

import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * 2016/3/3
 * 这个类负责动画效果
 */
public class AnimUtil {
    public static void closeMenu(RelativeLayout rl, int startOffset){

        // 把布局下的 child 都去掉事件响应
        for(int i=0;  i<rl.getChildCount(); i++){
            rl.getChildAt(i).setEnabled(false);
        }

        RotateAnimation anim = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 1);

        anim.setDuration(500);
        anim.setFillAfter(true); // 动画结束后保存当时的状态
        anim.setStartOffset(startOffset);
        rl.startAnimation(anim);
    }

    public static void showMenu(RelativeLayout rl, int startOffset){

        for(int i=0;  i<rl.getChildCount(); i++){
            rl.getChildAt(i).setEnabled(true);
        }

        RotateAnimation anim = new RotateAnimation(-180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 1);

        anim.setDuration(500);
        anim.setFillAfter(true);
        anim.setStartOffset(startOffset);
        rl.startAnimation(anim);
    }

}
