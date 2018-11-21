package com.hzw.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 测试
 */
public class SpUtils {

    public static final String LOGIN_KEY="login";

    public static void putBoolean(Context context,String key,boolean value){
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key,value).apply();
    }


    public static boolean getBoolean(Context context,String key){
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

}
