package com.example.kaio;

import android.content.Context;
import android.content.SharedPreferences;

public class Config {
    static String SERVER_URL_BASE = "https://feira-de-piadas.herokuapp.com/";   //Link será alterado

    static void setLogin(Context context, String login){
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("login", login).commit();
    }

    public static String getLogin(Context context){
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("login", "");
    }

    public static void setPassword(Context context, String password){
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("password", password).commit();
    }

    public static String getPassword(Context context){
        SharedPreferences mPrefs = context.getSharedPreferences("config", 0);
        return mPrefs.getString("password", "");
    }
}
