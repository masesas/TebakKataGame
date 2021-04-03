package com.example.tebakkatagame.Activity.GamePlay;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.Activity.LevelTahap_Activity;
import com.example.tebakkatagame.Activity.Tahap_Activity;
import com.example.tebakkatagame.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainGameTebakHuruf_Acitivity extends BaseApp {

    private int[] Images = {R.drawable.letter_a, R.drawable.letter_b, R.drawable.letter_u,R.drawable.letter_c,R.drawable.letter_d,
    R.drawable.letter_e};
    private final Random rand = new Random();
    private ImageView img1,img2,img3,img4,img5,imgGuest1,imgGuest2,imgGuest3,imgGuest4,imgGuest5;
    private ImageView[] ImagesArray = {img1,img2,img3,img4,img5};
    private boolean isGuest3 = false;

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


    }
    private void loadData(){
        TypedArray arrayLetter = getResources().obtainTypedArray(R.array.random_letter);
        int rand1=  rand.nextInt(arrayLetter.length());
        int rand2=  rand.nextInt(arrayLetter.length());
        int rand3=  rand.nextInt(arrayLetter.length());
        String level = getIntent().getStringExtra("LEVEL");
        switch (level){
            case "1": //be-mo
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_12" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                int[] key1 ={1, 4, rand1, rand2, rand3};
                setRandomImage(key1);
                break;
            case "2": //gu-ru
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_17" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20" , "drawable",getApplicationContext())));
                int[] key2 = {6, 20, rand1, rand2, rand3};
                setRandomImage(key2);
                break;
            case "3": //la-yu
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_24" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20" , "drawable",getApplicationContext())));
                int[] key3 = {11, 0, rand1, rand2, rand3};
                setRandomImage(key3);
                break;
            case "4": //ha-ti
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_19" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_8" , "drawable",getApplicationContext())));
                int[] key4 = {7, 0, rand1, rand2, rand3};
                setRandomImage(key4);
                break;
            case "5": //sa-pi
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_15" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_8" , "drawable",getApplicationContext())));
                int[] key5 = {18, 0, rand1, rand2, rand3};
                setRandomImage(key5);

                break;
            case "6": //bak-so
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_18" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                int[] key6 = {1, 0, 10, rand2, rand3};
                setRandomImage(key6);
                isGuest3 = true;
                break;
            case "7": //le-bah
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_7" , "drawable",getApplicationContext())));
                int[] key7 = {11, 4, rand1, rand2, rand3};
                setRandomImage(key7);
                break;
            case "8": //bu-lan
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_11" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_13" , "drawable",getApplicationContext())));
                int[] key8 = {1, 20, rand1, rand2, rand3};
                setRandomImage(key8);
                break;
            case "9": //ke-cap
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_2" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_15" , "drawable",getApplicationContext())));
                int[] key9 = {10, 4, rand1, rand2, rand3};
                setRandomImage(key9);
                break;
            case "10": //po-hon
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_7" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_13" , "drawable",getApplicationContext())));
                int[] key10 = {15, 14, rand1, rand2, rand3};
                setRandomImage(key10);
                break;
            case "11": //ga-jah
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_9" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_7" , "drawable",getApplicationContext())));
                int[] key11 = {6, 0, rand1, rand2, rand3};
                setRandomImage(key11);
                break;
            case "12": //jam-bu
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20" , "drawable",getApplicationContext())));
                int[] key12 = {9, 0, 12, rand2, rand3};
                setRandomImage(key12);
                isGuest3 = true;
                break;
            case "13": //lam-pu
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_15" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20" , "drawable",getApplicationContext())));
                int[] key13 = {11, 0, 12, rand2, rand3};
                setRandomImage(key13);
                isGuest3 = true;
                break;
            case "14": //ba-lon
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_11" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_13" , "drawable",getApplicationContext())));
                int[] key14 = {1, 0, rand1, rand2, rand3};
                setRandomImage(key14);
                break;
            case "15": //sa-wah
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_22" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_7" , "drawable",getApplicationContext())));
                int[] key15 = {18, 0, rand1, rand2, rand3};
                setRandomImage(key15);
                break;
            case "16": //gi-tar
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_19" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_17" , "drawable",getApplicationContext())));
                int[] key16 = {6, 8, rand1, rand2, rand3};
                setRandomImage(key16);
                break;
            case "17": //bam-bu
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20" , "drawable",getApplicationContext())));
                int[] key17 = {1, 0, 12, rand2, rand3};
                setRandomImage(key17);
                isGuest3 = true;
                break;
            case "18": //pia-la
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_11" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_0" , "drawable",getApplicationContext())));
                int[] key18 = {15, 8, 0, rand2, rand3};
                setRandomImage(key18);
                isGuest3 = true;
                break;
            case "19": //pia-no
                imgGuest5.setVisibility(View.VISIBLE);
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_13" , "drawable",getApplicationContext())));
                imgGuest5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                int[] key19 = {15, 8, 0, rand2, rand3};
                setRandomImage(key19);
                isGuest3 = true;
                break;
            case "20": //ka-do
                imgGuest5.setVisibility(View.GONE);
                imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_3" , "drawable",getApplicationContext())));
                imgGuest4.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_14" , "drawable",getApplicationContext())));
                int[] key20 = {10, 0, rand1, rand2, rand3};
                setRandomImage(key20);
                isGuest3 = true;
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
                if(Integer.parseInt(String.valueOf(ImagesArray[rndImage].getTag())) == key[0]){
                    imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_" + key[0], "drawable",getApplicationContext())));
                }else if (Integer.parseInt(String.valueOf(ImagesArray[rndImage].getTag())) == key[1]){
                    imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_" + key[1], "drawable",getApplicationContext())));
                }else if (isGuest3){
                    if(Integer.parseInt(String.valueOf(ImagesArray[rndImage].getTag())) == key[2]){
                        imgGuest3.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_" + key[2], "drawable",getApplicationContext())));
                    }else {
                        showInfo("Kata Tidak Cocok!!");
                    }
                }else {
                    showInfo("Kata Tidak Cocok!!");
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
}