package com.example.tebakkatagame.Activity.Tutorial;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tebakkatagame.Activity.LevelTahap_Activity;
import com.example.tebakkatagame.R;
import com.example.tebakkatagame.Utils.SharePrefUtils;
import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class Tutor_Activity extends TutorialActivity {

    String tahap = "";
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setComponent();
        //setContentView(R.layout.activity_tutor_suku_kata);

    }


    private void setComponent() {
        setPrevText("Kembali");
        setNextText("Selanjutnya");
        setCancelText("Lewati");

        if (getIntent().hasExtra("TEBAK HURUF")) {
            tahap = "TEBAK HURUF";
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.title_tebakhuruf))
                            .setContent(getString(R.string.pilih_level_1))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.tebaksukukata_1)
                            .setSummary(getString(R.string.continue_and_learn))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.memilihsukukata))
                            .setContent(getString(R.string.pilihhuruf))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.tebaksukukata_2)
                            .setSummary(getString(R.string.continue_and_learn))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.salah))
                            .setContent(getString(R.string.salahjawabantebak))
                            .setBackgroundColor(Color.parseColor("#4b585d"))
                            .setDrawable(R.drawable.tebaksukukata_3)
                            .setSummary(getString(R.string.continue_and_update))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.benar))
                            .setContent(getString(R.string.benarjawabantebak))
                            .setBackgroundColor(Color.parseColor("#4b585d"))
                            .setDrawable(R.drawable.tebaksukukata_4)
                            .setSummary(getString(R.string.continue_and_result))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.hasilmemuaskan))
                            .setContent(getString(R.string.lanjutmainlv2))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.tebaksukukata_5)
                            .build());
        } else if (getIntent().hasExtra("TEBAK GAMBAR")) {
            tahap = "TEBAK GAMBAR";
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.title_tebak_bergambar))
                            .setContent(getString(R.string.pilih_level_1))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.katabergambar1)
                            .setSummary(getString(R.string.continue_and_learn))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.merekamsuara))
                            .setContent(getString(R.string.tombolsuara))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.katabergambar2)
                            .setSummary(getString(R.string.continue_and_learn))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.salah))
                            .setContent(getString(R.string.salahjawabantebak))
                            .setBackgroundColor(Color.parseColor("#4b585d"))
                            .setDrawable(R.drawable.katabergambar3)
                            .setSummary(getString(R.string.continue_and_update))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.benar))
                            .setContent(getString(R.string.benarjawabantebak))
                            .setBackgroundColor(Color.parseColor("#4b585d"))
                            .setDrawable(R.drawable.katabergambar4)
                            .setSummary(getString(R.string.continue_and_result))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.hasilmemuaskan))
                            .setContent(getString(R.string.lanjutmainlv2))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.katabergambar5)
                            .build());
        } else if (getIntent().hasExtra("SUKU KATA")) {
            tahap = "SUKU KATA";
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.title_sukukata))
                            .setContent(getString(R.string.pilih_level_1))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.sukukata_1)
                            .setSummary(getString(R.string.continue_and_learn))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.merekamsuara))
                            .setContent(getString(R.string.tombolsuara))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.sukukata_2)
                            .setSummary(getString(R.string.continue_and_learn))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.salah))
                            .setContent(getString(R.string.salahjawaban))
                            .setBackgroundColor(Color.parseColor("#4b585d"))
                            .setDrawable(R.drawable.sukukata_3)
                            .setSummary(getString(R.string.continue_and_update))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.benar))
                            .setContent(getString(R.string.benarjawaban))
                            .setBackgroundColor(Color.parseColor("#4b585d"))
                            .setDrawable(R.drawable.sukukata_4)
                            .setSummary(getString(R.string.continue_and_result))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.hasilmemuaskan))
                            .setContent(getString(R.string.lanjutmainlv2))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.sukukata_5)
                            .build());
        } else if (getIntent().hasExtra("MEMBACA")) {
            tahap = "MEMBACA";
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.title_ayo_membaca))
                            .setContent(getString(R.string.pilih_level_1))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.ayomembaca1)
                            .setSummary(getString(R.string.continue_and_learn))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.merekamsuara))
                            .setContent(getString(R.string.tombolsuara))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.ayomembaca2)
                            .setSummary(getString(R.string.continue_and_learn))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.salah))
                            .setContent(getString(R.string.salahjawabantebak))
                            .setBackgroundColor(Color.parseColor("#4b585d"))
                            .setDrawable(R.drawable.ayomembaca3)
                            .setSummary(getString(R.string.continue_and_update))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.benar))
                            .setContent(getString(R.string.benarjawabantebak))
                            .setBackgroundColor(Color.parseColor("#4b585d"))
                            .setDrawable(R.drawable.ayomembaca4)
                            .setSummary(getString(R.string.continue_and_result))
                            .build());
            addFragment(
                    new Step.Builder()
                            .setTitle(getString(R.string.hasilmemuaskan))
                            .setContent(getString(R.string.lanjutmainlv2))
                            .setBackgroundColor(Color.parseColor("#a4d8ed"))
                            .setDrawable(R.drawable.ayomembaca5)
                            .build());
        }
    }

    @Override
    public void finishTutorial() {
        SharePrefUtils.saveTutorial(this, tahap, true);
        Toast.makeText(this, "Tutorial Selesai", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Tutor_Activity.this, LevelTahap_Activity.class);
        intent.putExtra(tahap, "");
        startActivity(intent);
        finish();
    }


    @Override
    public void currentFragmentPosition(int position) {
        currentPosition = position;
        //Toast.makeText(this, "Position : " + position, Toast.LENGTH_SHORT).show();
    }


}