package com.example.agoodob.myapplication;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
* 这个项目是高尔夫计分板，18个洞洞，计数
* 然后用 SharedPreferences 保存数据，这样关掉再开也还有数据
* */
public class MainActivity extends ListActivity {

    private static final String PREFS_FILE = "com.xxx.golfscorecar.perferences";
    private static final String KEY_STROKECOUNT = "key_strokecount";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Hole[] mHoles = new Hole[18];
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        // 新建一堆 HOLE 对象, 这就是我们的数据了
        int strokes = 0; // 初始化分数
        for(int i=0; i<mHoles.length; i++){
            strokes = mSharedPreferences.getInt(KEY_STROKECOUNT + i, 0); // 试着去取数据
            mHoles[i] = new Hole("Hole " + (i+1) + " :", strokes );
        }

        // 注意这个 ListAdapter 是我们自己的
        mListAdapter = new ListAdapter(this, mHoles);
        setListAdapter(mListAdapter);

        // 一个类继承 ListActivity, 然后 setListAdapter
        // 这个 ListAdapter 是我们自己写的, 在 getView 方法里有些用的是哪个布局文件
        // R.layout.list_item

    }

    @Override
    protected void onPause() {
        super.onPause();

        for(int i = 0; i < mHoles.length; i++) {
            mEditor.putInt(KEY_STROKECOUNT + i, mHoles[i].getStrokeCount());
        }
        mEditor.apply();
    }
}
