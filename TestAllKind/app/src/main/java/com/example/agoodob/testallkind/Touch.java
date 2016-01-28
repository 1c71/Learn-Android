package com.example.agoodob.testallkind;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Set;

/*
* 显示触控信息。也就是 XY 坐标
*
* */
public class Touch extends Activity {
    float xAxis = 0f;
    float yAxis = 0f;

    float lastXAxis = 0f;
    float lastYAxis = 0f;

    EditText ed1, ed2, ed3, ed4;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);

        tv1=(TextView)findViewById(R.id.textView2);
        tv1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int actionPeformed = event.getAction();

                switch(actionPeformed){
                    case MotionEvent.ACTION_DOWN:{
                        final float x = event.getX();
                        final float y = event.getY();

                        lastXAxis = x;
                        lastYAxis = y;

                        ed1.setText(Float.toString(lastXAxis));
                        ed2.setText(Float.toString(lastYAxis));
                        break;
                    }

                    case MotionEvent.ACTION_MOVE:{
                        final float x = event.getX();
                        final float y = event.getY();

                        final float dx = x - lastXAxis;
                        final float dy = y - lastYAxis;

                        xAxis += dx;
                        yAxis += dy;

                        ed3.setText(Float.toString(xAxis));
                        ed4.setText(Float.toString(yAxis));
                        break;
                    }
                }
                return true;
            }
        });
    }

}