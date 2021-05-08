package com.example.tebakkatagame.Activity.GamePlay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.Activity.LevelTahap_Activity;
import com.example.tebakkatagame.Activity.Tahap_Activity;
import com.example.tebakkatagame.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static com.example.tebakkatagame.Utils.Constanst.WORD_1;

public class MainGameTebakHuruf_Acitivity extends BaseApp {

    private int[] Images = {R.drawable.letter_a, R.drawable.letter_b, R.drawable.letter_u,R.drawable.letter_c,R.drawable.letter_d,
    R.drawable.letter_e};
    private final Random rand = new Random();
    private ImageView img1,img2,img3,img4,img5,imgGuest1,imgGuest2,imgGuest3,imgGuest4,imgGuest5,imgIcon;
    private ImageView[] ImagesArray = {img1,img2,img3,img4,img5};
    private boolean isGuest3 = false;
    private KonfettiView konfettiView;
    private int level;
    private int countCorrect = 0;
    private int countWrong = 0;
    private int countDuplicate = 0, countDuplicate2=0, countDuplicate3=0;

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
        ImagesArray[0] = (ImageView) findViewById(R.id.img_choice1);
        ImagesArray[1] = (ImageView) findViewById(R.id.img_choice2);
        ImagesArray[2] = (ImageView) findViewById(R.id.img_choice3);
        ImagesArray[3] = (ImageView) findViewById(R.id.img_choice4);
        ImagesArray[4] = (ImageView) findViewById(R.id.img_choice5);

        imgGuest1 = (ImageView) findViewById(R.id.img_guess_1);
        imgGuest2 = (ImageView) findViewById(R.id.img_guess_2);
        imgGuest3 = (ImageView) findViewById(R.id.img_guess_3);
        imgGuest4 = (ImageView) findViewById(R.id.img_guess_4);
        imgGuest5 = (ImageView) findViewById(R.id.img_guess_5);

