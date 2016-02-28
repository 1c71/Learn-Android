package com.example.agoodob.sdstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 2016/2/28
 */
public class SDCardReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        // 判断收到的是什么广播
        String action = intent.getAction();
        if ("android.intent.action.MEDIA_MOUNTED".equals(action)){

            Toast.makeText(context, "SD卡可用", Toast.LENGTH_SHORT).show();

        } else if("android.intent.action.MEDIA_REMOVED".equals(action)){

            Toast.makeText(context, "SD卡拔出", Toast.LENGTH_SHORT).show();

        } else if("android.intent.action.MEDIA_UNMOUNTED".equals(action)){

            Toast.makeText(context, "SD卡不可用", Toast.LENGTH_SHORT).show();
        }
    }
}
