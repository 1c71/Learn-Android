package com.example.agoodob.mobilesafe1c7.DB;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 2016/3/2
 * 归属地查询工具
 */
public class Address {

    private static String path = "data/data/com.example.agoodob.mobilesafe1c7/files/address.db";
    // 必须在这个目录下否则访问不到. 不太清楚为啥

    public static String address = "未知号码";
    /**
     *
     * @param number 电话号码
     * @return 归属地
     */
    public static String getAddress(String number){

        SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

        /**
         *  1 看下是不是手机号
         *      是就查
         *  2 看下是不是纯数字
         *      是就查
         */

        // 判断传入参数是否是电话号码
        if(number.matches("^1[3-8]\\d{9}$")){ // 匹配手机号
            Cursor c = database.rawQuery("SELECT location FROM data2 WHERE id=(select outkey from" +
                            " data1 where id=?)", new String[]{number.substring(0, 7)});

            if(c.moveToNext()){
                String address = c.getString(0);
            }

            c.close();

        } else if (number.matches("^\\d+$")){ // 匹配数字
            switch (number.length()){
                case 3:
                    address = "报警电话";
                    break;
                case 4:
                    address = "模拟器";
                case 5:
                    address = "客服电话";
                    break;
                case 7:
                case 8:
                    address = "本地电话";
                    break;
                default:
                    if (number.startsWith("0") && number.length() > 10){ // 有可能是长途电话
                        // 有些区号是4位，有些3位(包括0)
                        Cursor c = database.rawQuery("select location from data2 where area = ?",
                                new String[] {number.substring(1, 4)});
                        if (c.moveToNext()){
                            address = c.getString(0);
                        } else {
                            // 如果没找到
                            c.close();
                            c = database.rawQuery("select location from data2 where area = ?",
                                    new String[] {number.substring(1, 3)});
                            if (c.moveToNext()){
                                address = c.getString(0);
                            }

                            c.close();
                        }
                    }

                    break;
            }
        }
        database.close();
        return address;
    }
}
