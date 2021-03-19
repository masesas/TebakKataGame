package com.example.tebakkatagame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.tebakkatagame.R;

public class Tahap_Activity extends BaseApp implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahap);
        setComponent();
    }

    private void setComponent(){
        find(R.id.img_kata_bergambar).setOnClickListener(this);
        find(R.id.img_suku_kata).setOnClickListener(this);
        find(R.id.img_tebak_huruf).setOnClickListener(this);
        find(R.id.img_evaluasi).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_kata_bergambar:
                setIntent(LevelTahap_Activity.class, "TEBAK GAMBAR", "");
                break;
            case R.id.img_suku_kata:
                setIntent(LevelTahap_Activity.class, "SUKU KATA", "");
                break;
            case R.id.img_tebak_huruf:
                setIntent(LevelTahap_Activity.class, "TEBAK HURUF", "");
                break;
            case R.id.img_evaluasi:

                break;
        }
    }
}