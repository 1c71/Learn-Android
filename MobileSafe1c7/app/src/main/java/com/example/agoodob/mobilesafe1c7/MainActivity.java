package com.example.agoodob.mobilesafe1c7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 2016-2-25
 *
 * 手机卫士项目 (黑马52期, day40开始)
 * 主页面
 *
 * 还没测成功访问检查更新网址，因为手机访问电脑网站的问题
 *
 */
public class MainActivity extends AppCompatActivity {


    private String[] mItems = new String[]{"清理加速", "流量监控", "防盗功能", "软件管理",
            "手机杀毒", "进程管理", "高级工具", "拦截骚扰", "设置中心"};

    private int[] mPics = new int[]{R.drawable.icon_boat,
            R.drawable.icon_bus, R.drawable.icon_stamp,
            R.drawable.icon_drink, R.drawable.icon_fork,
            R.drawable.icon_sign, R.drawable.icon_sliper,
            R.drawable.icon_tree, R.drawable.icon_water
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(new MainAdapter());
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 8:
                        // 设置中心
                        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(i);
                        break;
                }
            }
        });

    }

    class MainAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = View.inflate(MainActivity.this,
                    R.layout.main_grid_view, null);

            ImageView ivItem = (ImageView) view.findViewById(R.id.iv_image);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);

            tvItem.setText(mItems[position]);
            ivItem.setImageResource(mPics[position]);
            return view;
        }
    }
}
