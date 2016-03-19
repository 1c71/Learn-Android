package com.example.agoodob.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Model
 */
public class Crime {

    private UUID mId;
    private String mTitlte;
    private Date mDate; // Crime 发生的时间
    private boolean mSolved; // 表示 Crime 是否得到处理

    public Crime() {
        mId = UUID.randomUUID(); // 唯一标示符
        mDate = new Date();
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitlte() {
        return mTitlte;
    }

    public void setTitlte(String titlte) {
        mTitlte = titlte;
    }

    @Override
    public String toString() {
        return mTitlte;
    }
}
