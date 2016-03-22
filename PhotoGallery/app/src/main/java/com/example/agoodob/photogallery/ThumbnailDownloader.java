package com.example.agoodob.photogallery;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ThumbnailDownloader<Token> extends HandlerThread {

    public static final String TAG = "ThumbnailDownloader";
    public static final int MESSAGE_DOWNLOAD = 0;

    Handler mHandler;
    Map<Token, String> requestMap =
            Collections.synchronizedMap(new HashMap<Token, String>());
    Handler mResponseHandler;
    Listener<Token> mListener;

    public interface Listener<Token> {
        void onThumbnailDownloaded(Token token, Bitmap thumbnail);
    }

    public void setListener(Listener<Token> listener){
        mListener = listener;
    }


    public ThumbnailDownloader(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;
    }

    public void queueThumbnail(Token token, String url){
        Log.i(TAG, "got url: " + url);

        requestMap.put(token, url);

        mHandler
                .obtainMessage(MESSAGE_DOWNLOAD, token)
                .sendToTarget();
    }

    /*
       这个发生在 Looper 第一次检查消息队列之前
     */
    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == MESSAGE_DOWNLOAD) {
                    Token token = (Token)msg.obj;
                    Log.i(TAG, "获取到 url 为 : " + requestMap.get(token) + "的请求");
                    handleRequest(token);
                }
            }
        };
    }

    private void handleRequest(final Token token){
        try {

            final String url = requestMap.get(token);
            if(url == null)
                return;
            byte[] bitmapBytes = new FlickrFetcher().getUrlBytes(url);
            final Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
            Log.i(TAG, "Bitmap 创建完成");

            mResponseHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(requestMap.get(token) != url) {
                        return;
                    }
                    requestMap.remove(token);
                    mListener.onThumbnailDownloaded(token, bitmap);
                }
            });
            /*
                因为 mResponseHandler 与主线程的 Looper 相关联，所以UI更新代码也是在主线程中完成的。
             */

        } catch (IOException e) {
            Log.i(TAG, "下载图片出错");
        }
    }

    public void clearQueue(){
        mHandler.removeMessages(MESSAGE_DOWNLOAD);
        requestMap.clear();
    }
}
