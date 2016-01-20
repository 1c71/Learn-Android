package com.example.agoodob.stormy.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.agoodob.stormy.R;
import com.example.agoodob.stormy.adapter.DayAdapter;
import com.example.agoodob.stormy.weather.Day;

public class DailyForecastActivity extends ListActivity {

    private Day[] mDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast2);

        Intent intent = getIntent();
        mDays = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);


        DayAdapter adapter = new DayAdapter(this, mDays);


        /*
        // Adapter 的简单应用例子, 只有纯字符串
        String[] daysOfTheWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                daysOfTheWeek);
        // ArrayAdapter 是个可以处理 Generic type 的 class
        // 这里传入 String 告诉它处理的是 String
        setListAdapter(adapter);
        */
    }
}
