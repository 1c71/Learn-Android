package com.example.agoodob.myapplication.model;

/**
 * Created by agoodob on 2016/1/15.
 */
public class Choice {
    private String mText; // 这个选择要显示的文字
    private int mNextPage; // 下一页的数字

    public Choice(String text, int nextPage){
        mText = text;
        mNextPage = nextPage;
    }

    public int getNextPage() {
        return mNextPage;
    }

    public void setNextPage(int nextPage) {
        mNextPage = nextPage;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

}
