package com.example.tebakkatagame.Activity.GamePlay;

import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.Activity.LevelTahap_Activity;
import com.example.tebakkatagame.R;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MainGameTebakHuruf_Acitivity extends BaseApp {

    private ImageView imgChoise1, imgChoise2, imgChoise3, imgChoise4,imgGuest1,imgGuest2,imgGuest3,imgGuest4,imgIcon;

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
    }

    private void initcomponent(){

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
        imgGuest3 = (ImageView) findViewById(R.id.img_guess_3);
        imgGuest4 = (ImageView) findViewById(R.id.img_guess_4);

        imgIcon = (ImageView) findViewById(R.id.img_icon_tebak);
        konfettiView = findViewById(R.id.viewKonfetti);

    }

    private void loadData(){
        level = getIntent().getIntExtra("LEVEL", 1);
        switch (level){
            case 0: //be-mo
                imgIcon.setImageResource(R.drawable.ic_bemo);

                imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_b" , "drawable",getApplicationContext())));
                imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_e" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_m" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_o" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_m" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_e" , "drawable",getApplicationContext())));

                LinierArray[0].setTag("key");
                String[] key1 = {"m", "o"};
                setImage(key1);
                break;
            case 1: //gu-ru
                imgIcon.setImageResource(R.drawable.ic_teacher);

                imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_g" , "drawable",getApplicationContext())));
                imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_r" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_r" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key2 = {"r", "u"};
                setImage(key2);
                break;
            case 2: //la-yu
                imgIcon.setImageResource(R.drawable.ic_withered);

                imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_l" , "drawable",getApplicationContext())));
                imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_y" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_y" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key3 = {"y", "u"};
                setImage(key3);
                break;
            case 3: //ha-ti
                imgIcon.setImageResource(R.drawable.ic_heart);

                imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_h" , "drawable",getApplicationContext())));
                imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_t" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_i" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_t" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_o" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key4 = {"t", "i"};
                setImage(key4);
                break;
            case 4: //sa-pi
                imgIcon.setImageResource(R.drawable.ic_cow);
                imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_s" , "drawable",getApplicationContext())));
                imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_p" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_p" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_i" , "drawable",getApplicationContext())));
                LinierArray[1].setTag("key");
                String[] key5 = {"p", "i"};
                setImage(key5);
                break;
            case 5: //ka-ki
                imgIcon.setImageResource(R.drawable.ic_foot);
                imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_k" , "drawable",getApplicationContext())));
                imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_k" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_k" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_i" , "drawable",getApplicationContext())));
                LinierArray[1].setTag("key");
                String[] key6 = {"k", "i"};
                setImage(key6);
                break;
            case 6: //ro-ti
                imgIcon.setImageResource(R.drawable.ic_bread);
                imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_r" , "drawable",getApplicationContext())));
                imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_o" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_t" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_i" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_t" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_o" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key7 = {"t", "i"};
                setImage(key7);
                break;
            case 7: //ba-ju
                imgIcon.setImageResource(R.drawable.ic_tshirt);
                imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_b" , "drawable",getApplicationContext())));
                imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_j" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_j" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_o" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key8 = {"j", "u"};
                setImage(key8);
                break;
            case 8: //cu-ci
                imgIcon.setImageResource(R.drawable.ic_washing_machine);
                imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_c" , "drawable",getApplicationContext())));
                imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_c" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_i" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_c" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key9 = {"c", "i"};
                setImage(key9);
                break;
            case 9: //ru-sa
                imgIcon.setImageResource(R.drawable.ic_deer);
                imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_r" , "drawable",getApplicationContext())));
                imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_s" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_s" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_i" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key10 = {"s", "a"};
                setImage(key10);
                break;
            case 10: //gu-la
                imgIcon.setImageResource(R.drawable.ic_sugar);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_l" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_g" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_g" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_i" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key11 = {"g", "u"};
                setImage(key11);
                break;
            case 11: //ku-da
                imgIcon.setImageResource(R.drawable.ic_horse);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_d" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_k" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_k" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_e" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key12 = {"k", "u"};
                setImage(key12);
                break;
            case 12: //bi-ru
                imgIcon.setImageResource(R.drawable.ic_blue);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_r" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_b" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_i" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_b" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_o" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key13 = {"b", "i"};
                setImage(key13);
                break;
            case 13: //ro-da
                imgIcon.setImageResource(R.drawable.ic_wheel);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_d" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_r" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_o" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_r" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_e" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key14 = {"r", "o"};
                setImage(key14);
                break;
            case 14: //ru-ko
                imgIcon.setImageResource(R.drawable.ic_ruko);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_k" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_o" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_r" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_r" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key15 = {"r", "u"};
                setImage(key15);
                break;
            case 15: //la-ut
                imgIcon.setImageResource(R.drawable.ic_sea);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_t" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_l" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_l" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_o" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key16 = {"l", "a"};
                setImage(key16);
                break;
            case 16: //ba-yi
                imgIcon.setImageResource(R.drawable.ic_baby);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_y" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_i" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_b" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_b" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_e" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key17 = {"b", "a"};
                setImage(key17);
                break;
            case 17: //un-ta
                imgIcon.setImageResource(R.drawable.ic_camel);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_t" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_n" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_n" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key18 = {"u", "n"};
                setImage(key18);
                break;
            case 18: //bu-ku
                imgIcon.setImageResource(R.drawable.ic_buku);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_k" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_b" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_u" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_b" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_i" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key19 = {"b", "u"};
                setImage(key19);
                break;
            case 19: //ka-do
                imgIcon.setImageResource(R.drawable.ic_gift);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_d" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_o" , "drawable",getApplicationContext())));

                imgChoise1.setImageDrawable(getResources().getDrawable(getResourceID("letter_k" , "drawable",getApplicationContext())));
                imgChoise2.setImageDrawable(getResources().getDrawable(getResourceID("letter_a" , "drawable",getApplicationContext())));
                imgChoise3.setImageDrawable(getResources().getDrawable(getResourceID("letter_k" , "drawable",getApplicationContext())));
                imgChoise4.setImageDrawable(getResources().getDrawable(getResourceID("letter_o" , "drawable",getApplicationContext())));
                LinierArray[0].setTag("key");
                String[] key20 = {"k", "a"};
                setImage(key20);
                break;
            default:
                setIntent(LevelTahap_Activity.class, "TEBAK HURUF", "");
                break;
        }
    }

    @SuppressLint("Recycle")
    private void setImage(String[] key){
        for (int i = 0; i <2; i++) {
            int finalI = i;
            LinierArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(LinierArray[finalI].getTag() == "key"){
                        if(level < 10){
                            imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_" + key[0] , "drawable",getApplicationContext())));
                            imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_" + key[1] , "drawable",getApplicationContext())));

                        }else {
                            imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_" + key[0] , "drawable",getApplicationContext())));
                            imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_" + key[1] , "drawable",getApplicationContext())));
                        }
                        setCorectMode(imgGuest1);
                        setCorectMode(imgGuest2);
                        setCorectMode(imgGuest3);
                        setCorectMode(imgGuest4);
                        selebrateWin(true);
                    }else {
                        if(level < 10){
                            imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_empty" , "drawable",getApplicationContext())));
                            imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_empty" , "drawable",getApplicationContext())));
                            setWrongMode(imgGuest3);
                            setWrongMode(imgGuest4);
                            shakesAnimate(lyGuest2);
                        }else {
                            imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_empty" , "drawable",getApplicationContext())));
                            imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_empty" , "drawable",getApplicationContext())));
                            setWrongMode(imgGuest1);
                            setWrongMode(imgGuest2);
                            shakesAnimate(lyGuest1);
                        }


                    }
                }
            });
        }
    }

    private static int getResourceID (final String resName, final String resType, final Context ctx) {
        final int ResourceID = ctx.getResources().getIdentifier(resName, resType,ctx.getApplicationInfo().packageName);
        if (ResourceID == 0){
            throw new IllegalArgumentException("No resource string found with name " + resName);
        }
        else{
            return ResourceID;
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
                showWinDialog(level + 1, "TEBAK HURUF", true);
            }, 3000);
        } else {
            showWinDialog(level + 1, "TEBAK HURUF", false);
        }
    }
}