package com.example.agoodob.camera_try.HiveOrWIFI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.agoodob.camera_try.HiveOrWIFI.CheckNetworkType;


/**
 *
 */
public class NetworkStateReceiver extends BroadcastReceiver {
    public NetworkStateReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "==============", Toast.LENGTH_LONG).show();
        // 怎么调用另一个方法去修改 TextView ？
        // 我这里就直接崩溃了
    }
}
