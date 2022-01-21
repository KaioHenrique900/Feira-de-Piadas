package com.example.kaio;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kaio.model.PerfilUserViewModel;

public class Config {
    public static String SERVER_URL_BASE = "https://feira-de-piadas.herokuapp.com/";

    static void setLogin(Context context, String login){
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("login", login).commit();
    }

    static void setUid(Context context, String uid){
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("uid", uid).commit();
    }

    public static String getUid(Context context){
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("uid", "");
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
