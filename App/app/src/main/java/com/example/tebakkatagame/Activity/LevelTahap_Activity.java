package com.example.tebakkatagame.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tebakkatagame.Activity.GamePlay.MainGameKalimat_Activity;
import com.example.tebakkatagame.Activity.GamePlay.MainGameKataBergambar_Activity;
import com.example.tebakkatagame.Activity.GamePlay.MainGameSukuKata_Activity;
import com.example.tebakkatagame.Activity.GamePlay.MainGameTebakHuruf_Acitivity;
import com.example.tebakkatagame.Activity.Tutorial.Tutor_Activity;
import com.example.tebakkatagame.R;
import com.example.tebakkatagame.Utils.SharePrefUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LevelTahap_Activity extends BaseApp {

    ArrayAdapter<String> menuAdapter;
    private String TahapGame = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_tahap);
        setComponent();
        loadData();
    }

    private void setComponent() {
        GridView gridViewMenu = findViewById(R.id.gv_level);

        List<String> iconMenuList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String icon = "number_" + i;
            iconMenuList.add(icon);
        }

        int savedLevel = 0;
        if (getIntent().hasExtra("TEBAK HURUF")) {
            TahapGame = "TEBAK HURUF";
            savedLevel = SharePrefUtils.getLevel(getActivity(), "HURUF", 1);
        } else if (getIntent().hasExtra("TEBAK GAMBAR")) {
            TahapGame = "TEBAK GAMBAR";
            savedLevel = SharePrefUtils.getLevel(getActivity(), "GAMBAR", 1);
        } else if (getIntent().hasExtra("SUKU KATA")) {
            TahapGame = "SUKU KATA";
            savedLevel = SharePrefUtils.getLevel(getActivity(), "KATA", 1);
        } else if(getIntent().hasExtra("MEMBACA")){
            TahapGame = "MEMBACA";
            savedLevel = SharePrefUtils.getLevel(getActivity(), "MEMBACA", 1);
        }

        int finalSavedLevel = savedLevel;
        menuAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item_level_menu, iconMenuList) {
            @Override
            public boolean isEnabled(int position) {
                return position < finalSavedLevel;
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                @SuppressLint({"ViewHolder", "InflateParams"})
                View v = getLayoutInflater().inflate(R.layout.item_level_menu, null);

                if (position > 8) {
                    setImageFromString((ImageView) v.findViewById(R.id.img_icon_number), iconMenuList.get(position), 200, 200);
                } else {
                    setImageFromString((ImageView) v.findViewById(R.id.img_icon_number), iconMenuList.get(position));
                }

                if (position < finalSavedLevel) {
                    v.findViewById(R.id.img_lock).setVisibility(View.GONE);
                } else {
                    v.findViewById(R.id.img_lock).setVisibility(View.VISIBLE);
                }
                return v;
            }
        };

        gridViewMenu.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
        gridViewMenu.setOnItemClickListener((parent, view, position, id) -> {
            clickSound();
            if (getIntent().hasExtra("TEBAK HURUF")) {
                setIntent(MainGameTebakHuruf_Acitivity.class, "LEVEL", position);
            } else if (getIntent().hasExtra("TEBAK GAMBAR")) {
                setIntent(MainGameKataBergambar_Activity.class, "LEVEL", position);
            } else if (getIntent().hasExtra("SUKU KATA")) {
                setIntent(MainGameSukuKata_Activity.class, "LEVEL", position);
            } else  if (getIntent().hasExtra("MEMBACA")){
                setIntent(MainGameKalimat_Activity.class, "LEVEL", position);
            }
        });

        find(R.id.img_btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back();
            }
        });

        find(R.id.img_tittle_icon).setOnClickListener(v -> {
            setIntent(Tutor_Activity.class, TahapGame,"");
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setComponent();
    }

    private void loadData() {
        if (getIntent().hasExtra("TEBAK HURUF")) {
            find(R.id.img_tittle_icon, ImageView.class).setImageResource(R.drawable.ic_tebak_suku_kata);
        } else if (getIntent().hasExtra("TEBAK GAMBAR")) {
            find(R.id.img_tittle_icon, ImageView.class).setImageResource(R.drawable.ic_tittle_kata_bergambar);
        } else if (getIntent().hasExtra("SUKU KATA")) {
            find(R.id.img_tittle_icon, ImageView.class).setImageResource(R.drawable.ic_tittle_suku_kata);
        } else if (getIntent().hasExtra("MEMBACA")){
            find(R.id.img_tittle_icon, ImageView.class).setImageResource(R.drawable.ic_ayo_membaca);
        }
    }

    public void Back(){
        super.onBackPressed();
    }
}