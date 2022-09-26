package com.example.tebakkatagame.Activity.GamePlay;

import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.Activity.LevelTahap_Activity;
import com.example.tebakkatagame.Activity.Tahap_Activity;
import com.example.tebakkatagame.R;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MainGameTebakHuruf_Acitivity extends BaseApp {

    private ImageView imgChoise1, imgChoise2, imgChoise3, imgChoise4, imgGuest1, imgGuest2, imgGuest3, imgGuest4, imgIcon;

    private boolean isGuest3 = false;
    private KonfettiView konfettiView;
    private LinearLayout lyChoice1, lyChoice2, lyGuest1, lyGuest2;
    private LinearLayout[] LinierArray = {lyChoice1, lyChoice2};
    private int level;

    public MainGameTebakHuruf_Acitivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_tebak_huruf);
        initcomponent();
        loadData();

        find(R.id.ly_next).setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void initcomponent() {
        LinierArray[0] = findViewById(R.id.ly_choice1);
        LinierArray[1] = findViewById(R.id.ly_choice2);

        imgChoise1 = (ImageView) findViewById(R.id.img_choice1);
        imgChoise2 = (ImageView) findViewById(R.id.img_choice2);
        imgChoise3 = (ImageView) findViewById(R.id.img_choice3);
        imgChoise4 = (ImageView) findViewById(R.id.img_choice4);

        lyGuest1 = findViewById(R.id.ly_guest1);
        lyGuest2 = findViewById(R.id.ly_guest2);

        imgGuest1 = (ImageView) findViewById(R.id.img_guess_1);
        imgGuest2 = (ImageView) findViewById(R.id.img_guess_2);
        imgGuest3 = findViewById(R.id.img_guess_3);
        imgGuest4 = (ImageView) findViewById(R.id.img_guess_4);

        imgIcon = (ImageView) findViewById(R.id.img_icon_tebak);
        konfettiView = findViewById(R.id.viewKonfetti);

        find(R.id.img_btn_back).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Tahap_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void loadData() {
        level = getIntent().getIntExtra("LEVEL", 1);
        switch (level) {
            case 0: //IBU
                imgIcon.setImageResource(R.drawable.ic_ibu);

                imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                imgGuest2.setVisibility(View.GONE);

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_b", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_o", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_b", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_u", "drawable", getApplicationContext())));

                LinierArray[0].setTag("key");
                String[] key1 = {"b", "u"};
                setImage(key1);
                break;
            case 1: //UBI
                imgIcon.setImageResource(R.drawable.ic_ubi);

                imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_u", "drawable", getApplicationContext())));
                imgGuest2.setVisibility(View.GONE);

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_b", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_b", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key2 = {"b", "i"};
                setImage(key2);
                break;
            case 2: //API
                imgIcon.setImageResource(R.drawable.ic_api);

                imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));
                imgGuest2.setVisibility(View.GONE);

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_p", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_y", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key3 = {"p", "i"};
                setImage(key3);
                break;
            case 3: //BUMI
                imgIcon.setImageResource(R.drawable.ic_earth);

                imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_b", "drawable", getApplicationContext())));
                imgGuest2.setImageDrawable(getDrawable(getResourceID("letter_u", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_t", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_m", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key4 = {"m", "i"};
                setImage(key4);
                break;
            case 4: //PADI
                imgIcon.setImageResource(R.drawable.ic_rice);

                imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_p", "drawable", getApplicationContext())));
                imgGuest2.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_t", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_d", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                LinierArray[1].setTag("key");
                String[] key5 = {"d", "i"};
                setImage(key5);
                break;
            case 5: //GIGI
                imgIcon.setImageResource(R.drawable.ic_tooth);

                imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_g", "drawable", getApplicationContext())));
                imgGuest2.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_g", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_g", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                LinierArray[1].setTag("key");
                String[] key6 = {"g", "i"};
                setImage(key6);
                break;
            case 6: //DADU
                imgIcon.setImageResource(R.drawable.ic_dice);

                imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_d", "drawable", getApplicationContext())));
                imgGuest2.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_d", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_d", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_u", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key7 = {"d", "u"};
                setImage(key7);
                break;
            case 7: //BIJI
                imgIcon.setImageResource(R.drawable.ic_seed);

                imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_b", "drawable", getApplicationContext())));
                imgGuest2.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_j", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_j", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_o", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key8 = {"j", "i"};
                setImage(key8);
                break;
            case 8: //GURU
                imgIcon.setImageResource(R.drawable.ic_guru);
                imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_g", "drawable", getApplicationContext())));
                imgGuest2.setImageDrawable(getDrawable(getResourceID("letter_u", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_r", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_r", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_u", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key9 = {"r", "u"};
                setImage(key9);
                break;
            case 9: //BIJI
                imgIcon.setImageResource(R.drawable.ic_seed);

                imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_b", "drawable", getApplicationContext())));
                imgGuest2.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_j", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_j", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_o", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key10 = {"j", "i"};
                setImage(key10);
                break;
            case 10: //gu-la
                imgIcon.setImageResource(R.drawable.ic_sugar);
                imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_l", "drawable", getApplicationContext())));
                imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_g", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_u", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_g", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key11 = {"g", "u"};
                setImage(key11);
                break;
            case 11: //PIPI
                imgIcon.setImageResource(R.drawable.ic_cheek);

                imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_p", "drawable", getApplicationContext())));
                imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_p", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_p", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_e", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key12 = {"p", "i"};
                setImage(key12);
                break;
            case 12: //KOPI
                imgIcon.setImageResource(R.drawable.ic_coffee);
                imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_k", "drawable", getApplicationContext())));
                imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_o", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_p", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_p", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_o", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key13 = {"p", "i"};
                setImage(key13);
                break;
            case 13: //DURI
                imgIcon.setImageResource(R.drawable.ic_thorn);
                imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_r", "drawable", getApplicationContext())));
                imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_d", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_u", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_d", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_e", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key14 = {"d", "u"};
                setImage(key14);
                break;
            case 14: //KAYU
                imgIcon.setImageResource(R.drawable.ic_wood);
                imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_k", "drawable", getApplicationContext())));
                imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_y", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_u", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_y", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key15 = {"y", "u"};
                setImage(key15);
                break;
            case 15: //RUSA
                imgIcon.setImageResource(R.drawable.ic_deer);
                imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_s", "drawable", getApplicationContext())));
                imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_r", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_u", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_r", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_o", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key16 = {"r", "u"};
                setImage(key16);
                break;
            case 16: //tali
                imgIcon.setImageResource(R.drawable.ic_rope);
                imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_l", "drawable", getApplicationContext())));
                imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_i", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_t", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_t", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_e", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key17 = {"t", "a"};
                setImage(key17);
                break;
            case 17: //PETA
                imgIcon.setImageResource(R.drawable.ic_map);
                imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_t", "drawable", getApplicationContext())));
                imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_p", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_e", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_p", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_o", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key18 = {"p", "e"};
                setImage(key18);
                break;
            case 18: //DESA
                imgIcon.setImageResource(R.drawable.ic_village);
                imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_s", "drawable", getApplicationContext())));
                imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_d", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_e", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_d", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_u", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key19 = {"d", "e"};
                setImage(key19);
                break;
            case 19: //RODA
                imgIcon.setImageResource(R.drawable.ic_wheel);
                imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_d", "drawable", getApplicationContext())));
                imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));

                imgChoise1.setImageDrawable(getDrawable(getResourceID("letter_r", "drawable", getApplicationContext())));
                imgChoise2.setImageDrawable(getDrawable(getResourceID("letter_a", "drawable", getApplicationContext())));
                imgChoise3.setImageDrawable(getDrawable(getResourceID("letter_r", "drawable", getApplicationContext())));
                imgChoise4.setImageDrawable(getDrawable(getResourceID("letter_o", "drawable", getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key20 = {"r", "o"};
                setImage(key20);
                break;
            default:
                setIntent(LevelTahap_Activity.class, "TEBAK HURUF", "");
                break;
        }
    }

    @SuppressLint("Recycle")
    private void setImage(String[] key) {
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            LinierArray[i].setOnClickListener(v -> {
                if (LinierArray[finalI].getTag() == "key") {
                    if (level < 10) {
                        imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_" + key[0], "drawable", getApplicationContext())));
                        imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_" + key[1], "drawable", getApplicationContext())));

                    } else {
                        imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_" + key[0], "drawable", getApplicationContext())));
                        imgGuest2.setImageDrawable(getDrawable(getResourceID("letter_" + key[1], "drawable", getApplicationContext())));
                    }
                    setCorectMode(imgGuest1);
                    setCorectMode(imgGuest2);
                    setCorectMode(imgGuest3);
                    setCorectMode(imgGuest4);
                    selebrateWin(true);
                } else {
                    if (level < 10) {
                        imgGuest3.setImageDrawable(getDrawable(getResourceID("letter_empty", "drawable", getApplicationContext())));
                        imgGuest4.setImageDrawable(getDrawable(getResourceID("letter_empty", "drawable", getApplicationContext())));
                        setWrongMode(imgGuest3);
                        setWrongMode(imgGuest4);
                        shakesAnimate(lyGuest2);
                    } else {
                        imgGuest1.setImageDrawable(getDrawable(getResourceID("letter_empty", "drawable", getApplicationContext())));
                        imgGuest2.setImageDrawable(getDrawable(getResourceID("letter_empty", "drawable", getApplicationContext())));
                        setWrongMode(imgGuest1);
                        setWrongMode(imgGuest2);
                        shakesAnimate(lyGuest1);
                    }
                    selebrateWin(false);


                }
            });
        }
    }

    private static int getResourceID(final String resName, final String resType, final Context ctx) {
        final int ResourceID = ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);
        if (ResourceID == 0) {
            throw new IllegalArgumentException("No resource string found with name " + resName);
        } else {
            return ResourceID;
        }
    }

    private void selebrateWin(boolean isBenar) {
        Handler handler = new Handler(Looper.getMainLooper());
        find(R.id.view_blur).setVisibility(View.VISIBLE);

        if (isBenar) {
            find(R.id.ly_next).setOnClickListener(v -> setIntentFinish(MainGameTebakHuruf_Acitivity.class, "LEVEL", (level + 1)));

            MediaPlayer mediaPlayerWin = MediaPlayer.create(getActivity(), R.raw.sound_applause);
            mediaPlayerWin.start();
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
            handler.postDelayed(() -> {
                showWinDialog(level + 1, "TEBAK HURUF", true);
            }, 3000);

        } else {
            showWinDialog(level + 1, "TEBAK HURUF", false);
        }

        handler.postDelayed(() -> {
            if (!isBenar) {
                find(R.id.img_next_level, ImageView.class).setImageDrawable(getDrawable(R.drawable.ic_repeat));
                find(R.id.tv_next, TextView.class).setText("Coba Lagi");
                find(R.id.ly_next).setOnClickListener(v -> setIntentFinish(MainGameTebakHuruf_Acitivity.class, "LEVEL", (level)));
            } else {
                find(R.id.img_next_level, ImageView.class).setImageDrawable(getDrawable(R.drawable.ic_next));
                find(R.id.tv_next, TextView.class).setText("Selanjutnya");
                find(R.id.ly_next).setOnClickListener(v -> setIntentFinish(MainGameTebakHuruf_Acitivity.class, "LEVEL", (level + 1)));
            }
            find(R.id.ly_next).setVisibility(View.VISIBLE);
        }, 4000);
    }

    public void Back() {
        super.onBackPressed();
    }
}