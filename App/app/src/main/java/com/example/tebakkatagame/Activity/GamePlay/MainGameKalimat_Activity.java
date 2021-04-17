package com.example.tebakkatagame.Activity.GamePlay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.SpeechRecognizer;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.R;

public class MainGameKalimat_Activity extends BaseApp {

    SpeechRecognizer mSpeechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_kalimat);
    }
}