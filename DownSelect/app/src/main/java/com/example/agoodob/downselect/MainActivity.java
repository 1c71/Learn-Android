package com.example.agoodob.downselect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 2016-3-6
 * 下拉选择, 原生太丑所以我们自己写
 *
 * 其实就是把一个 ListView 在监听到点击事件的时候以 PopupWindow 方式弹出来
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private ImageView iv_select;
    private ArrayList<String> list = new ArrayList<String>();
    private MyAdapter myAdapter;
    private ListView lv;
    private int popupWindowHeight = 300;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
        initData();

    }

    /*
    用于显示下拉列表
     */
    private void showNumberList(){
        if (popupWindow == null){

            popupWindow = new PopupWindow(lv,
                    editText.getWidth(), popupWindowHeight);
            popupWindow.showAsDropDown(editText, 0, 0);
        }
    }

    private void initData() {
        for (int i=1; i<=15; i++){
            list.add(9000+i+"");
        }
        initListView();
    }

    private void initListener() {
        iv_select.setOnClickListener(this);

    }

    private void initView() {
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.et);
        iv_select = (ImageView) findViewById(R.id.iv_select);

    }

    private void initListView(){
        lv = new ListView(this);
        myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et:
                showNumberList();
                break;
        }
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(MainActivity.this, R.layout.adapter_list, null);
            // 上下文，布局，viewgroup
            TextView tv_number = (TextView) v.findViewById(R.id.tv_number);
            ImageView iv_delete = (ImageView) v.findViewById(R.id.iv_deleve);

            tv_number.setText(list.get(position));
            iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

            return null;
        }
    }
}
