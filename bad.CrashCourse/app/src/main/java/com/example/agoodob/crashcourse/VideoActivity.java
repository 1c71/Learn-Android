package com.example.agoodob.crashcourse;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * 视频播放页
 *
 * 如果要全屏就不能用 AppCompatActivity, 不然报错，不知道为什么
 */
public class VideoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);



        VideoView vidView = (VideoView)findViewById(R.id.myVideo);

        String vidAddress = "http://ws.acgvideo.com/c/c3/6652729-1.flv?wsTime=1458753480&wsSecret2=887ca4fe64b06b2f78928e18ec525240&oi=1790444448&player=1&or=1807132074";
        Uri vidUri = Uri.parse(vidAddress);
        vidView.setVideoURI(vidUri);
        vidView.start();

        MediaController vidControl = new MediaController(this);

        vidControl.setAnchorView(vidView);

        vidView.setMediaController(vidControl);
    }
}
