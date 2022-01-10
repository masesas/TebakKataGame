package com.example.tebakkatagame.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;


import com.example.tebakkatagame.Activity.Tutorial.Tutor_Activity;
import com.example.tebakkatagame.R;
import com.example.tebakkatagame.Utils.SharePrefUtils;

public class Tahap_Activity extends BaseApp implements View.OnClickListener {

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahap);
        Tahap_Activity.context = getApplicationContext();
        getTahapComplete();
        setComponent();
    }

    private void setComponent() {
        find(R.id.img_kata_bergambar).setOnClickListener(this);
        find(R.id.img_suku_kata).setOnClickListener(this);
        find(R.id.img_tebak_huruf).setOnClickListener(this);
        find(R.id.img_ayo_membaca).setOnClickListener(this);
        find(R.id.img_btn_back).setOnClickListener(this);
    }

    private void getTahapComplete() {
        String tebakGambar = SharePrefUtils.getTahap(getActivity(), "TEBAK GAMBAR", "SUKU_KATA");
        if (!tebakGambar.isEmpty() && tebakGambar.equals("SUKU_KATA")) {
            find(R.id.img_lock_suku_kata).setVisibility(View.GONE);
            String sukuKata = SharePrefUtils.getTahap(getActivity(), "SUKU KATA", "TEBAK_HURUF");
            if (sukuKata.equals("TEBAK_HURUF")) {
                find(R.id.img_lock_tebak_kata).setVisibility(View.GONE);
                String tebakHuruf = SharePrefUtils.getTahap(getActivity(), "TEBAK HURUF", "MEMBACA");
                if (tebakHuruf.equals("MEMBACA")) {
                    find(R.id.img_lock_membaca).setVisibility(View.GONE);
                }
            }
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        clickSound();
        boolean hasTutor = false;

        switch (v.getId()) {
            case R.id.img_kata_bergambar:
                hasTutor = SharePrefUtils.getTutorial(this, "TEBAK GAMBAR");
                if (hasTutor) {
                    setIntent(LevelTahap_Activity.class, "TEBAK GAMBAR", "");
                } else {
                    setIntent(Tutor_Activity.class, "TEBAK GAMBAR", "");
                }
                break;
            case R.id.img_suku_kata:
                hasTutor = SharePrefUtils.getTutorial(this, "SUKU KATA");
                if (find(R.id.img_lock_suku_kata).getVisibility() == View.VISIBLE) {
                    showInfo("Kamu Belum Menyelesaikan Tahap Kata Bergambar!");
                } else {
                    if (hasTutor) {
                        setIntent(LevelTahap_Activity.class, "SUKU KATA", "");
                    } else {
                        setIntent(Tutor_Activity.class, "SUKU KATA", "");
                    }
                }
                break;
            case R.id.img_tebak_huruf:
                hasTutor = SharePrefUtils.getTutorial(this, "TEBAK HURUF");
                if (find(R.id.img_lock_tebak_kata).getVisibility() == View.VISIBLE) {
                    showInfo("Kamu Belum Menyelesaikan Tahap Suku Kata");
                } else {
                    if (hasTutor) {
                        setIntent(LevelTahap_Activity.class, "TEBAK HURUF", "");
                    } else {
                        setIntent(Tutor_Activity.class, "TEBAK HURUF", "");
                    }
                }
                break;
            case R.id.img_ayo_membaca:
                hasTutor = SharePrefUtils.getTutorial(this, "MEMBACA");
                if (find(R.id.img_lock_membaca).getVisibility() == View.VISIBLE) {
                    showInfo("Kamu Belum Menyelesaikan Tahap Tebak Huruf");
                } else {
                    if(hasTutor){
                        setIntent(LevelTahap_Activity.class, "MEMBACA", "");
                    }else{
                        setIntent(Tutor_Activity.class, "MEMBACA", "");
                    }
                }
                break;
            case R.id.img_btn_back:
                super.onBackPressed();
                break;

        }
    }

    public static Context getAppContext() {
        return Tahap_Activity.context;
    }
}