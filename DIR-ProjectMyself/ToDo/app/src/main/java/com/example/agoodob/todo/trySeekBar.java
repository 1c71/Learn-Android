package com.example.agoodob.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

public class trySeekBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        SeekBar sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setMax(100);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("aaa", progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(trySeekBar.this, "SeekBar START", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(trySeekBar.this, "SeekBar END", Toast.LENGTH_SHORT).show();
            }
        });



        RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
        rb.setMax(5);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(trySeekBar.this, rating + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
