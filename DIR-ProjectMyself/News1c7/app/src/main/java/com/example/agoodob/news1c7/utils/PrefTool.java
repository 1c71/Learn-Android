package com.example.agoodob.news1c7.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference 的封装
 */
public class PrefTool {

    public static final String PREF_NAME = "config";

    public static boolean getBoolean(Context ctx, String key, boolean defaultValue){
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        boolean a = sp.getBoolean(key, defaultValue);
        return a;
    }

    public static void setBoolean(Context ctx, String key, boolean defaultValue){
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        sp.edit().putBoolean(key, defaultValue).apply();
    }

}
