package com.example.agoodob.customwidget;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 *
 * 2016-3-3
 * 优酷旧版的三级环形菜单 - 黑马52期  day53
 * 还没测试
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean isShowLevel2 = false;
    private boolean isShowLevel3 = false;

    RelativeLayout level1_rl;
    RelativeLayout level2_rl;
    RelativeLayout level3_rl;
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        level1_rl = (RelativeLayout) findViewById(R.id.level1_rl);
        level2_rl = (RelativeLayout) findViewById(R.id.level2_rl);
        level3_rl = (RelativeLayout) findViewById(R.id.level3_rl);
        menu = (ImageView) findViewById(R.id.iv_menu);
        level1_rl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.level1_rl:
                if(isShowLevel2){
                    // 需要隐藏
                    isShowLevel2 = false;
                    AnimUtil.closeMenu(level2_rl);

                } else {
                    // 需要显示
                    isShowLevel2 = true;
                    AnimUtil.showMenu(level2_rl);
                }
                break;
            case R.id.iv_menu:
                if(isShowLevel3){
                    // 需要隐藏
                    isShowLevel3 = false;
                    AnimUtil.closeMenu(level3_rl);

                } else {
                    // 需要显示
                    isShowLevel3 = true;
                    AnimUtil.showMenu(level3_rl);
                }
                break;
        }


    }
}

