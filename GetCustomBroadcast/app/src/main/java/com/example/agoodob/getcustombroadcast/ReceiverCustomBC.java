package com.example.agoodob.getcustombroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 2016/2/28
 *
 */
public class ReceiverCustomBC extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到了广播", Toast.LENGTH_SHORT).show();
    }
}
