package com.example.agoodob.myapplication;

/**
 * Created by agoodob on 2016/1/24.
 */
public class Hole {
    private String mLabel;
    private int mStrokeCount;

    public Hole(String label, int count){
        mLabel = label;
        mStrokeCount = count;

    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public int getStrokeCount() {
        return mStrokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        mStrokeCount = strokeCount;
    }
}
