package com.example.agoodob.customwidget;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 *
 * 2016-3-3
 * 优酷菜单
 * 优酷旧版的三级环形菜单 - 黑马52期  day53
 * 
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean isShowLevel2 = true; // 2级菜单
    private boolean isShowLevel3 = true; // 3级菜单
    private boolean isShowMenu = true; // 包括1级，2级，3级菜单

    private RelativeLayout level1,level2,level3;
    ImageView iv_home, iv_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_menu = (ImageView) findViewById(R.id.iv_menu);
        level1 = (RelativeLayout) findViewById(R.id.level1_rl);
        level2 = (RelativeLayout) findViewById(R.id.level2_rl);
        level3 = (RelativeLayout) findViewById(R.id.level3_rl);
    }

    private void initListener() {
        iv_home.setOnClickListener(this);
        iv_menu.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.level1_rl:
                if(isShowLevel2){
                    // 需要隐藏

                    int startOffset = 0;

                    if(isShowLevel3){
                        isShowLevel3 = false;
                        AnimUtil.closeMenu(level3, startOffset);
                        startOffset += 200;
                    }

                    isShowLevel2 = false;
                    AnimUtil.closeMenu(level2, startOffset);

                } else {
                    // 需要显示
                    isShowLevel2 = true;
                    AnimUtil.showMenu(level2, 0);
                }
                break;
            case R.id.iv_menu:
                Toast.makeText(MainActivity.this, "yijing dianji ", Toast.LENGTH_SHORT).show();
                if(isShowLevel3){
                    // 需要隐藏
                    isShowLevel3 = false;
                    AnimUtil.closeMenu(level3, 0);

                } else {
                    // 需要显示
                    isShowLevel3 = true;
                    AnimUtil.showMenu(level3, 0);
                }
                break;
        }


    }
}

