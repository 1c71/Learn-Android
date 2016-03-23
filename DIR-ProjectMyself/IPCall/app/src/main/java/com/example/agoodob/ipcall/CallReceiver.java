package com.example.agoodob.ipcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by agoodob on 2016/2/27.
 * 广播接收器
 *
 */
public class CallReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("收到了吗????");
        // 添加 IP 线路
        // 在打电话的广播中，会携带拨打的电话号码
        String number = getResultData();

        SharedPreferences sp = context.getSharedPreferences("ip", Context.MODE_PRIVATE);
        String ipNumber = sp.getString("ipNumber", "");

        // 把 IP 线路添加至用户拨打号码的前面
        number = ipNumber + number;
        setResultData(number);

    }
}
