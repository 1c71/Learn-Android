package com.example.agoodob.photogallery;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 *  2016-3-22, 3-23
 *  Android编程权威指南 - 第26章 HTTP与后台任务
 *
 *  项目6
 *  一个看图片的应用
 *  用了 Flickr 的 API
 *  https://www.flickr.com/ 要翻墙
 *  https://www.flickr.com/services/api/
 *
 *  建议电脑翻墙 + Genymotion
 *
 */
public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PhotoGalleryFragment();
    }


}
