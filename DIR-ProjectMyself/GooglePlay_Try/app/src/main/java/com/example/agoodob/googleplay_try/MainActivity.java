package com.example.agoodob.googleplay_try;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.View;

/**
 * 2016-3-11
 * Google Play 市场
 * 黑马52期安卓 day61_谷歌电子市场
 *
 */
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    /**
     * 菜单创建
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);

        // 根据不同安卓版本
        if(Build.VERSION.SDK_INT > 11){
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                    .getActionView();
            searchView.setOnQueryTextListener(this);
        } else {

        }



        return true;
    }

    /**
     * 菜单响应
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_search){
            Toast.makeText(getApplicationContext(), "搜索", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Toast.makeText(getApplicationContext(), newText, Toast.LENGTH_SHORT).show();
        return true;
    }

    public void bbb(View view){
        startActivity(new Intent(MainActivity.this, DetailActivity.class));
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(getApplicationContext(), "asdasd", Toast.LENGTH_SHORT).show();
    }
}
