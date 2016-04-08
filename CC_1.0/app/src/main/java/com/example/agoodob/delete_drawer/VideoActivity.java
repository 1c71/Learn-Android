package com.example.agoodob.delete_drawer;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

public class VideoActivity extends Activity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private MediaPlayer mediaPlayer;
    private SurfaceHolder vidHolder;
    private SurfaceView vidSurface;
    GestureDetector mGesture = null;
    private Boolean playControlVisiable = true;
    //String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
    String viewSource ="http://cn-sh7-cc.acgvideo.com/vg8/7/d9/6922784-1.flv?expires=1460127300&ssig=95_CQcYCUzywJIv-wUT15A&oi=1701326943&player=1&or=2081295465&rate=0";
    RelativeLayout process;
    private Boolean pausing = false; // 播放暂停按钮的初始状态
    ImageButton playPauseButton;
    View.OnTouchListener playButtonListener;
    TextView currentTime;
    TextView endTime;
    View bigProcess;
    View smallProcess;

    int beforeJumpMilli; // 跳转前的播放位置
    int durationMilliconds; // 视频总长度: 毫秒
    int newMilliSecond; // 新毫秒位置
    Boolean needJumpVideo = false;

    ResizeWidthAnimation bigAnim;//进度条动画
    ResizeWidthAnimation smallAnim; //进度条动画
    Boolean bigProcessVisiable = false;


    int screenHeightPixel;
    int screenWidthPixel;

    Handler handler=new Handler();

    Runnable runnable=new Runnable(){
        @Override
        public void run() {

            // 如果视频到末尾了也就不用更新时间了，
            // 有个问题是到底了再拉回来就不改时间了
            if (!mUpdateProgress)
                return;

            int currentPosition = mediaPlayer.getCurrentPosition();


            // 更新文字
            String current = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(currentPosition),
                    TimeUnit.MILLISECONDS.toSeconds(currentPosition) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes
                                    (currentPosition))
            );
            currentTime.setText(current);

            // 更新下方细条

            // 1 百分比
            float percent = (float) currentPosition / (float) durationMilliconds * 100;
            float newWidth = percent  *  (float)screenWidthPixel  / 100 ;
            bigAnim = new ResizeWidthAnimation(bigProcess, (int)newWidth);
            smallAnim = new ResizeWidthAnimation(smallProcess, (int)newWidth);
            bigAnim.setDuration(400);
            smallAnim.setDuration(400);

