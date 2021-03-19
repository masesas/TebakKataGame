package com.example.tebakkatagame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tebakkatagame.R;

public class MainActivity extends BaseApp {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        find(R.id.btn_mulai).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), Tahap_Activity.class));
            finish();
        });
    }
}