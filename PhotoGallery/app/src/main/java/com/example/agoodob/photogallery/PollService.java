package com.example.agoodob.photogallery;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;
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
        Log.i(TAG, "RECEIVED an intent: " + intent);
    }
}
