package com.example.agoodob.criminalintent;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * 单例模式，保存 Crime 数组
 */
public class CrimeLab {

    private ArrayList<Crime> mCrimes;

    private static CrimeLab sCrimeLab; // s 前缀代表 static
    private Context mAppConext;

    public CrimeLab(Context appConext) {
        mAppConext = appConext;
        mCrimes = new ArrayList<Crime>();

        for (int i = 0; i < 100; i++) {
            Crime c  = new Crime();
            c.setTitlte("Crime #" + i);
            c.setSolved(i % 2 == 0);
            mCrimes.add(c);
            Log.e("asd", "asd");
        }
    }

    public static CrimeLab get(Context c){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime c : mCrimes){
            if (c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public void addCrime(Crime c){
        mCrimes.add(c);
    }
}
