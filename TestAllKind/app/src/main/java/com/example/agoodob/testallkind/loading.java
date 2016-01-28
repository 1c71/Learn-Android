package com.example.agoodob.testallkind;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

/*
* 怎样显示一个载入图标
*
* */
public class loading extends Activity {
    Button b1;

    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        b1=(Button)findViewById(R.id.button);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinner.getVisibility() == View.INVISIBLE){
                    spinner.setVisibility(View.VISIBLE);
                } else {
                    spinner.setVisibility(View.INVISIBLE);
                }
                //spinner.setVisibility(View.VISIBLE);
            }
        });
    }

}