package com.example.tebakkatagame.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tebakkatagame.R;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class SplashScreen_Activity extends BaseApp {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_);

        final GifImageView gifView = (GifImageView) findViewById(R.id.gif);
        try {
            GifDrawable gifDrawable = new GifDrawable(getResources().openRawResource(R.raw.sukukatagif));
            gifView.setImageDrawable(gifDrawable);

        }catch (Exception e){}

        find(R.id.ly_splash).postDelayed(() -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            finish();
        },2950);
    }



}