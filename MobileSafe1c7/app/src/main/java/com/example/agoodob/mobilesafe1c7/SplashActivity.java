package com.example.agoodob.mobilesafe1c7;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.logging.LogRecord;

import utils.StreamUtils;

/**
 * 2016-2-25
 * Splash 页面, 也就是 APP 一开始启动的载入页
 *
 * 我们的逻辑是 Splash 页面用一个 Activity 实现，
 * 然后在这个页面看看有没有新版本,
 * 如果网速很快, 导致检查新版本很快, 就会导致 Splash 一闪而过
 * 所以在跳之前, 检查有没有满足一定时间, 解决一闪而过的问题
 */
public class SplashActivity extends AppCompatActivity {

    private static final int CODE_UPDATE_DIALOG = 0;
    private static final int CODE_URL_ERROR = 1;
    private static final int CODE_NET_ERROR = 2;
    private static final int CODE_JSON_ERROR = 3;
    private static final int CODE_ENTER_HOME = 4;

    // 服务器返回的四个版本信息
    private String mName;
    private String mVersion;
    private String mDesc;
    private String mDownloadLink;

    private RelativeLayout rl_root;

    // Splash 页面的固定显示时间
    private long splashDisplayTime = 1600;

    TextView dProgressTextView;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case CODE_UPDATE_DIALOG:
                    showUpdateDialog();
                    break;
                case CODE_JSON_ERROR:
                    Toast.makeText(SplashActivity.this, "JSON 异常", Toast.LENGTH_SHORT).show();
                    enterHome();
                    break;
                case CODE_NET_ERROR:
                    Toast.makeText(SplashActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    enterHome();
                    break;
                case CODE_URL_ERROR:
                    Toast.makeText(SplashActivity.this, "URL 异常", Toast.LENGTH_SHORT).show();
                    enterHome();
                    break;
                case CODE_ENTER_HOME:
                    enterHome();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences mPrefs = getSharedPreferences("config", MODE_PRIVATE);
        boolean checkUpdateSwitch = mPrefs.getBoolean("auto_update", false);

        rl_root = (RelativeLayout) findViewById(R.id.rl_root);
        // 根布局元素

        // 检查更新
        if (checkUpdateSwitch){
            dProgressTextView = (TextView) findViewById(R.id.splashDownloadProgressTextView);
            checkUpdate();
        } else {
            mHandler.sendEmptyMessageDelayed(CODE_ENTER_HOME, 2000);
            // 延时一定时间后发送消息
        }

        // 渐变动画效果, 慢慢出现
        AlphaAnimation anim = new AlphaAnimation(0.5f, 1f);
        anim.setDuration(1200);
        rl_root.startAnimation(anim);
    }

