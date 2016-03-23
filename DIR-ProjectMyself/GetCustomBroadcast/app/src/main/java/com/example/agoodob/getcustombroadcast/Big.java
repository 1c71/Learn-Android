package com.example.agoodob.getcustombroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 2016/2/28.
 */
public class Big extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String text = getResultData();
        setResultData("80斤大米");
    }
}
