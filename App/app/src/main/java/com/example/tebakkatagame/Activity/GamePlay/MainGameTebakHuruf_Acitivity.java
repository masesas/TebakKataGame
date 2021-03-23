package com.example.tebakkatagame.Activity.GamePlay;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainGameTebakHuruf_Acitivity extends BaseApp {

    private int[] Images = {R.drawable.letter_a, R.drawable.letter_b, R.drawable.letter_u,R.drawable.letter_c,R.drawable.letter_d,
    R.drawable.letter_e};
    private int[] ImagesKey = {R.drawable.letter_u, R.drawable.letter_b};
    private ImageView img1,img2,img3,img4,img5,imgGuest1,imgGuest2;
    private ImageView[] ImagesArray = {img1,img2,img3,img4,img5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_tebak_huruf);

        initcomponent();
        setRandomImage();
    }

    private void initcomponent(){
        ImagesArray[0] = (ImageView) findViewById(R.id.img_choice1);
        ImagesArray[1] = (ImageView) findViewById(R.id.img_choice2);
        ImagesArray[2] = (ImageView) findViewById(R.id.img_choice3);
        ImagesArray[3] = (ImageView) findViewById(R.id.img_choice4);
        ImagesArray[4] = (ImageView) findViewById(R.id.img_choice5);

        imgGuest1 = (ImageView) findViewById(R.id.img_guess_1);
        imgGuest2 = (ImageView) findViewById(R.id.img_guess_2);

    }

    @SuppressLint("Recycle")
    private void setRandomImage(){
        TypedArray arrayLetter = getResources().obtainTypedArray(R.array.random_letter);
        final Random rand = new Random();
        final int key1[] = {1,20,3,15,22}; //dummy
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < ImagesArray.length; i++)
        {
            list.add(i);
        }
        Collections.shuffle(list);

        for (int i = 0; i <list.size(); i++) {
            final int rndInt = rand.nextInt(arrayLetter.length());
            final int rndImage = list.get(i);
            final String str = "letter_random_" + rndInt;
            final String strKey = "letter_random_" + key1[i];
            ImagesArray[rndImage].setImageDrawable(getResources().getDrawable(getResourceID(strKey, "drawable",getApplicationContext())));
            ImagesArray[rndImage].setTag(key1[i]);
            ImagesArray[rndImage].setOnClickListener(v -> {
                if(Integer.parseInt(String.valueOf(ImagesArray[rndImage].getTag())) == key1[0]){
                    imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1", "drawable",getApplicationContext())));
                }else if (Integer.parseInt(String.valueOf(ImagesArray[rndImage].getTag())) == key1[1]){
                    imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20", "drawable",getApplicationContext())));
                }else {
                    showInfo("Kata Tidak Cocok!!");
                }
            });
        }
    }

    protected final static int getResourceID (final String resName, final String resType, final Context ctx) {
        final int ResourceID = ctx.getResources().getIdentifier(resName, resType,ctx.getApplicationInfo().packageName);
        if (ResourceID == 0){
            throw new IllegalArgumentException("No resource string found with name " + resName);
        }
        else{
            return ResourceID;
        }
    }
}