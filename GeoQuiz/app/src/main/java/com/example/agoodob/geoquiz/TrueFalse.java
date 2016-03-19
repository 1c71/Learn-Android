package com.example.agoodob.geoquiz;

/**
 * Created by agoodob on 2016/3/17.
 */
public class TrueFalse {

    private int mQuestion; // 问题. 因为是资源ID 所以是 INT，而不是 STRING
    private boolean mTrueQuestion;

    public TrueFalse(int question, boolean trueQuestion) {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }
}
