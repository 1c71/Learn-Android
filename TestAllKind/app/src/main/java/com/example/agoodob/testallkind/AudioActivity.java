package com.example.agoodob.testallkind;


import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

/*
* 调节手机的三种模式，有声音，震动和静音
*
* 弄个 AudioManager x = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
* 然后 x.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
* */
public class AudioActivity extends Activity {
    Button mode,ring,vibrate,silent;
    private AudioManager myAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        vibrate=(Button)findViewById(R.id.button3);
        ring=(Button)findViewById(R.id.button2);

        mode=(Button)findViewById(R.id.button);
        silent=(Button)findViewById(R.id.button4);
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(AudioActivity.this,"Now in Vibrate Mode",Toast.LENGTH_LONG).show();
            }
        });

        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(AudioActivity.this,"Now in Ringing Mode",Toast.LENGTH_LONG).show();
            }
        });

        silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(AudioActivity.this,"Now in silent Mode",Toast.LENGTH_LONG).show();
            }
        });

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mod=myAudioManager.getRingerMode();

                if(mod==AudioManager.RINGER_MODE_VIBRATE){
                    Toast.makeText(AudioActivity.this,"Now in Vibrate Mode",Toast.LENGTH_LONG).show();
                }

                else if(mod==AudioManager.RINGER_MODE_NORMAL){
                    Toast.makeText(AudioActivity.this,"Now in Ringing Mode",Toast.LENGTH_LONG).show();
                }

                else
                {
                    Toast.makeText(AudioActivity.this,"Now in Vibrate Mode",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}