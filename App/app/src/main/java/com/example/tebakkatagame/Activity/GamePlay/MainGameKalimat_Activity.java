package com.example.tebakkatagame.Activity.GamePlay;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.ImageView;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.R;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MainGameKalimat_Activity extends BaseApp {

    private KonfettiView konfettiView;
    SpeechRecognizer mSpeechRecognizer;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_kalimat);
        setImageLevel();
        setComponent();
    }

    private void setComponent(){
        konfettiView = findViewById(R.id.viewKonfetti);

        find(R.id.btn_speak).setOnClickListener(v -> {
            selebrateWin(true);
        });
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
            handler.postDelayed(() -> {
                showWinDialog(level + 1, "MEMBACA", true);
            }, 3000);
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
                find(R.id.img_word_13, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_14, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_15, ImageView.class).setImageResource(R.drawable.letter_i);
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

}