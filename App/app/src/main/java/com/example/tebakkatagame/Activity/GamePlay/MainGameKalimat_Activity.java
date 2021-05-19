package com.example.tebakkatagame.Activity.GamePlay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

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

import static com.example.tebakkatagame.Utils.Constanst.WORD_1;

public class MainGameKalimat_Activity extends BaseApp implements RecognitionListener {

    private KonfettiView konfettiView;
    private GifImageView gifView;
    SpeechRecognizer mSpeechRecognizer;

    Locale localeIndonesia = new Locale("id", "ID");
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_kalimat);
        setImageLevel();
        setComponent();
    }

    private void setComponent() {
        konfettiView = findViewById(R.id.viewKonfetti);
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

    private void selebrateWin(boolean isBenar) {
        find(R.id.view_blur).setVisibility(View.VISIBLE);
        if (isBenar) {
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
            handler.postDelayed(() -> showWinDialog(level + 1, "MEMBACA", true), 3000);
        } else {
            showWinDialog(level + 1, "MEMBACA", false);
        }
    }


    private void setImageLevel() {
        /*
         * NOTE :
         *  - main = parent layout;
         *
         *
         *
         * */
        level = getIntent().getIntExtra("LEVEL", 1);
        find(R.id.img_word_16, ImageView.class).setVisibility(View.GONE);
        find(R.id.img_word_17, ImageView.class).setVisibility(View.GONE);
        find(R.id.img_word_10_1, ImageView.class).setVisibility(View.GONE);
        find(R.id.img_word_10_2, ImageView.class).setVisibility(View.GONE);
        find(R.id.ly_kalimat_tambahan).setVisibility(View.GONE);

        switch (level) {
            case 0: //ibu cuci ubi
                //main1 gone
                find(R.id.img_word_4, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                //main2 gone
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);
                //main3 gone
                find(R.id.img_word_14, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_15, ImageView.class).setVisibility(View.GONE);

                //main1
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_u);
                //main2
                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_c);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_c);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_i);
                //main4
                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 1: //dadu dodi satu
                //main1 gone
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                //main2 gone
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);
                //main3 gone
                find(R.id.img_word_15, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_i);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 2: //baju jojo hijau
                //main1 gone
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                //main2 gone
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_o);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_h);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_15, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 3: //bagai gigi hiu
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_14, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_15, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_5, ImageView.class).setImageResource(R.drawable.letter_i);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_i);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_h);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 4: //gado gado adi
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_14, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_15, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_o);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_o);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 5: //bola bili tiga
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_15, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_i);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 6: //kuku kaki kuda
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_15, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_i);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 7: // kado ema lucu
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_9, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_15, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_o);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_a);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_c);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 8: //ada dua keledai
                find(R.id.img_word_4, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_9, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_16, ImageView.class).setVisibility(View.VISIBLE);
                find(R.id.img_word_17, ImageView.class).setVisibility(View.VISIBLE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_a);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_a);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_15, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_16, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_17, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 9: //pada suatu sore
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_15, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_u);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_e);
                break;
            case 10: //ula pakai kebaya
                find(R.id.img_word_4, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_16, ImageView.class).setVisibility(View.VISIBLE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_a);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_10, ImageView.class).setImageResource(R.drawable.letter_i);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_15, ImageView.class).setImageResource(R.drawable.letter_y);
                find(R.id.img_word_16, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 11: //tanam padi di sawah
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);
                find(R.id.ly_kalimat_tambahan).setVisibility(View.VISIBLE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_n);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_5, ImageView.class).setImageResource(R.drawable.letter_m);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_i);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_w);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_15, ImageView.class).setImageResource(R.drawable.letter_h);
                break;
            case 12: //awan seputih kapas
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_10_1, ImageView.class).setVisibility(View.VISIBLE);
                find(R.id.img_word_10_2, ImageView.class).setVisibility(View.VISIBLE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_w);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_n);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_10, ImageView.class).setImageResource(R.drawable.letter_t);
                //find(R.id.img_word_11_tambahan, ImageView.class).setImageResource(R.drawable.letter_i);
                //find(R.id.img_word_12_tambahan, ImageView.class).setImageResource(R.drawable.letter_h);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_15, ImageView.class).setImageResource(R.drawable.letter_s);
                break;
            case 13: //panda makan bambu
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_n);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_5, ImageView.class).setImageResource(R.drawable.letter_a);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_10, ImageView.class).setImageResource(R.drawable.letter_n);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_15, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 14: //abi minum kopi
                find(R.id.img_word_4, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_15, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_i);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_n);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_10, ImageView.class).setImageResource(R.drawable.letter_m);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 15: //ada balon udara
                find(R.id.img_word_4, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_a);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_10, ImageView.class).setImageResource(R.drawable.letter_n);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_15, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 16: //gitar itu bagus
                find(R.id.img_word_9, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_5, ImageView.class).setImageResource(R.drawable.letter_r);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_u);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_15, ImageView.class).setImageResource(R.drawable.letter_s);
                break;
            case 17: //ada pohon apel
                find(R.id.img_word_4, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_5, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_15, ImageView.class).setVisibility(View.GONE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_a);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_h);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_10, ImageView.class).setImageResource(R.drawable.letter_n);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_l);
                break;
            case 18: //lebah ada di pohon
                find(R.id.img_word_9, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_10, ImageView.class).setVisibility(View.GONE);
                find(R.id.ly_kalimat_tambahan).setVisibility(View.VISIBLE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_5, ImageView.class).setImageResource(R.drawable.letter_h);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_a);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_h);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_15, ImageView.class).setImageResource(R.drawable.letter_n);
                break;
            case 19: //bunga mawar merah
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_n);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_5, ImageView.class).setImageResource(R.drawable.letter_a);

                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_8, ImageView.class).setImageResource(R.drawable.letter_w);
                find(R.id.img_word_9, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_10, ImageView.class).setImageResource(R.drawable.letter_r);

                find(R.id.img_word_11, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_12, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_15, ImageView.class).setImageResource(R.drawable.letter_h);

                break;

        }
    }

    private boolean setCorrectAnswer(String speech) {
        speech = speech.toLowerCase().replaceAll(" ", "");
        speech = speech.replaceAll("-", "");
        switch (level) {
            case 0: ////ibu cuci ubi
                if (speech.charAt(0) == 'i') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'b') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'u') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'c') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(4) == 'u') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(5) == 'c') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(6) == 'i') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(7) == 'u') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(8) == 'b') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(9) == 'i') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                return speech.toLowerCase().contains("ibucuciubi");
            case 1: //dadu dodi satu
                if (speech.charAt(0) == 'd') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'd') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'u') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 'd') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(5) == 'o') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(6) == 'd') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(7) == 'i') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(8) == '1') {
                    setCorectMode(find(R.id.img_word_11));
                    setCorectMode(find(R.id.img_word_12));
                    setCorectMode(find(R.id.img_word_13));
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    if (speech.charAt(8) == 's') {
                        setCorectMode(find(R.id.img_word_11));
                    } else {
                        setWrongMode(find(R.id.img_word_11));
                    }
                    if (speech.length() > 9 && speech.charAt(9) == 'a') {
                        setCorectMode(find(R.id.img_word_12));
                    } else {
                        setWrongMode(find(R.id.img_word_12));
                    }
                    if (speech.length() > 10 && speech.charAt(10) == 't') {
                        setCorectMode(find(R.id.img_word_13));
                    } else {
                        setWrongMode(find(R.id.img_word_13));
                    }
                    if (speech.length() > 11 && speech.charAt(11) == 'u') {
                        setCorectMode(find(R.id.img_word_14));
                    } else {
                        setWrongMode(find(R.id.img_word_14));
                    }
                }

                return speech.toLowerCase().contains("dadudodisatu") || speech.toLowerCase().contains("dadudodi1");
            case 2: //baju jojo hijau
                if (speech.charAt(0) == 'b') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'j') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'u') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 'j') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(5) == 'o') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(6) == 'j') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(7) == 'o') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(8) == 'h') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(9) == 'i') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(10) == 'j') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(11) == 'a') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                if (speech.charAt(12) == 'u') {
                    setCorectMode(find(R.id.img_word_15));
                } else {
                    setWrongMode(find(R.id.img_word_15));
                }
                return speech.toLowerCase().contains("bajujojohijau");
            case 3: ////bagai gigi hiu
                if (speech.charAt(0) == 'b') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'g') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 'i') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                if (speech.charAt(5) == 'g') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(6) == 'i') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(7) == 'g') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(8) == 'i') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(9) == 'h') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(10) == 'i') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(11) == 'u') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                return speech.toLowerCase().contains("bagaigigihiu");
            case 4: //gado gado adi
                if (speech.charAt(0) == 'g') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'd') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'o') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 'g') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                if (speech.charAt(5) == 'a') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(6) == 'g') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(7) == 'o') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(8) == 'a') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(9) == 'd') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(10) == 'i') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                return speech.toLowerCase().contains("gadogadoadi");
            case 5://bola bili tiga
                if (speech.charAt(0) == 'b') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'o') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'l') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.contains("billy")) {
                    setCorectMode(find(R.id.img_word_6));
                    setCorectMode(find(R.id.img_word_7));
                    setCorectMode(find(R.id.img_word_8));
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    if (speech.charAt(4) == 'b') {
                        setCorectMode(find(R.id.img_word_6));
                    } else {
                        setWrongMode(find(R.id.img_word_6));
                    }
                    if (speech.charAt(5) == 'i') {
                        setCorectMode(find(R.id.img_word_7));
                    } else {
                        setWrongMode(find(R.id.img_word_7));
                    }
                    if (speech.charAt(6) == 'l') {
                        setCorectMode(find(R.id.img_word_8));
                    } else {
                        setWrongMode(find(R.id.img_word_8));
                    }
                    if (speech.charAt(7) == 'i') {
                        setCorectMode(find(R.id.img_word_9));
                    } else {
                        setWrongMode(find(R.id.img_word_9));
                    }
                }

                if (speech.contains("3")) {
                    setCorectMode(find(R.id.img_word_11));
                    setCorectMode(find(R.id.img_word_12));
                    setCorectMode(find(R.id.img_word_13));
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    if (speech.length() > 8 && speech.charAt(8) == 't') {
                        setCorectMode(find(R.id.img_word_11));
                    } else {
                        setWrongMode(find(R.id.img_word_11));
                    }
                    if (speech.length() > 9 && speech.charAt(9) == 'i') {
                        setCorectMode(find(R.id.img_word_12));
                    } else {
                        setWrongMode(find(R.id.img_word_12));
                    }
                    if (speech.length() > 10 && speech.charAt(10) == 'g') {
                        setCorectMode(find(R.id.img_word_13));
                    } else {
                        setWrongMode(find(R.id.img_word_13));
                    }
                    if (speech.length() > 11 && speech.charAt(11) == 'a') {
                        setCorectMode(find(R.id.img_word_14));
                    } else {
                        setWrongMode(find(R.id.img_word_14));
                    }
                }

                return speech.toLowerCase().contains("bolabilitiga") || speech.toLowerCase().contains("bolabili3") || speech.toLowerCase().contains("bolabilly3");
            case 6: //kuku kaki kuda
                if (speech.charAt(0) == 'k') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'u') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'k') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'u') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 'k') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(5) == 'a') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(6) == 'k') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(7) == 'i') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(8) == 'k') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(9) == 'u') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(10) == 'd') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(10) == 'a') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                return speech.toLowerCase().contains("kukukakikuda");
            case 7: //// kado ema lucu
                if (speech.charAt(0) == 'k') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'd') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'o') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 'e') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(5) == 'm') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(6) == 'a') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(7) == 'l') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(8) == 'u') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(9) == 'c') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(10) == 'u') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                return speech.toLowerCase().contains("kadoemalucu");
            case 8: ////ada dua keledai
                if (speech.charAt(0) == 'a') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'd') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'a') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'd') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(4) == 'u') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(5) == 'a') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(6) == 'k') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(7) == 'e') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(9) == 'l') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(10) == 'e') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                if (speech.charAt(11) == 'd') {
                    setCorectMode(find(R.id.img_word_15));
                } else {
                    setWrongMode(find(R.id.img_word_15));
                }
                if (speech.charAt(12) == 'a') {
                    setCorectMode(find(R.id.img_word_16));
                } else {
                    setWrongMode(find(R.id.img_word_16));
                }
                if (speech.charAt(12) == 'i') {
                    setCorectMode(find(R.id.img_word_17));
                } else {
                    setWrongMode(find(R.id.img_word_17));
                }
                return speech.toLowerCase().contains("adaduakeledai");
            case 9: ////pada suatu sore
                if (speech.charAt(0) == 'p') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'd') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 's') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(5) == 'u') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(6) == 'a') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(7) == 't') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(8) == 'u') {
                    setCorectMode(find(R.id.img_word_10));
                } else {
                    setWrongMode(find(R.id.img_word_10));
                }
                if (speech.charAt(9) == 's') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(10) == 'o') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(11) == 'r') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(12) == 'e') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }

                return speech.toLowerCase().contains("padasuatusore");
            case 10: ////ula pakai kebaya
                if (speech.charAt(0) == 'u') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'l') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'a') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'p') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(4) == 'a') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(5) == 'k') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(6) == 'a') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(7) == 'i') {
                    setCorectMode(find(R.id.img_word_10));
                } else {
                    setWrongMode(find(R.id.img_word_10));
                }
                if (speech.charAt(8) == 'k') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(9) == 'e') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(10) == 'b') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(11) == 'a') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                if (speech.charAt(12) == 'y') {
                    setCorectMode(find(R.id.img_word_15));
                } else {
                    setWrongMode(find(R.id.img_word_15));
                }
                if (speech.charAt(13) == 'a') {
                    setCorectMode(find(R.id.img_word_16));
                } else {
                    setWrongMode(find(R.id.img_word_16));
                }
                return speech.toLowerCase().contains("ulapakaikebaya");
            case 11: //tanam padi di sawah
                if (speech.charAt(0) == 't') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'n') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 'm') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                if (speech.charAt(5) == 'p') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(6) == 'a') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(7) == 'd') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(8) == 'i') {
                    setCorectMode(find(R.id.img_word_10));
                } else {
                    setWrongMode(find(R.id.img_word_10));
                }
                if (speech.contains("di")) {
                    setCorectMode(find(R.id.img_word_tambahan1));
                    setCorectMode(find(R.id.img_word_tambahan2));
                } else {
                    setWrongMode(find(R.id.img_word_tambahan1));
                    setWrongMode(find(R.id.img_word_tambahan2));
                }
                if (speech.charAt(11) == 's') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(12) == 'a') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(13) == 'w') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(14) == 'a') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                if (speech.charAt(15) == 'h') {
                    setCorectMode(find(R.id.img_word_15));
                } else {
                    setWrongMode(find(R.id.img_word_15));
                }
                return speech.toLowerCase().contains("tanampadidisawah");
            case 12: ////awan seputih kapas
                if (speech.charAt(0) == 'a') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'w') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'a') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'n') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 's') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(5) == 'e') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(6) == 'p') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(7) == 'u') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(8) == 't') {
                    setCorectMode(find(R.id.img_word_10));
                } else {
                    setWrongMode(find(R.id.img_word_10));
                }
                if (speech.charAt(9) == 'i') {
                    setCorectMode(find(R.id.img_word_10_1));
                } else {
                    setWrongMode(find(R.id.img_word_10_1));
                }
                if (speech.charAt(10) == 'h') {
                    setCorectMode(find(R.id.img_word_10_2));
                } else {
                    setWrongMode(find(R.id.img_word_10_2));
                }
                if (speech.charAt(11) == 'k') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(12) == 'a') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(13) == 'p') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(14) == 's') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                if (speech.charAt(15) == 'd') {
                    setCorectMode(find(R.id.img_word_15));
                } else {
                    setWrongMode(find(R.id.img_word_15));
                }

                return speech.toLowerCase().contains("awanseputihkapas");
            case 13: //panda makan bambu
                if (speech.charAt(0) == 'p') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'n') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'd') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 'a') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                if (speech.charAt(5) == 'm') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(6) == 'a') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(7) == 'k') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(8) == 'a') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(9) == 'n') {
                    setCorectMode(find(R.id.img_word_10));
                } else {
                    setWrongMode(find(R.id.img_word_10));
                }
                if (speech.charAt(10) == 'b') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(11) == 'a') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(12) == 'm') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(11) == 'b') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                if (speech.charAt(12) == 'u') {
                    setCorectMode(find(R.id.img_word_15));
                } else {
                    setWrongMode(find(R.id.img_word_15));
                }

                return speech.toLowerCase().contains("pandamakanbambu");
            case 14: ////abi minum kopi
                if (speech.charAt(0) == 'a') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'b') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'i') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'm') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(4) == 'i') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(5) == 'n') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(6) == 'u') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(7) == 'm') {
                    setCorectMode(find(R.id.img_word_10));
                } else {
                    setWrongMode(find(R.id.img_word_10));
                }
                if (speech.charAt(8) == 'k') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(9) == 'o') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(10) == 'p') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(11) == 'i') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }

                return speech.toLowerCase().contains("abiminumkopi");
            case 15: //ada balon udara
                if (speech.charAt(0) == 'a') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'd') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'a') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'b') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(4) == 'a') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(5) == 'l') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(6) == 'o') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(7) == 'n') {
                    setCorectMode(find(R.id.img_word_10));
                } else {
                    setWrongMode(find(R.id.img_word_10));
                }
                if (speech.charAt(8) == 'u') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(9) == 'd') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(10) == 'a') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(11) == 'r') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                if (speech.charAt(12) == 'a') {
                    setCorectMode(find(R.id.img_word_15));
                } else {
                    setWrongMode(find(R.id.img_word_15));
                }

                return speech.toLowerCase().contains("adabalonudara");
            case 16: ////gitar itu bagus
                if (speech.charAt(0) == 'g') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'i') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 't') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 'r') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                if (speech.charAt(5) == 'i') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(6) == 't') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(7) == 'u') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(8) == 'b') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(9) == 'a') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(10) == 'g') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(10) == 'u') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                if (speech.charAt(10) == 's') {
                    setCorectMode(find(R.id.img_word_15));
                } else {
                    setWrongMode(find(R.id.img_word_15));
                }
                return speech.toLowerCase().contains("gitaritubagus");
            case 17: ////ada pohon apel
                if (speech.charAt(0) == 'a') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'd') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'a') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'p') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(4) == 'o') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(5) == 'h') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(6) == 'o') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(7) == 'n') {
                    setCorectMode(find(R.id.img_word_10));
                } else {
                    setWrongMode(find(R.id.img_word_10));
                }
                if (speech.charAt(8) == 'a') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(9) == 'p') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(10) == 'e') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(11) == 'l') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }

                return speech.toLowerCase().contains("adapohonapel");
            case 18: ////lebah ada di pohon
                if (speech.charAt(0) == 'l') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'e') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'b') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 'h') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                if (speech.charAt(5) == 'a') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(6) == 'd') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(7) == 'a') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.contains("di")) {
                    setCorectMode(find(R.id.img_word_tambahan1));
                    setCorectMode(find(R.id.img_word_tambahan2));
                } else {
                    setWrongMode(find(R.id.img_word_tambahan1));
                    setWrongMode(find(R.id.img_word_tambahan2));
                }
                if (speech.charAt(10) == 'p') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(11) == 'o') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(12) == 'h') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(13) == 'o') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                if (speech.charAt(14) == 'n') {
                    setCorectMode(find(R.id.img_word_15));
                } else {
                    setWrongMode(find(R.id.img_word_15));
                }
                return speech.toLowerCase().contains("lebahadadipohon");
            case 19: //bunga mawar merah
                if (speech.charAt(0) == 'b') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.charAt(1) == 'u') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.charAt(2) == 'n') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.charAt(3) == 'g') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.charAt(4) == 'a') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                if (speech.charAt(5) == 'm') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                if (speech.charAt(6) == 'a') {
                    setCorectMode(find(R.id.img_word_7));
                } else {
                    setWrongMode(find(R.id.img_word_7));
                }
                if (speech.charAt(7) == 'w') {
                    setCorectMode(find(R.id.img_word_8));
                } else {
                    setWrongMode(find(R.id.img_word_8));
                }
                if (speech.charAt(8) == 'a') {
                    setCorectMode(find(R.id.img_word_9));
                } else {
                    setWrongMode(find(R.id.img_word_9));
                }
                if (speech.charAt(9) == 'r') {
                    setCorectMode(find(R.id.img_word_10));
                } else {
                    setWrongMode(find(R.id.img_word_10));
                }
                if (speech.charAt(10) == 'm') {
                    setCorectMode(find(R.id.img_word_11));
                } else {
                    setWrongMode(find(R.id.img_word_11));
                }
                if (speech.charAt(11) == 'e') {
                    setCorectMode(find(R.id.img_word_12));
                } else {
                    setWrongMode(find(R.id.img_word_12));
                }
                if (speech.charAt(12) == 'r') {
                    setCorectMode(find(R.id.img_word_13));
                } else {
                    setWrongMode(find(R.id.img_word_13));
                }
                if (speech.charAt(13) == 'a') {
                    setCorectMode(find(R.id.img_word_14));
                } else {
                    setWrongMode(find(R.id.img_word_14));
                }
                if (speech.charAt(14) == 'h') {
                    setCorectMode(find(R.id.img_word_15));
                } else {
                    setWrongMode(find(R.id.img_word_15));
                }

                return speech.contains("bungamawarmerah");
            default:
                return false;
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

    private void setResultSpech(String... eja) {
        if (setCorrectAnswer(eja[0])) {
            selebrateWin(true);
        } else {
            selebrateWin(false);
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

    }

    @Override
    public void onResults(Bundle results) {
        List<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String[] result = matches.toArray(new String[]{});
        setResultSpech(result);
        find(R.id.tv_result, TextView.class).setText(matches.get(0));//just dummy
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}