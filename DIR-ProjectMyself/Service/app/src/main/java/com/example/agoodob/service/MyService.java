package com.example.agoodob.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Telephony;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class MyService extends Service {

    public MyService() {
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
        tm.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
        // 第二个 event 参数决定了监听什么事件
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class MyListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:
                    // 空闲
                    System.out.println("空闲");

                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    // 响铃, 初始化录音机
                    System.out.println("空闲");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    // 摘机
                    System.out.println("空闲");
                    break;
            }
        }
    }


}
