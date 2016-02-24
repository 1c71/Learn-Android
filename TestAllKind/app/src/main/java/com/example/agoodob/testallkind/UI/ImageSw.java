package com.example.agoodob.testallkind.UI;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.app.Activity;

import android.support.v4.content.ContextCompat;
import android.view.View;

import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.agoodob.testallkind.R;

import java.util.ArrayList;
import java.util.List;

/*
* ImageSwitcher 图片切换
* 2016-2-24
* 能运行
*
* 1 xml 那边写个 ImageSwitcher 控件
* 2 java 里 findViewById
* 3 imageSwitcher.setFactory(new ViewFactory(){ ... })
* 4 setImageDrawable 就可以更改图片了
*
* 整体思路:
* 用个 list 装 drawable
* 每当点击了 prev 按钮 或者 next 按钮就换个 list 索引，
* 然后 setImageDrawable 就行了
*
* 注意:
* setFactory 要写在 setImageDrawable 前面，不然会报错
* */
public class ImageSw extends Activity implements ViewFactory{

    private ImageSwitcher mImageSwitcher;
    private Button prevImageButton, nextImageButton;
    private int index = 0 ;    //用来代表图片索引, 因为我们要用列表来装图片资源
    private List<Drawable> list = new ArrayList<Drawable>();  // 图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_sw);

        prevImageButton = (Button) findViewById(R.id.prevImage);
        nextImageButton = (Button) findViewById(R.id.nextImage);
        mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher22);

        list.add(ContextCompat.getDrawable(ImageSw.this, R.drawable.aa));
        list.add(ContextCompat.getDrawable(ImageSw.this, R.drawable.bb));
        list.add(ContextCompat.getDrawable(ImageSw.this, R.drawable.cc));
        //这种写法不行list.add(getResources().getDrawable(R.drawable.noti));
        mImageSwitcher.setFactory(this);

        if(list.size()>0){
            mImageSwitcher.setImageDrawable(list.get(0));
        }

        // 上一个
        prevImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                if (index < 0) {
                    index = list.size() - 1;
                }
                mImageSwitcher.setImageDrawable(list.get(index));
            }
        });

        // 下一个
        nextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if (index > list.size() - 1) {
                    index = 0;
                }
                mImageSwitcher.setImageDrawable(list.get(index));
            }
        });
    }

    // 没具体理解这个干啥的
    @Override
    public View makeView() {
        return new ImageView(ImageSw.this);
    }
}