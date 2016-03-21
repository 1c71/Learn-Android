package com.example.agoodob.criminalintent;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 单例模式，保存 Crime 数组
 */
public class CrimeLab {

    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";
    private ArrayList<Crime> mCrimes;

    private CriminalIntentJSONSerializer mSerializer;

    private static CrimeLab sCrimeLab; // s 前缀代表 static
    private Context mAppConext;

    public CrimeLab(Context appConext) {
        mAppConext = appConext;

        try{
            mSerializer = new CriminalIntentJSONSerializer(appConext, FILENAME);
            mCrimes = mSerializer.loadCrimes();
        } catch (Exception e) {
            mCrimes = new ArrayList<Crime>();
        }

    }

    public void deleteCrime(Crime c){
        mCrimes.remove(c);
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

    public boolean saveCrimes(){
        try {
            mSerializer.saveCrimes(mCrimes);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes: " + e);
            return false;
        }

    }
}
