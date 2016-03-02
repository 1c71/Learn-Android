package com.example.agoodob.mobilesafe1c7.DB;

import android.database.sqlite.SQLiteDatabase;

/**
 * 2016/3/2
 * 归属地查询工具
 */
public class Address {

    private static String path = "data/data/com.example.agoodob.mobilesafe1c7/files/address.db";

    /**
     *
     * @param number 电话号码
     * @return 归属地
     */
    public static String getAddress(String number){

        SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);


        return null;
    }
}
