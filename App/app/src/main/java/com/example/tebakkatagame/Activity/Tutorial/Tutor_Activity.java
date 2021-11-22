package com.example.tebakkatagame.Activity.Tutorial;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tebakkatagame.Activity.LevelTahap_Activity;
import com.example.tebakkatagame.R;
import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class Tutor_Activity extends TutorialActivity {

    String tahap = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setComponent();
        //setContentView(R.layout.activity_tutor_suku_kata);

    }
    private void setComponent() {
        if (getIntent().hasExtra("TEBAK HURUF")) {
            tahap = "TEBAK HURUF";
        } else if (getIntent().hasExtra("TEBAK GAMBAR")) {
            tahap = "TEBAK GAMBAR";
        } else if (getIntent().hasExtra("SUKU KATA")) {
            tahap = "SUKU KATA";
        } else if(getIntent().hasExtra("MEMBACA")){
            tahap = "MEMBACA";
        }

        switch (tahap){
            case "SUKU KATA":
                addFragment(
                        new PermissionStep
                                .Builder()
                                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
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
                break;

            case "TEBAK HURUF":
                addFragment(
                        new PermissionStep
                                .Builder()
                                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
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
                break;
        }
    }

    @Override
    public void finishTutorial() {
        Toast.makeText(this, "Tutorial Selesai", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Tutor_Activity.this, LevelTahap_Activity.class);
        intent.putExtra(tahap, "");
        startActivity(intent);
    }


    @Override
    public void currentFragmentPosition(int position) {
        //Toast.makeText(this, "Position : " + position, Toast.LENGTH_SHORT).show();
    }


}