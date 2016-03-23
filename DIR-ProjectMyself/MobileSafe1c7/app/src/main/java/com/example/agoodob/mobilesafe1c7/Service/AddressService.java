package com.example.agoodob.mobilesafe1c7.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * 来电提醒
 */
public class AddressService extends Service {
    public AddressService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        MyListener myListener = new MyListener();
        tm.listen(myListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    class MyListener extends PhoneStateListener{
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:
                    System.out.println("电话响起..");
                    break;
                default:
                    break;
            }
        }
    }
}
