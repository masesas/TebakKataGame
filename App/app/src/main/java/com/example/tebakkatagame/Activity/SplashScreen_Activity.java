package com.example.tebakkatagame.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        find(R.id.ly_splash).postDelayed(() -> {
            Intent intent = new Intent(getActivity(), LevelTahap_Activity.class);
            startActivity(intent);
            finish();
        }, 2950);
    }
}