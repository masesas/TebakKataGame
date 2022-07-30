package com.example.tebakkatagame.Activity.GamePlay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.R;

public class HurufCategory_Activity extends BaseApp {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huruf_category);
        initComponents();
    }


    void initComponents(){
     find(R.id.img_vokal).setOnClickListener(v -> setIntent(MainGameMengenalHuruf_Activity.class, "category", "vokal"));
     find(R.id.img_non_vokal).setOnClickListener(v -> setIntent(MainGameMengenalHuruf_Activity.class, "category", "non-vokal"));
    }
}