package com.example.agoodob.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by agoodob on 2016/3/19.
 */
public class AudioPlayer {

    private MediaPlayer mPlayer;

    public void stop(){
        if (mPlayer != null) {
            mPlayer.release(); // 除非调用这个方法，不然会一直占用着系统资源
            mPlayer = null;
        }
    }

    public void play(Context c){
        stop();

        mPlayer = MediaPlayer.create(c, R.raw.one_small_step);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
            }
        });

        mPlayer.start();
        /*
        这种写法是为了只有一个 MediaPlayer 实例
        */
    }


}