        imgIcon = (ImageView) findViewById(R.id.img_icon_tebak);
        konfettiView = findViewById(R.id.viewKonfetti);


    }

    private void loadData(){
        TypedArray arrayLetter = getResources().obtainTypedArray(R.array.random_letter);
        int rand1=  rand.nextInt(arrayLetter.length());
        int rand2=  rand.nextInt(arrayLetter.length());
        int rand3=  rand.nextInt(arrayLetter.length());
        level = getIntent().getIntExtra("LEVEL", 1);
        switch (level){
            case 0: //be-mo
                imgIcon.setImageResource(R.drawable.ic_bemo);
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_12" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                imgGuest3.setColorFilter(ContextCompat.getColor(getActivity(), R.color.red_500), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgGuest4.setColorFilter(ContextCompat.getColor(getActivity(), R.color.red_500), android.graphics.PorterDuff.Mode.MULTIPLY);
                int[] key1 ={1, 4, rand1, rand2, rand3};
                setRandomImage(key1);
                break;
            case 1: //gu-ru
                imgIcon.setImageResource(R.drawable.ic_teacher);
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_17" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20" , "drawable",getApplicationContext())));
                int[] key2 = {6, 20, rand1, rand2, rand3};
                setRandomImage(key2);
                break;
            case 2: //la-yu
                imgIcon.setImageResource(R.drawable.ic_withered);
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_24" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20" , "drawable",getApplicationContext())));
                int[] key3 = {11, 0, rand1, rand2, rand3};
                setRandomImage(key3);
                break;
            case 3: //ha-ti
                imgIcon.setImageResource(R.drawable.ic_heart);
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_19" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_8" , "drawable",getApplicationContext())));
                int[] key4 = {7, 0, rand1, rand2, rand3};
                setRandomImage(key4);
                break;
            case 4: //sa-pi
                imgIcon.setImageResource(R.drawable.ic_cow);
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_15" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_8" , "drawable",getApplicationContext())));
                int[] key5 = {18, 0, rand1, rand2, rand3};
                setRandomImage(key5);

                break;
            case 5: //bak-so
                imgIcon.setImageResource(R.drawable.ic_meatballs);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_18" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                int[] key6 = {1, 0, 10, rand2, rand3};
                setRandomImage(key6);
                isGuest3 = true;
                break;
            case 6: //le-bah
                imgIcon.setImageResource(R.drawable.ic_bee);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_7" , "drawable",getApplicationContext())));
                int[] key7 = {11, 4, rand1, rand2, rand3};
                setRandomImage(key7);
                break;
            case 7: //bu-lan
                imgIcon.setImageResource(R.drawable.ic_halfmoon);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_11" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_13" , "drawable",getApplicationContext())));
                int[] key8 = {1, 20, rand1, rand2, rand3};
                setRandomImage(key8);
                break;
            case 8: //ke-cap
                imgIcon.setImageResource(R.drawable.ic_kecap);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_2" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_15" , "drawable",getApplicationContext())));
                int[] key9 = {10, 4, rand1, rand2, rand3};
                setRandomImage(key9);
                break;
            case 9: //po-hon
                imgIcon.setImageResource(R.drawable.ic_trees);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_7" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_13" , "drawable",getApplicationContext())));
                int[] key10 = {15, 14, rand1, rand2, rand3};
                setRandomImage(key10);
                break;
            case 10: //ga-jah
                imgIcon.setImageResource(R.drawable.ic_elephant);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_9" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_7" , "drawable",getApplicationContext())));
                int[] key11 = {6, 0, rand1, rand2, rand3};
                setRandomImage(key11);
                break;
            case 11: //jam-bu
                imgIcon.setImageResource(R.drawable.ic_guava);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20" , "drawable",getApplicationContext())));
                int[] key12 = {9, 0, 12, rand2, rand3};
                setRandomImage(key12);
                isGuest3 = true;
                break;
            case 12: //lam-pu
                imgIcon.setImageResource(R.drawable.ic_ligth);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_15" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20" , "drawable",getApplicationContext())));
                int[] key13 = {11, 0, 12, rand2, rand3};
                setRandomImage(key13);
                isGuest3 = true;
                break;
            case 13: //ba-lon
                imgIcon.setImageResource(R.drawable.ic_balloon);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_11" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_13" , "drawable",getApplicationContext())));
                int[] key14 = {1, 0, rand1, rand2, rand3};
                setRandomImage(key14);
                break;
            case 14: //sa-wah
                imgIcon.setImageResource(R.drawable.ic_ricefield);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_22" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_7" , "drawable",getApplicationContext())));
                int[] key15 = {18, 0, rand1, rand2, rand3};
                setRandomImage(key15);
                break;
            case 15: //gi-tar
                imgIcon.setImageResource(R.drawable.ic_guitar);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_19" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_17" , "drawable",getApplicationContext())));
                int[] key16 = {6, 8, rand1, rand2, rand3};
                setRandomImage(key16);
                break;
            case 16: //bam-bu
                imgIcon.setImageResource(R.drawable.ic_bamboo);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20" , "drawable",getApplicationContext())));
                int[] key17 = {1, 0, 12, rand2, rand3};
                setRandomImage(key17);
                isGuest3 = true;
                break;
            case 17: //pia-la
                imgIcon.setImageResource(R.drawable.ic_trophy);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_11" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                int[] key18 = {15, 8, 0, rand2, rand3};
                setRandomImage(key18);
                isGuest3 = true;
                break;
            case 18: //pia-no
                imgIcon.setImageResource(R.drawable.ic_piano);
                imgIcon.setImageResource(R.drawable.ic_trophy);
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_13" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                int[] key19 = {15, 8, 0, rand2, rand3};
                setRandomImage(key19);
                isGuest3 = true;
                break;
            case 19: //ka-do
                imgIcon.setImageResource(R.drawable.ic_gift);
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_3" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                int[] key20 = {10, 0, rand1, rand2, rand3};
                setRandomImage(key20);
                break;
            default:
                setIntent(LevelTahap_Activity.class, "TEBAK HURUF", "");
                break;
        }
    }

    @SuppressLint("Recycle")
    private void setRandomImage(int[] key){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < ImagesArray.length; i++)
        {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i <list.size(); i++) {
            final int rndImage = list.get(i);
            final String strKey = "letter_random_" + key[i];
            ImagesArray[rndImage].setImageDrawable(getResources().getDrawable(getResourceID(strKey, "drawable",getApplicationContext())));
            ImagesArray[rndImage].setTag(key[i]);
            ImagesArray[rndImage].setOnClickListener(v -> {
                if(Integer.parseInt(String.valueOf(ImagesArray[rndImage].getTag())) == key[0]) {
                    if(countDuplicate == 0) {
                        countCorrect ++;
                        countDuplicate ++;
                    }
                    imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_" + key[0], "drawable", getApplicationContext())));
                    shakesAnimate(imgGuest1);
                    setCorectMode(imgGuest1);
                }else if (Integer.parseInt(String.valueOf(ImagesArray[rndImage].getTag())) == key[1]){
                    if(countDuplicate2 == 0) {
                        countCorrect ++;
                        countDuplicate2 ++;
                    }
                    imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_" + key[1], "drawable",getApplicationContext())));
                    setCorectMode(imgGuest2);
                    shakesAnimate(imgGuest2);
                }else if(isGuest3) {
                    if (Integer.parseInt(String.valueOf(ImagesArray[rndImage].getTag())) == key[2]) {
                        if (countDuplicate3 == 0) {
                            countCorrect++;
                            countDuplicate3++;
                        }
                        imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_" + key[2], "drawable", getApplicationContext())));
                        setCorectMode(imgGuest3);
                        shakesAnimate(imgGuest3);
                    } else {
                        if (countDuplicate3 == 0) {
                            setWrongMode(imgGuest3);
                            shakesAnimate(imgGuest3);
                        }
                    }
                }else {
                    if (countDuplicate == 0) {
                        setWrongMode(imgGuest1);
                        shakesAnimate(imgGuest1);
                    }else if (countDuplicate2 == 0){
                        setWrongMode(imgGuest2);
                        shakesAnimate(imgGuest2);
                    }else if (countDuplicate3 == 0){
                        setWrongMode(imgGuest3);
                        shakesAnimate(imgGuest3);
                    }else{
                        setWrongMode(find(R.id.ly_guest));
                        shakesAnimate(find(R.id.ly_guest));
                    }

                }

                //dialogwin
                if(isGuest3){
                    if(countCorrect == 3 ){
                        selebrateWin(true);
                        setCorectMode(imgGuest4);
                        setCorectMode(imgGuest5);
                    }
                }else {
                    if(countCorrect == 2 ){
                        setCorectMode(imgGuest3);
                        setCorectMode(imgGuest4);
                        selebrateWin(true);
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
                showWinDialog(level + 1, "TEBAK HURUF", true);
            }, 2000);
        }else{
            showWinDialog(level + 1, "TEBAK HURUF", false);
        }


    }

}