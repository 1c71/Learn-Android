package com.example.agoodob.mobilesafe1c7;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * 2016-2-25
 * Android Studio 1.5.1 + Win10
 *
 * 手机卫士项目 (黑马52期, day40开始)
 * 主页面
 *
 * 还没测成功访问检查更新网址，因为手机访问电脑网站的问题
 *
 */
public class MainActivity extends AppCompatActivity {


    private String[] mItems = new String[]{"手机防盗", "流量监控", "清理加速", "软件管理",
            "手机杀毒", "进程管理", "高级工具", "拦截骚扰", "设置中心"};

    private int[] mPics = new int[]{R.drawable.icon_boat,
            R.drawable.icon_bus, R.drawable.icon_stamp,
            R.drawable.icon_drink, R.drawable.icon_fork,
            R.drawable.icon_sign, R.drawable.icon_sliper,
            R.drawable.icon_tree, R.drawable.icon_water
            };

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs = getSharedPreferences("config", MODE_PRIVATE);

        GridView gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(new MainAdapter());
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        // 手机防盗
                        showPasswordDialog();
                        break;
                    case 8:
                        // 设置中心
                        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(i);
                        break;
                }
            }
        });

    }

    /**
     * 显示密码设置弹窗
     */
    private void showPasswordDialog() {
        // 判断之前是否设置过密码, 设置过就不弹出了
        String savePassword = mPrefs.getString("password", null);
        if(!TextUtils.isEmpty(savePassword)){
            // 输入密码弹窗
            showPasswordInputDialog();
        } else {
            // 设置密码弹窗
            showPasswordSetDialog();
        }
    }

    /**
     * 输入密码弹窗
     */
    private void showPasswordInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();

        View view = View.inflate(this, R.layout.dialog_input_password, null);

        Button btn_ok = (Button) view.findViewById(R.id.btn_ok);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        final EditText et_password = (EditText) view.findViewById(R.id.et_password);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = et_password.getText().toString();

                if(!TextUtils.isEmpty(pwd)){
                    //mPrefs.edit().putString("password", pwd).apply();
                    String storePassword = mPrefs.getString("password", null);
                    if (pwd == storePassword){
//                        Intent i = new Intent(MainActivity.class);
//                        startActivity(i);
                        Toast.makeText(MainActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.setView(view, 0, 0, 0, 0); // 保证在 2.x 版本上运行没问题
        // 将自定义的布局文件设置给 view


        dialog.show();
    }

    /**
     * 设置密码弹窗
     */
    private void showPasswordSetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();

        View view = View.inflate(this, R.layout.dialog_set_password, null);

        Button btn_ok = (Button) view.findViewById(R.id.btn_ok);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        final EditText et_password = (EditText) view.findViewById(R.id.et_password);
        final EditText et_password_confirm = (EditText) view.findViewById(R.id.et_password_confirm);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = et_password.getText().toString();
                String pwd_confirm = et_password_confirm.getText().toString();

                if(!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(pwd_confirm)){
                    mPrefs.edit().putString("password", pwd).apply();
                    // 把用户输入的密码存起来
                } else {
                    Toast.makeText(MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        dialog.setView(view, 0, 0, 0, 0); // 保证在 2.x 版本上运行没问题
        // 将自定义的布局文件设置给 view


        dialog.show();
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
