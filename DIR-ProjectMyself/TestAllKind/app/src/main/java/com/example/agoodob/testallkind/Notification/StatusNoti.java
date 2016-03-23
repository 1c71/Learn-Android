package com.example.agoodob.testallkind.Notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.agoodob.testallkind.R;

/*
* Status Notifications
* 常驻通知
*
*
* required:
* icon
* title and message
* PendingIntent
*
* 默认通知的流程:
*  我们需要3个对象，一个 NotificationManager, 一个 Notification.Builder 一个 Notification
*  Notification 存 Notification.Builder  build() 之后的值
* 然后 NotificationManager.notify(id, Notification)
*
*
* 自定义通知的流程:
*  和默认通知一样, 只不过要新建一个 xml
*  然后代码里写的是 RemoteViews 传入那个 xml,
*  然后builder setContent, 把这个 RemoteViews 设置进去，其他就一样了
*
* */
public class StatusNoti extends AppCompatActivity {

    private NotificationManager notiManager;
    private Notification.Builder notiBuilder;
    private Notification.Builder notiBuilder2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_noti);

        Button b = (Button) findViewById(R.id.show_noti);
        Button b2 = (Button) findViewById(R.id.show_custom_noti);

        String ns = Context.NOTIFICATION_SERVICE;
        notiManager = (NotificationManager) getSystemService(ns);
        notiBuilder = new Notification.Builder(this);
        notiBuilder2 = new Notification.Builder(this);

        //
        b.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                Intent i = new Intent(StatusNoti.this, StatusNotiFired.class);
                PendingIntent pt = PendingIntent.getActivity(StatusNoti.this, 0, i, 0);

                long[] virbrate = {3000, 2000, 50};
                notiBuilder.setContentIntent(pt)
                        .setContentTitle("点击查看文件") // 最上面
                        .setContentText("下载已完成")  // 中间
                        .setSmallIcon(R.drawable.xin)
                        //.setSubText("字幕")  // 最下面
                        .setTicker("22222222222222")
                        .setDefaults(Notification.DEFAULT_ALL)
                        //.setSound(uri)
                        //.setVibrate(virbrate) // 震动
                        .setNumber(321); // 在右下角

                // 还可以设置声音，震动，闪光灯
                //

                Notification noti = notiBuilder.build();
                notiManager.notify(100, noti);
            }
        });


        // 自定义通知
        b2.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                // 新建了一个 RemoteViews 然后设置了一些东西
                RemoteViews contentViews = new RemoteViews(getPackageName(),
                       // R.layout.activity_status_noti_custom);
                         R.layout.custom_notification);

                contentViews.setImageViewResource(R.id.image, R.drawable.xin);
                contentViews.setTextViewText(R.id.title, "自定义通知的标题");
                contentViews.setTextViewText(R.id.text, "asdasd");

                // 把 Intent 变成 pendingIntent
                Intent i = new Intent(StatusNoti.this, StatusNotiFired.class);
                PendingIntent pt = PendingIntent.getActivity(StatusNoti.this, 0, i, 0);

                // 设置 Builder
                notiBuilder2.setContentIntent(pt)    // 指定 pending intent
                        .setContent(contentViews)    // 指定布局
                        .setContentTitle("点击查看文件") // 最上面
                        .setContentText("下载已完成")  // 中间
                        .setSmallIcon(R.drawable.xin)
                        .setTicker("自定义通知");

                Notification noti = notiBuilder2.build();
                notiManager.notify(1001, noti);

            }
        });

    }

}
