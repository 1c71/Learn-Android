package com.example.agoodob.mobilesafe1c7;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.TextView;

/**
 * 2016-3-3
 * 软件管理
 */
public class SoftwareManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_manager);
        initUI();
    }

    private void initUI() {

        // 教程里是说一个是内存一个是SD卡，但是我这里显示的一样差不多
        // 不知道咋回事
        long rom_freeSpace = Environment.getDataDirectory().getFreeSpace();
        long sd_freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();

        TextView tv_freeMemo = (TextView) findViewById(R.id.tv_freeMemo);
        TextView tv_freeSpace = (TextView) findViewById(R.id.tv_freeSpace);

        //tv_freeMemo.setText(rom_freeSpace + ""); // 170 2634 2912
        tv_freeMemo.setText(Formatter.formatFileSize(this, rom_freeSpace));// 15.86 GB

        //tv_freeSpace.setText(sd_freeSpace + ""); // 169 8335 1296
        tv_freeSpace.setText(Formatter.formatFileSize(this, sd_freeSpace));// 15.82 GB

    }


}