//            bigProcess.startAnimation(bigAnim);
            smallProcess.startAnimation(smallAnim);

            handler.postDelayed(this, 400);
        }
    };

    private boolean mUpdateProgress = true;

    private MediaPlayer.OnCompletionListener mComplete =
    new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mUpdateProgress = false;
        }

    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 因为 SimpleGesture 没有 onUp 所以这里自己实现

        if (event.getAction() == MotionEvent.ACTION_UP) {
            return onUp(event);
        } else {
            return mGesture.onTouchEvent(event);
        }
    }

    private boolean onUp(MotionEvent event) {
        Log.v("TEST", "Up");
        // up 有两种情况，一种是需要跳转到指定时间，一种是不需要
        if(needJumpVideo){
            mediaPlayer.seekTo(newMilliSecond);
            needJumpVideo = false;
            mUpdateProgress = true; // 这样才会更新时间和进度

            // 渐变显示大进度条
            if(bigProcessVisiable){
                Animation alphaAnimation = new AlphaAnimation(1.0f,0.0f);   //设置透明度动画效果
                alphaAnimation.setDuration(200);
                alphaAnimation.setFillAfter(true);
                bigProcess.setAnimation(alphaAnimation);
                bigProcessVisiable = false;
            }

        }

        return true;
    }

 /*   @Override
    public boolean onTouchEvent(MotionEvent me) {

        Log.v("Touch", "ontouch: " + me.getAction());

        if(me.getAction() == 0){
            Log.v("Touch Down", "Down");
        }
        else if (me.getAction() == 1) {
            Log.v("Touch Up", "Up");
        }
        else if (me.getAction() == 2) {
            Log.v("touch Scroll", "Scroll");
        }
        boolean detectedUp = me.getAction() == MotionEvent.ACTION_UP;
        if (!mGesture.onTouchEvent(me) && detectedUp)
        {
            return onUp(me);
        }
        return true;
    }

    private boolean onUp(MotionEvent me) {
        Log.i("TEST", "onUp");
        return false;
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.surface_view);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //使得屏幕不会自动关闭. 加了之后, Note 5 的屏幕自动关闭(15秒)就不管用了


        mGesture = new GestureDetector(this, new GestureListener());

        process = (RelativeLayout) findViewById(R.id.process);
        endTime = (TextView) findViewById(R.id.endTime);
        currentTime = (TextView) findViewById(R.id.currentTime);
        bigProcess = findViewById(R.id.bigProcess);
        smallProcess = findViewById(R.id.smallProcess);


        // 拿到屏幕高宽, 单位是像素
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidthPixel = size.x;
        screenHeightPixel = size.y;


        vidSurface = (SurfaceView) findViewById(R.id.surfView);
        vidHolder = vidSurface.getHolder();
        vidHolder.addCallback(this);

        // 播放暂停按钮的图标和背景颜色
        playPauseButton = (ImageButton)findViewById(R.id.playStop);
        playButtonListener = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton) v;
                        view.setBackgroundColor(Color.parseColor("#99000000"));
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                    {
                        ImageButton view = (ImageButton) v;
                        view.setBackgroundDrawable(null);

                        if (pausing){
                            // 换成暂停按钮, 并且暂停视频
                            view.setImageResource(R.drawable.ic_pause_white_36dp);
                            pausing = false;
                            mediaPlayer.start();
                        } else {
                            view.setImageResource(R.drawable.ic_play_arrow_white_36dp);
                            pausing = true;
                            mediaPlayer.pause();

                        }
                        break;
                    }
                }

                // return true 代表已经对事件做了处理，告诉系统你后面啥也别干了
                //
//                if(!playControlVisiable){
//                    return false;
//                } else {
//                    return true;
//                }
                return true;
            }
        };

        playPauseButton.setOnTouchListener(playButtonListener);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDisplay(vidHolder);
            mediaPlayer.setDataSource(viewSource);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(mComplete);
            //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        mUpdateProgress = true;
        handler.postDelayed(runnable, 1000); // or simply mRunnable.run() if you are on the main thread
        mediaPlayer.start();
        durationMilliconds = mediaPlayer.getDuration();

        String totalTime = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(durationMilliconds),
                TimeUnit.MILLISECONDS.toSeconds(durationMilliconds) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes
                                (durationMilliconds))
        );
        endTime.setText(totalTime);

    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener
    {

        @Override
        public boolean onDoubleTap(MotionEvent e)
        {
            // TODO Auto-generated method stub
            Log.i("TEST", "onDoubleTap");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDown(MotionEvent e)
        {
            // TODO Auto-generated method stub
            Log.i("TEST", "onDown");
            beforeJumpMilli = mediaPlayer.getCurrentPosition(); // 跳转前的播放毫秒


            // SHOW 进度条

            if (playControlVisiable) { // 可见变不可见
                TranslateAnimation tAnim = new TranslateAnimation(0, 0, 0, 200);//横向位移400个单
                tAnim.setDuration(200);
                tAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                tAnim.setFillAfter(true);
                tAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                playControlVisiable = false;
                process.startAnimation(tAnim);

                playPauseButton.setOnTouchListener(null);

            } else {
                TranslateAnimation tAnim = new TranslateAnimation(0, 0, 200, 0);//横向位移400个单
                tAnim.setDuration(200);
                tAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                tAnim.setFillAfter(true);
                tAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                playControlVisiable = true;
                process.startAnimation(tAnim);

                playPauseButton.setOnTouchListener(playButtonListener);
            }


            return super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY)
        {
            // TODO Auto-generated method stub
            //0Log.i("TEST", "onFling:velocityX = " + velocityX + " velocityY" + velocityY);
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onLongPress(MotionEvent e)
        {
            // TODO Auto-generated method stub
            Log.i("TEST", "onLongPress");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY)
        {
            //Log.i("TEST", "onScroll:distanceX = " + distanceX + " distanceY = " + distanceY);

            //distanceX 往右是负数，往左是正数，不取决于你点击的位置，取决于你移动的方向
            if (distanceX < 0){
                //Log.i("TEST", "向右");
            } else {
                //Log.i("TEST", "左边1");
            }

            // 渐变显示大进度条
            if(!bigProcessVisiable){
                Animation alphaAnimation = new AlphaAnimation(0.0f,1.0f);   //设置透明度动画效果
                alphaAnimation.setDuration(200);
                alphaAnimation.setFillAfter(true);
                bigProcess.setAnimation(alphaAnimation);
                bigProcessVisiable = true;
            }


            // 这个0.5 是个滑动比例, 不然会滑动太快, 可以一下子就跳到视频末尾
            // 你可以试试不乘以  看看效果
            double v = -distanceX * 0.5;
            int newWidth = bigProcess.getLayoutParams().width + (int)v;


            // 限定一下宽度在可见范围内
            if (newWidth >= 0 && newWidth <= screenWidthPixel){
                bigProcess.getLayoutParams().width = newWidth;
                bigProcess.requestLayout(); // 这样才会马上更新, 不然你会感觉有延迟.

                // 计算要跳转多少秒

                // 1 百分比
                float percent = (float) newWidth / (float) screenWidthPixel * 100;
                int intPercent = (int)percent;
                Log.i("TEST", "新的百分比:" + intPercent + "%");

                // 根据这个百分比 算出视频的这个百分比是多少毫秒,
                newMilliSecond = (int)((float)durationMilliconds * percent/100);
                Log.i("TEST", "视频长度:   " + durationMilliconds + "");
                //mediaPlayer.seekTo(newMilliSecond);
                needJumpVideo = true; // 代表需要跳视频

            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }



        @Override
        public boolean onSingleTapUp(MotionEvent e)
        {
            // TODO Auto-generated method stub
            Log.i("TEST", "onSingleTapUp");
            return super.onSingleTapUp(e);
        }



    }
}
