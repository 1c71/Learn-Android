package com.example.agoodob.testallkind.DataStorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agoodob.testallkind.R;

import java.io.FileInputStream;
    import java.io.FileOutputStream;

    public class IntelStorege extends Activity  {
        Button b1,b2;
        TextView tv;
        EditText ed1;

        String data;
        private String file_name = "mydata";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_intel_storege);

            b1=(Button)findViewById(R.id.button);
            b2=(Button)findViewById(R.id.button2);

            ed1=(EditText)findViewById(R.id.editText);
            tv=(TextView)findViewById(R.id.textView2);

            // 保存到文件里
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data=ed1.getText().toString();

                    try {
                        FileOutputStream fOut = openFileOutput(file_name, MODE_WORLD_READABLE);
                        fOut.write(data.getBytes());
                        fOut.close();
                        Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();
                    }

                    catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        FileInputStream fin = openFileInput(file_name);
                        int c;
                        String temp="";

                        while( (c = fin.read()) != -1){
                            temp = temp + Character.toString((char)c);
                        }
                        tv.setText(temp);
                        Toast.makeText(getBaseContext(),"file read",Toast.LENGTH_SHORT).show();
                    }
                    catch(Exception e){
                    }
                }
            });
        }

    }