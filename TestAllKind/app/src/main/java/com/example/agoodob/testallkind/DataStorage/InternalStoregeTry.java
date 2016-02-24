package com.example.agoodob.testallkind.DataStorage;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agoodob.testallkind.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;

/**
 * 2016-2-24
 * 内部存储的写和读
 *
 *
 */
public class InternalStoregeTry extends AppCompatActivity implements View.OnClickListener{

    EditText usernameET;
    EditText passwordET;
    CheckBox saveAccountCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storege_try);

        usernameET = (EditText) findViewById(R.id.username_storge);
        passwordET = (EditText) findViewById(R.id.password_storge);
        saveAccountCheckBox = (CheckBox) findViewById(R.id.checkBox);
        Button logInButton = (Button) findViewById(R.id.logIn);
        Button readFileButton = (Button) findViewById(R.id.readFile);

        Button rootFileButton = (Button) findViewById(R.id.rootFile);
        Button rootFolderButton = (Button) findViewById(R.id.rootFolder);
        Button freeSpaceButton = (Button) findViewById(R.id.freeSpace);

        // 根目录下创建文件
        rootFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FILENAME = Environment.getExternalStorageDirectory().getPath() + "/1c7hahaha.txt";
                //  这个写法不对..
                //  http://developer.android.com/reference/android/os/Environment.html

                Toast.makeText(InternalStoregeTry.this, "根目录下创建文件", Toast.LENGTH_SHORT).show();
                try {
                    // 存的文件去哪里了??
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write("faefaefaefaef".getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    Toast.makeText(InternalStoregeTry.this, "创建文件失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(InternalStoregeTry.this, "关闭文件失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        // 根目录下创建文件夹
        rootFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FOLDERNAME = "1c7oooo";
                Toast.makeText(InternalStoregeTry.this, "根目录下, 文件架", Toast.LENGTH_SHORT).show();
            }
        });

        // 空闲空间的容量显示
        freeSpaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InternalStoregeTry.this, "根目录下, 文件架", Toast.LENGTH_SHORT).show();
            }
        });

        // 保存到文件的按钮
        logInButton.setOnClickListener(this);

        // 读取文件的按钮
        readFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String dir = getFilesDir().getAbsolutePath();
                //Toast.makeText(InternalStoregeTry.this, dir, Toast.LENGTH_SHORT).show();

                File file = new File("data/data/com.example.agoodob.testallkind/asd.txt");
                // Context.getFilesDir().getPath().

                FileInputStream fis  = null;
                try {
                    fis = new FileInputStream(file);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String text = br.readLine();
                    Toast.makeText(InternalStoregeTry.this, text, Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    @Override
    public void onClick(View v) {
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        if(saveAccountCheckBox.isChecked()){
            try {
                // 存的文件去哪里了??
                FileOutputStream fos = openFileOutput("111.txt", Context.MODE_PRIVATE);
                fos.write((username + "##" + password).getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                Toast.makeText(InternalStoregeTry.this, "创建文件失败", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(InternalStoregeTry.this, "关闭文件失败", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        Toast.makeText(InternalStoregeTry.this, "内容已保存", Toast.LENGTH_SHORT).show();
    }
}
