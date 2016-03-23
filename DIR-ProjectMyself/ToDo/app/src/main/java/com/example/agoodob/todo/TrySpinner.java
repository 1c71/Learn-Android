package com.example.agoodob.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TrySpinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_spinner);

        // ===========
        // 创建 ArrayAdapter
        // ===========
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plant_array, android.R.layout.simple_spinner_item);
        // 第三个是样式, simple_spinner_item 是系统自带的

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 一个是下拉菜单没展开的样式，这里这个是展开之后的样式。两个样式

        Spinner s = (Spinner) findViewById(R.id.spinner);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                Toast.makeText(TrySpinner.this, selected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // ================
        //
        // =================

        List<String> l = new ArrayList<String>();
        l.add("haha");
        l.add("hoho");

        //ArrayAdapter apt = new ArrayAdapter(this, R.layout.activity_try_spinner, R.id.textView, l);
        // 四个参数 第二个是布局文件，第三个必须是那个布局文件里的 textView, , 第四个是数据来源











    }
}