    // 检查更新
    private void checkUpdate(){
        final long startTime = System.currentTimeMillis();
        new Thread(){
            @Override
            public void run() {

                Message msg = Message.obtain();
                HttpURLConnection conn = null;

                try {
                    URL url = new URL("http://192.168.1.102/AndroidMobileSafe_Backend_PHP/checkVersionUpdate.php");
                    conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(4000);
                    conn.setReadTimeout(3000);

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200){
                        InputStream inputStream = conn.getInputStream();
                        String result = StreamUtils.readFromSteam(inputStream);
                        // 现在拿到了字符串形式的结果
                        //
                        System.out.println(result);
                        JSONObject json = new JSONObject(result);
                        System.out.println(json);

                        mName = json.getString("name");
                        mVersion = json.getString("version");
                        mDesc = json.getString("desc");
                        mDownloadLink = json.getString("downloadLink");
                        /*
                        {
                            name: "新春特别版",
                            mName: "3.2.1",
                            desc: "修复了三个bug, 五个性能提升, 增加了1个新栏目, 新增夜间模式",
                            downloadLink: "http://www.baidu.com"
                         }
                         */

                        // 如果远程的版本大于本地的
                        // 那么就是需要更新
                        if (Integer.parseInt(mVersion) > getVersionCode()){
                            // 弹出升级对话框
                            msg.what = CODE_UPDATE_DIALOG;
                        } else {
                            // 没有新版本就直接进入主页面
                            msg.what = CODE_ENTER_HOME;
                        }
                    } else {
                        Toast.makeText(SplashActivity.this, "检查版本更新时, 服务器返回非200", Toast.LENGTH_SHORT).show();
                    }
                } catch (MalformedURLException e) {
                    // URL 异常
                    e.printStackTrace();
                    msg.what = CODE_URL_ERROR;
                } catch (IOException e) {
                    // 网络异常
                    e.printStackTrace();
                    msg.what = CODE_NET_ERROR;
                } catch (JSONException e) {
                    e.printStackTrace();
                    msg.what = CODE_JSON_ERROR;
                } finally {

                    // 使得 Splash 页面至少显示一段时间
                    long endTime = System.currentTimeMillis();
                    long timeUsed = endTime - startTime;
                    if(timeUsed < splashDisplayTime){
                        try {
                            sleep(splashDisplayTime - timeUsed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    mHandler.sendMessage(msg);
                    if(conn!=null){
                        conn.disconnect();
                    }
                }
            }
        }.start();
    }

    /**
     *  APP 更新提示框
     */
    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("有新版本可以下载了~");
        builder.setMessage(mDesc);
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downloadNewVersion();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enterHome(); // 进入主页面
            }
        });

        // 这个是点击页面其他地方，关掉 Dialog 之后的响应
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                enterHome(); // 进入主页面
            }
        });
        builder.show();
    }

    /**
     * 用于下载新版本 APP 的 apk 文件
     *
     * 改进点: Status Notification 的进度提示
     * 现在没有任何提示就是后台慢慢下
     */
    private void downloadNewVersion() {

        // 判断有没有 SD 卡
        // 我的 Samsung Note 5 是不支持 SD卡的，但是也可以进 if 语句
        // 迷..
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String target = Environment.getExternalStorageDirectory() + "/update.apk";

            // 把显示下载进度用的 TextView 显示起来
            dProgressTextView.setVisibility(View.VISIBLE);

            HttpUtils utils = new HttpUtils();
            utils.download(mDownloadLink, target, new RequestCallBack<File>() {

                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    super.onLoading(total, current, isUploading);

                    //int percent = (int) Math.ceil(current * 1.0/ total * 100);
                    // long / long 这里会得到 0, 所以要乘个 1.0
                    // 然后会得到 13.1384811830 这样的数字, 所以要 ceil
                    // ceil 之后还是得到 22.0  73.0 这样, 所以 casting int

                    //dProgressTextView.setText("下载进度: " + percent + " %");
                    // 这种管用

                    dProgressTextView.setText("下载进度: " + current * 100 / total + " %");
                }

                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    dProgressTextView.setText("新版本下载成功");
                    Toast.makeText(SplashActivity.this, "新版本下载成功", Toast.LENGTH_SHORT).show();
                    enterHome();
                    // 安装下载好的 APP


                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(SplashActivity.this, "下载新版本失败", Toast.LENGTH_SHORT).show();
                    // 如果正在下载, 然后断开网络也会跑到这里了
                    enterHome();
                }
            });
        } else {
            Toast.makeText(SplashActivity.this, "你没有 SD 卡嘿", Toast.LENGTH_SHORT).show();
        }

    }

    // 获取 AndroidManifest.xml 里面的 versionName
    // 1 先 getPackageManager
    // 2 通过 packageManager 拿到 packageInfo
    // 3 通过 packageInfo 拿 versionCode 或者 versionName
    private int getVersionCode(){
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    getPackageName(), 0
            );

            int versionCode = packageInfo.versionCode;
            return versionCode;

            //String versionName = packageInfo.versionName;
            //return versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 进入主界面
     */
    private void enterHome(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();  // 干掉当前的 Activity, 这样 Back 的时候就不会回到这个页面
    }

    public void copyDB(){

        //File fileDir = getFilesDir();
        //System.out.println("路径: " + fileDir.getAbsolutePath());

        File destFile = new File(getFilesDir(), "address.db"); // 目标地址


        FileOutputStream out;
        InputStream in;

        try {
            in = getAssets().open("address.db");
            out = new FileOutputStream(destFile);

            int len = 0;
            byte[] buffer = new byte[1024];

            while((len=in.read(buffer))  !=  -1){
                out.write(buffer, 0, len);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
    }

}
