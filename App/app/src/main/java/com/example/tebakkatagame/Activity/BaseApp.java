package com.example.tebakkatagame.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class BaseApp extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    public <T extends View> T to(View v, Class<? super T> s) {
        return (T) (v);
    }

    public <T extends View> T find(int id) {
        return (T) findViewById(id);
    }

    public <T extends View> T find(int id, Class<? super T> s) {
        return (T) findViewById(id);
    }

    public <T extends View> T findView(View v, int id, Class<? super T> s) {
        return (T) v.findViewById(id);
    }

    public Activity getActivity() {
        return this;
    }

    public void showInfo(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setImageFromString(ImageView icon, String imageName) {
        Resources res = getResources();
        int resID = res.getIdentifier(imageName, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID);
        icon.setImageDrawable(drawable);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setImageFromString(ImageView icon, String imageName, int width, int height) {
        Resources res = getResources();
        int resID = res.getIdentifier(imageName, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID);
        icon.setImageDrawable(drawable);
        LinearLayout.LayoutParams frameLayout = new LinearLayout.LayoutParams(width, height);
        icon.setLayoutParams(frameLayout);
        icon.getLayoutParams().height = 260;
        icon.getLayoutParams().width = 260;
        icon.requestLayout();
    }

    public void setIntent(Class to, String key, String value){
        Intent intent = new Intent(getActivity(), to);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onStop() {
        super.onStop();
    }

}

