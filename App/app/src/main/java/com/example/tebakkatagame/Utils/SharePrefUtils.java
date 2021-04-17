package com.example.tebakkatagame.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefUtils {

    public static int getLevel(Context context, String key, int level) {
        if (context != null) {
            try{
                SharedPreferences settings = context.getApplicationContext().getSharedPreferences("TebakKata", 0);
                return settings.getInt(key, 1);
            }catch (Exception e){
                return level;
            }
        } else {
            return level;
        }
    }

    @SuppressLint("ApplySharedPref")
    public static void saveLevel(Context context, String key, int level) {
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("TebakKata", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, level);
        editor.commit();
    }

}
