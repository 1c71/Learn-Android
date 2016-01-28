package com.example.agoodob.testallkind;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import android.os.BatteryManager;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class Gesture2 extends Activity {
    private ImageView iv;
    private Matrix matrix = new Matrix();
    private float scale = 1f;
    private ScaleGestureDetector SGD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture2);

        iv=(ImageView)findViewById(R.id.imageView);
        SGD = new ScaleGestureDetector(this,new ScaleListener());
    }

    public boolean onTouchEvent(MotionEvent ev) {
        SGD.onTouchEvent(ev);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));

            matrix.setScale(scale, scale);
            iv.setImageMatrix(matrix);
            return true;
        }
    }

}