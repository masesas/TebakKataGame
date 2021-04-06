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

import com.example.tebakkatagame.Activity.GamePlay.MainGameKataBergambar_Activity;
import com.example.tebakkatagame.Activity.GamePlay.MainGameSukuKata_Activity;
import com.example.tebakkatagame.Activity.GamePlay.MainGameTebakHuruf_Acitivity;
import com.example.tebakkatagame.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LevelTahap_Activity extends BaseApp {

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

        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item_level_menu, iconMenuList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                @SuppressLint({"ViewHolder", "InflateParams"}) View v = getLayoutInflater().inflate(R.layout.item_level_menu, null);
                if(position > 8){
                    setImageFromString((ImageView) v.findViewById(R.id.img_icon_number), iconMenuList.get(position), 200, 200);
                }else{
                    setImageFromString((ImageView) v.findViewById(R.id.img_icon_number), iconMenuList.get(position));
                }
                return v;
            }
        };

        gridViewMenu.setAdapter(menuAdapter);
        gridViewMenu.setOnItemClickListener((parent, view, position, id) -> {
            if (getIntent().hasExtra("TEBAK HURUF")) {
                setIntent(MainGameTebakHuruf_Acitivity.class, "LEVEL", String.valueOf(position + 1));
            } else if (getIntent().hasExtra("TEBAK GAMBAR")) {
                setIntent(MainGameKataBergambar_Activity.class, "LEVEL", position);
            } else if (getIntent().hasExtra("SUKU KATA")) {
                setIntent(MainGameSukuKata_Activity.class, "LEVEL", position);
            } else {
                setIntent(MainActivity.class, "LEVEL", position);
            }
        });
    }

    private void loadData() {
        if (getIntent().hasExtra("TEBAK HURUF")) {
            find(R.id.img_tittle_icon, ImageView.class).setImageResource(R.drawable.ic_tittle_tebak_huruf);
        } else if (getIntent().hasExtra("TEBAK GAMBAR")) {
            find(R.id.img_tittle_icon, ImageView.class).setImageResource(R.drawable.ic_tittle_kata_bergambar);
        } else if (getIntent().hasExtra("SUKU KATA")) {
            find(R.id.img_tittle_icon, ImageView.class).setImageResource(R.drawable.ic_tittle_suku_kata);
        } else {
            find(R.id.img_tittle_icon, ImageView.class).setImageResource(R.drawable.ic_tittle_tebak_huruf);
        }
    }
}