package com.example.agoodob.smsfirewall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

/**
 * 2016-2-27
 * 2016-2-28 update
 *
 */
public class Receiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle b = intent.getExtras();
        Object[] objects = (Object[]) b.get("pdus");
        // pdus 是key. 取出多条短信, 这里每一个对象都是一条短信

        System.out.println("测试能否正常接收广播");

        for (Object object : objects){

            SmsMessage sms = SmsMessage.createFromPdu((byte[]) object);
            System.out.println(sms.getOriginatingAddress());
            System.out.println(sms.getMessageBody());

            if (sms.getOriginatingAddress().equals("138439")){
                abortBroadcast();
                //阻止其他人收到广播
                // 这样拦截不到, 因为是在短信应用之后收到广播
                // 所以要在 manifest.xml 里设置优先级数字

                //SmsManager.getDefault().sendTextMessage(sms.getOriginatingAddress(), null, "你是个好人", null, null);
                // 然后回一条短信
            }
        }



    }
}
