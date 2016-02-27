package com.example.agoodob.smsfirewall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 2016/2/27
 *
 *
 *
 */
public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    Bundle b = intent.getExtras();
        b.get("pdus");
}
}
