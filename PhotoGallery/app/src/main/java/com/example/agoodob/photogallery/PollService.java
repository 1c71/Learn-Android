package com.example.agoodob.photogallery;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * Created by agoodob on 2016/3/22.
 */
public class PollService extends IntentService {

    public static final String TAG = "PollService";

    public PollService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getBackgroundDataSetting() &&
                cm.getActiveNetworkInfo() != null;
        if (!isNetworkAvailable) return;

        Log.i(TAG, "RECEIVED an intent: " + intent);



    }
}
