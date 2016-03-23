package com.example.agoodob.shareperfences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String PREFES_FILES = "com.teamtreehouse.sharepreferencesapp.preferences";
    private static final String KEY_EDITTEXT = "key_edittext";
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.editText);
        mSharedPreferences = getSharedPreferences(PREFES_FILES, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        String editText = mSharedPreferences.getString(KEY_EDITTEXT, "");
        // 第二个参数是默认值

        mEditText.setText(editText);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor.putString(KEY_EDITTEXT, mEditText.getText().toString());
        mEditor.apply(); // save your change
    }

    // 总结
    // 1. 拿到界面上的元素 finViewById, 留着待会用
    // 2. m = getShareaPreferences函数 设置 KEY 和 MODE
    // 3. m.edit() 弄个 Edit 对象
    // 4. onPause 的时候用 Edit 对象的 putString 存 key-value
    // 5. onCreate 的时候用 m.getString(key, 默认值); 去拿

}
