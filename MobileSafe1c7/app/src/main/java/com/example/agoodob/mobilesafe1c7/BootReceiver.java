package com.example.agoodob.mobilesafe1c7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * 2016/3/1
 *
 * 开机的广播接收器
 */
public class BootReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences mPref = context.getSharedPreferences("config", Context.MODE_PRIVATE);

        // 判断 SIM 序列号是否一致
        String oldSIM = mPref.getString("sim", null);
        if(!TextUtils.isEmpty(oldSIM)){
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String currentSIM = tm.getSimSerialNumber();
            if(!oldSIM.equals(currentSIM)){
                // 如果不一致
                
            }
        }
    }
}
