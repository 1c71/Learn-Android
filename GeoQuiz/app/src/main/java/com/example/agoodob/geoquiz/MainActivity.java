package com.example.agoodob.geoquiz;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 2016-3-17 开始+结束
 *
 * Android编程权威指南
 *
 * 项目1  GeoQuiz
 * 地理知识问答
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";
    // saveInstance 的 key

    private Button true_button;  // 正确按钮
    private Button false_button;  // 错误按钮
    private Button next;  // 下一个按钮
    private TextView mQuestionTextView; // 放问题的 TextView
    private Button cheat_button;
    private boolean mIsCheater;

    // 问题集
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
      new TrueFalse(R.string.question_oceans, true),
      new TrueFalse(R.string.question_midest, false),
      new TrueFalse(R.string.question_africa, false),
      new TrueFalse(R.string.question_americas, true),
      new TrueFalse(R.string.question_asia, true),
    };

    // 问题索引
    private int mCurrentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 初始化第一个问题
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();


        true_button = (Button) findViewById(R.id.true_button);
        false_button = (Button) findViewById(R.id.false_button);
        cheat_button = (Button) findViewById(R.id.cheat_button);
        next = (Button) findViewById(R.id.next_button);

        true_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        false_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        // 作弊按钮
        cheat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(i, 0);
            }
        });



    }

    // 旋转设备的时候把 index 存起来，不然又回到第一道题了
    /**
     * 这个方法在系统需要回收内存的时候会被调用
     * (如果这个 APP 处于暂停或者停止状态才会)
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    // 根据问题索引，更新问题
    public void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }


    /**
     * 检查答案
     * @param userPressTrue 用户点击的是 "正确" 还是 "错误" 按钮
     */
    public void checkAnswer(boolean userPressTrue){
        boolean answer = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageId = 0;

        if (mIsCheater){
            messageId = R.string.jugment_toast;
        } else {
            if(userPressTrue == answer){ //  如果用户按下的和我们设置的一样, 代表答案正确
                messageId = R.string.correct_toast;
            } else {
                messageId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(MainActivity.this, messageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null){
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }
}
