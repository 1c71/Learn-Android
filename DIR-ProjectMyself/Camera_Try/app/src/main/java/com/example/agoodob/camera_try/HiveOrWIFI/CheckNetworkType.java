package com.example.agoodob.camera_try.HiveOrWIFI;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.agoodob.camera_try.R;

/**
 * 1 检查是否有网络
 * 2 检查是 GPRS 还是 WIFI
 *
 *
 */
public class CheckNetworkType extends AppCompatActivity {

    private TextView tv_isConnect, tv_networkType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_network_type);

        tv_isConnect = (TextView) findViewById(R.id.tv_isConnect);
        tv_networkType = (TextView) findViewById(R.id.tv_networkType);

        checkNetwork();
    }

    /**
     * 检查网络状态并且设置文本
     */
    public void checkNetwork(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;

        if (isConnected){
            tv_isConnect.setText("连接上了");
            if(isWiFi){
                tv_networkType.setText("是 WIFI");
            } else {
                tv_networkType.setText("是移动网络");
            }
        } else {
            tv_isConnect.setText("没连接上");
        }
    }

}
