package com.example.kaio;

import android.content.Context;
import android.util.DisplayMetrics;

public class Util {
    public static double tamanhoBotaoTopPiadas(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        double tam = displayMetrics.widthPixels/3;
        return tam;
    }
}
