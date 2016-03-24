package com.example.agoodob.crashcourse;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Episode> episodes = new ArrayList<Episode>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.attach);
        // configure the SlidingMenu
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.shadow);
//        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setBehindWidth(800);
        menu.setMenu(R.layout.menu);



        Episode e = new Episode("a", "b", "c", "d");
        episodes.add(e);
        episodes.add(e);
        episodes.add(e);
        episodes.add(e);
        episodes.add(e);

        lv = (ListView) findViewById(R.id.episode);
        lv.setAdapter(new MyAdapter(this, episodes));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "asd", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, SlidingExample.class);
//                Intent i = new Intent(MainActivity.this, WebViewActivity.class);
//                Intent i = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(i);

            }
        });
    }



    private class MyAdapter extends ArrayAdapter<Episode> {

        public MyAdapter(Context c ,ArrayList<Episode> items) {
            super(c, 0, items);
            // super 的最后一个 items 参数如果不传就不会显示任何东西
        }
        @Override
        public int getCount() {
            return episodes.size();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if(convertView == null){
                convertView = getLayoutInflater()
                        .inflate(R.layout.list_item, parent, false);
            }

//            ImageView imageView = (ImageView)
//                    convertView.findViewById(R.id.gallery_item_imageView);
//            imageView.setImageResource(R.drawable.brian_up_close);
//            GalleryItem item = getItem(position);
//            mThumbnailThread.queueThumbnail(imageView, item.getUrl());

            return convertView;
        }
    }
}
