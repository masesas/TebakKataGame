package com.example.tebakkatagame.Activity;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

=======
import android.content.Intent;
>>>>>>> 3dfb6e9c5c15db82950a5d46f2f08bf85fcad53d
import android.os.Bundle;

import com.example.tebakkatagame.R;

<<<<<<< HEAD
public class SplashScreen_Activity extends AppCompatActivity {
=======
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class SplashScreen_Activity extends BaseApp {
>>>>>>> 3dfb6e9c5c15db82950a5d46f2f08bf85fcad53d

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_);
<<<<<<< HEAD
    }
=======

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



>>>>>>> 3dfb6e9c5c15db82950a5d46f2f08bf85fcad53d
}