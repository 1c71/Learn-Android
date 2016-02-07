package com.example.agoodob.testallkind.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.agoodob.testallkind.R;

/*
* 这一节全是用右上角那个菜单的视频, 菜单选中跳页面
*
*
* */
public class TryMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_try, menu);

        //用 Java 做菜单
        //MenuItem menuItem = menu.add(1001, 100, 1, "安卓菜单");
        //MenuItem menuItem2 = menu.add(1001, 101, 1, "AA菜单");
        //MenuItem menuItem3 = menu.add(1001, 102, 1, "BB菜单");
        //menuItem3.setShortcut('c', 'c');
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 100:
                Intent i = new Intent(TryMenu.this, TryMenuJump.class);
                item.setIntent(i);
                Toast.makeText(TryMenu.this, "菜单1", Toast.LENGTH_SHORT).show();
                break;
            case 101:
                Toast.makeText(TryMenu.this, "菜单2", Toast.LENGTH_SHORT).show();
                break;
            case 102:
                Toast.makeText(TryMenu.this, "菜单3", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);

    }
}
