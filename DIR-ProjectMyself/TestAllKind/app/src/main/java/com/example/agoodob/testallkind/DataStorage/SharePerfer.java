package com.example.agoodob.testallkind.DataStorage;

import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agoodob.testallkind.R;


/*
*
* Share Preferences
* */
public class SharePerfer extends ActionBarActivity {
    EditText ed1,ed2,ed3;
    Button b1;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_perfer);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);

        b1=(Button)findViewById(R.id.button);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        // key,mode


        String NameValue = sharedpreferences.getString(Name, "");
        String PhoneValue = sharedpreferences.getString(Phone, "");
        String EmailValue = sharedpreferences.getString(Email, "");

        ed1.setText(NameValue);
        ed2.setText(PhoneValue);
        ed3.setText(EmailValue);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n  = ed1.getText().toString();
                String ph  = ed2.getText().toString();
                String e  = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, n);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.apply();
                Toast.makeText(SharePerfer.this,"Thanks",Toast.LENGTH_LONG).show();
            }
        });
    }

}



