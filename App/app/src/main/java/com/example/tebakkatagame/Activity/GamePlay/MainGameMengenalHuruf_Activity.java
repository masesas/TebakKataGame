package com.example.tebakkatagame.Activity.GamePlay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.R;

public class MainGameMengenalHuruf_Activity extends BaseApp {

    MediaPlayer wordSound;
    private String category, nextAlpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mengenal_huruf);
        initComponents();

        addImage();
    }

    void addImage(){
        LinearLayout lyDummy = findViewById(R.id.ly_dummy);
        ImageView myImage = new ImageView(this);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(100,100);
        myImage.setLayoutParams(parms);
        myImage.setImageResource(R.drawable.letter_a);
        shakesAnimate(myImage);
        lyDummy.addView(myImage);
        myImage.setImageResource(R.drawable.letter_y);
        shakesAnimate(myImage);
        lyDummy.addView(myImage);
        myImage.setImageResource(R.drawable.letter_a);
        shakesAnimate(myImage);
        lyDummy.addView(myImage);
        myImage.setImageResource(R.drawable.letter_m);
        shakesAnimate(myImage);
        lyDummy.addView(myImage);
    }

    void initComponents() {
        category = getIntent().getStringExtra("category");
        nextAlpa = getIntent().getStringExtra("next");
        find(R.id.img_tebak).setVisibility(View.GONE);
        find(R.id.img_huruf).setOnClickListener(v -> next());
        if (isVokal()) {
            find(R.id.img_title, ImageView.class).setImageResource(R.drawable.vokal_new);
            setVokal(nextAlpa, false, false);
        } else if (isNonVokal()) {
            find(R.id.img_title, ImageView.class).setImageResource(R.drawable.non_vokal);
        }
    }

    void next() {
        if (category.equals("vokal")) {
            setVokal(nextAlpa, getIntent().hasExtra("next"), true);
        } else if (category.equals("non-vokal")) {
            setNonVokal(nextAlpa, getIntent().hasExtra("next"), true);
        }
    }

    void setVokal(String nextAlpa, boolean isLevel, boolean isBtn) {
        //next alpha is next word show or what ever like next level begin
        if(nextAlpa == null){
            if(!isBtn){

                find(R.id.img_huruf, ImageView.class).setImageResource(R.drawable.letter_a);
            }
            setNext("a", isLevel);
        }else{
            switch (nextAlpa) {
                case "e":
                    if(!isBtn){
                        find(R.id.img_huruf, ImageView.class).setImageResource(R.drawable.letter_e);
                    }
                    setNext("i", isLevel);
                    break;
                case "i":
                    if(!isBtn){
                        find(R.id.img_huruf, ImageView.class).setImageResource(R.drawable.letter_i);
                    }
                    setNext("o", isLevel);
                    break;
                case "o":
                    if(!isBtn){
                        find(R.id.img_huruf, ImageView.class).setImageResource(R.drawable.letter_o);
                    }
                    setNext("u", isLevel);
                    break;
                case "u":
                    if(!isBtn){
                        find(R.id.img_huruf, ImageView.class).setImageResource(R.drawable.letter_u);
                    }
                    break;
            }
        }
    }

    void setNonVokal(String nextAlpa, boolean isLevel, boolean isBtn) {
        if(nextAlpa == null){
            setNext("b", isLevel);
        }else{
            switch (nextAlpa) {
                case "c":
                    break;
                case "d":
                    break;
                case "f":
                    break;
                case "g":
                    break;
                case "h":
                    break;
                case "j":
                    break;
                case "k":
                    break;
                case "l":
                    break;
                case "m":
                    break;
                case "n":
                    break;
                case "p":
                    break;
                case "q":
                    break;
                case "r":
                    break;
                case "s":
                    break;
                case "t":
                    break;
                case "v":
                    break;
                case "w":
                    break;
                case "x":
                    break;
                case "y":
                    break;
                case "z":
                    break;
            }
        }
    }

    void setNext(String nextAlfa, boolean isLvl) {
        if (isLvl) {
            moveNext(nextAlfa);
        } else {
            //play sound and show dialog
            wordSound = MediaPlayer.create(getActivity(), R.raw.padi);
            wordSound.setOnCompletionListener(mp -> {
                mp.reset();
                mp.release();
                mp = null;
            });
            wordSound.start();
        }
    }

    void moveNext(String alphabet) {
        Intent intent = new Intent(getActivity(), MainGameMengenalHuruf_Activity.class);
        intent.putExtra("category", category);
        intent.putExtra("next", alphabet);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    boolean isVokal(){
        return category.equals("vokal");
    }

    boolean isNonVokal(){
        return category.equals("non-vokal");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}