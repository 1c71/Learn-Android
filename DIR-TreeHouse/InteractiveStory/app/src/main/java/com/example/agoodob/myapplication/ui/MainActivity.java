package com.example.agoodob.myapplication.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agoodob.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private EditText mNameField;
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText mNameField = (EditText) findViewById(R.id.nameEditText);
        Button mStartButton = (Button) findViewById(R.id.startButton);

        View.OnClickListener startListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = mNameField.getText().toString();
                // Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
                startStory(name);
            }
        };
        mStartButton.setOnClickListener(startListener);
    }

    private void startStory(String name) {
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra(getString(R.string.key_name), name); // 把数据传到另一个 activity 里
        startActivity(intent); // 打开 activity
    }


    @Override
    protected void onResume() {
        super.onResume();
        //mNameField.setText("");//把名字去掉, 变回空的输入框
    }
}







