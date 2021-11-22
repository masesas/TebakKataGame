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

    public static String getTahap(Context context, String key, String tahap) {
        if (context != null) {
            try{
                SharedPreferences settings = context.getApplicationContext().getSharedPreferences("Tahap", 0);
                String check = settings.getString(key, tahap);
                if(check != null ){
                    return check;
                }else{
                    return "";
                }
            }catch (Exception e){
                return "";
            }
        } else {
            return "";
        }
    }

    public static String getTutorial(Context context, String key, String tutorial) {
        if (context != null) {
            try{
                SharedPreferences settings = context.getApplicationContext().getSharedPreferences("Tutorial", Context.MODE_PRIVATE);
                String check = settings.getString(key, null);
                if(check != null ){
                    return check;
                }else{
                    return "";
                }
            }catch (Exception e){
                return "";
            }
        } else {
            return "";
        }
    }


    @SuppressLint("ApplySharedPref")
    public static void saveLevel(Context context, String key, int level) {
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("TebakKata", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, level);
        editor.commit();
    }

    @SuppressLint("ApplySharedPref")
    public static void saveTahap(Context context, String key, String tahap) {
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("Tahap", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, tahap);
        editor.commit();
    }

    @SuppressLint("ApplySharedPref")
    public static void saveTutorial(Context context, String key, String tutorial) {
        SharedPreferences settings = context.getApplicationContext().getSharedPreferences("Tutorial", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, tutorial);
        editor.commit();
    }

}
