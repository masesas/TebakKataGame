package com.example.tebakkatagame.Activity.GamePlay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import static com.example.tebakkatagame.Activity.GamePlay.MainGameKataBergambar_Activity.getErrorText;
import static com.example.tebakkatagame.Utils.Constanst.WORD_1;
import static com.example.tebakkatagame.Utils.Constanst.WORD_2;
import static com.example.tebakkatagame.Utils.Constanst.WORD_3;

public class MainGameSukuKata_Activity extends BaseApp implements RecognitionListener {

    Locale localeIndonesia = new Locale("id", "ID");
    SpeechRecognizer mSpeechRecognizer;
    private KonfettiView konfettiView;
    private GifImageView gifView;

    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_suku_kata);
        setComponent();
        setImageLevel();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setComponent() {
        konfettiView = findViewById(R.id.viewKonfetti_sukukata);
        gifView = findViewById(R.id.gif_speak);
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizer.setRecognitionListener(this);
        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, localeIndonesia);

        find(R.id.btn_speak).setOnClickListener(v -> {
            setTimerGif();
            mSpeechRecognizer.stopListening();
            mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
        });

        setOpeningStart();
    }

    private void setOpeningStart() {
        FrameLayout openingContainer = findViewById(R.id.container_opening);
        find(R.id.view_blur).bringToFront();
        openingContainer.bringToFront();
        FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );

        frameParams.width = 300;
        frameParams.height = 300;
        frameParams.bottomMargin = 200;
        frameParams.gravity = Gravity.CENTER;

        Runnable runnable = () -> {
            find(R.id.view_blur).setVisibility(View.VISIBLE);
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(frameParams);
            imageView.setImageResource(R.drawable.number_1);
            openingContainer.addView(imageView);
            imageView.postDelayed(() -> {
                imageView.setImageResource(R.drawable.number_2);
                imageView.postDelayed(() -> {
                    imageView.setImageResource(R.drawable.number_3);
                    imageView.postDelayed(() -> {
                        openingContainer.setVisibility(View.GONE);
                        find(R.id.view_blur).setVisibility(View.GONE);
                        burstEffect();
                    }, 1500);
                }, 1500);
            }, 1500);
        };

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            //TransitionManager.beginDelayedTransition();
            handler.postDelayed(runnable, 0);
        }, 0);
    }

    private void setTimerGif() {
        GifDrawable gifDrawable;
        try {
            gifDrawable = new GifDrawable(getResources().openRawResource(R.raw.btn_speak_5s));
            gifView.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        find(R.id.btn_speak).setVisibility(View.GONE);
        gifView.setVisibility(View.VISIBLE);
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //implements count timer if need
            }

            @Override
            public void onFinish() {
                gifView.setVisibility(View.GONE);
                find(R.id.btn_speak).setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void burstEffect(){
        konfettiView.post(() -> konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5))
                .setPosition(konfettiView.getX() + konfettiView.getWidth() / 2, konfettiView.getY()  + konfettiView.getHeight() / 3)
                .burst(100));
    }


    private void setResultSpech(String... eja) {
        if (setCorrectAnswer(eja[0])) {
            selebrateWin(true);
        } else {
            selebrateWin(false);
        }
    }

    private void selebrateWin(boolean isBenar) {
        find(R.id.view_blur).setVisibility(View.VISIBLE);
        if(isBenar){
            konfettiView.post(() -> konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                    .addSizes(new Size(12, 5f))
                    .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                    .streamFor(300, 5000L));
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                showWinDialog(level + 1, "SUKU KATA", true);
            }, 2000);
        }else{
            showWinDialog(level + 1, "SUKU KATA", false);
        }


    }

    private boolean setCorrectAnswer(String speech) {
        speech = speech.toLowerCase();
        int length = speech.length();
        switch (level) {
            case 0: //ibu
                if (length > 3 ) {
                    if(speech.charAt(0) == 'i'){
                    setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'b'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(2) == 'u'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("ibu");
                } else {
                    return false;
                }
            case 1: //abi
                if (length > 3 ) {
                    if(speech.charAt(0) == 'a'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'b'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(2) == 'i'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("abi");
                } else{
                    return false;
                }
            case 2: //ubi
                if (length > 3 ) {
                    if(speech.charAt(0) == 'u'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'b'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(2) == 'i'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("ubi");
                } else{
                    return false;
                }
            case 3: //api
                if (length > 3 ) {
                    if(speech.charAt(0) == 'a'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'p'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(2) == 'i'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("api");
                } else{
                    return false;
                }
            case 4: //soda
                if (length > 4 ) {
                    if(speech.charAt(0) == 's'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'o'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'd'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'a'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("sore");
                } else{
                    return false;
                }
            case 5: //pagi
                if (length > 4 ) {
                    if(speech.charAt(0) == 'p'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'a'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'g'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'i'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("pagi");
                } else{
                    return false;
                }
            case 6: //bagi
                if (length > 4 ) {
                    if(speech.charAt(0) == 'b'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'a'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'g'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'i'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("bagi");
                } else{
                    return false;
                }
            case 7: //goda
                if (length > 4 ) {
                    if(speech.charAt(0) == 'g'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'o'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'd'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'a'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("goda");
                } else{
                    return false;
                }
            case 8: //haji
                if (length > 4 ) {
                    if(speech.charAt(0) == 'h'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'a'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'j'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'i'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("haji");
                } else{
                    return false;
                }
            case 9: //beli
                if (length > 4 ) {
                    if(speech.charAt(0) == 'b'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'e'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'l'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'i'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("beli");
                } else{
                    return false;
                }
            case 10: //lupa
                if (length > 4 ) {
                    if(speech.charAt(0) == 'l'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'u'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'p'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'a'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("lupa");
                } else{
                    return false;
                }
            case 11: //jasa
                if (length > 4 ) {
                    if(speech.charAt(0) == 'j'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'a'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 's'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'a'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("jasa");
                } else{
                    return false;
                }
            case 12: //bawa
                if (length > 4 ) {
                    if(speech.charAt(0) == 'b'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'a'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'w'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'a'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("bawa");
                } else{
                    return false;
                }
            case 13: //coba
                if (length > 4 ) {
                    if(speech.charAt(0) == 'c'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'o'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'b'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'a'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("coba");
                } else{
                    return false;
                }
            case 14: //nada
                if (length > 4 ) {
                    if(speech.charAt(0) == 'n'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'a'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'd'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'a'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("nada");
                } else{
                    return false;
                }
            case 15: //jaga
                if (length > 4 ) {
                    if(speech.charAt(0) == 'j'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'a'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'g'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'a'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("jaga");
                } else{
                    return false;
                }
            case 16: //jago
                if (length > 4 ) {
                    if(speech.charAt(0) == 'j'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'a'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'g'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'o'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("jago");
                } else{
                    return false;
                }
            case 17: //sore
                if (length > 4 ) {
                    if(speech.charAt(0) == 's'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'o'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'r'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'e'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("sore");
                } else{
                    return false;
                }
            case 18: //hama
                if (length > 4 ) {
                    if(speech.charAt(0) == 'h'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'a'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'm'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'a'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("hama");
                } else{
                    return false;
                }
            case 19: //bola
                if (length > 4 ) {
                    if(speech.charAt(0) == 'b'){
                        setCorectMode(find(R.id.img_word_1));
                    }else{
                        setWrongMode(find(R.id.img_word_1));
                    }
                    if(speech.charAt(1) == 'o'){
                        setCorectMode(find(R.id.img_word_2));
                    }else{
                        setWrongMode(find(R.id.img_word_2));
                    }
                    if(speech.charAt(2) == 'l'){
                        setCorectMode(find(R.id.img_word_3));
                    }else{
                        setWrongMode(find(R.id.img_word_3));
                    }
                    if(speech.charAt(3) == 'a'){
                        setCorectMode(find(R.id.img_word_4));
                    }else{
                        setWrongMode(find(R.id.img_word_4));
                    }
                    return speech.toLowerCase().contains("bola");
                } else{
                    return false;
                }
            default:
                return false;
        }
    }

    private void setImageLevel() {
        level = getIntent().getIntExtra("LEVEL", 1);
        switch (level) {
            case 0: //ibu
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_2, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 1: //abi
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_2, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 2: //ubi
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_2, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 3: //api
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_2, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 4: //soda
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 5: //pagi
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 6: //bagi
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 7: //goda
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 8: //haji
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_h);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 9: //beli
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 10: //lupa
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 11: //jasa
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 12: //bawa
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_w);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 13: //coba
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_c);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 14: //nada
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_n);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 15: //jaga
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 16: //jago
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_o);
                break;
            case 17: //sore
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_e);
                break;
            case 18: //hama
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_h);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 19: //bola
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
        }
    }

    private void setCorectMode(View view) {
        if (view instanceof ImageView) {
            ((ImageView) view).setColorFilter(ContextCompat.getColor(getActivity(), R.color.green_500), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    private void setWrongMode(View view) {
        if (view instanceof ImageView) {
            ((ImageView) view).setColorFilter(ContextCompat.getColor(getActivity(), R.color.red_500), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {
//        showInfo(getErrorText(error));
    }

    @Override
    public void onResults(Bundle results) {
        List<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String[] result = matches.toArray(new String[]{});
        setResultSpech(result);
        find(R.id.tv_result_sukukata, TextView.class).setText(matches.get(0));//just dummy
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}