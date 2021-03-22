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

import java.util.Random;

public class MainGameTebakHuruf_Acitivity extends BaseApp implements View.OnClickListener{

    private int[] Images = {R.drawable.letter_a, R.drawable.letter_b, R.drawable.letter_u,R.drawable.letter_c,R.drawable.letter_d,
    R.drawable.letter_e};
    private int[] ImagesKey = {R.drawable.letter_u, R.drawable.letter_b};
    private ImageView img1,img2,img3,img4,img5,imgGuest1,imgGuest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_tebak_huruf);

        initcomponent();
    }

    private void initcomponent(){
        img1 = (ImageView) findViewById(R.id.img_choice1);
        img2 = (ImageView) findViewById(R.id.img_choice2);
        img3 = (ImageView) findViewById(R.id.img_choice3);
        img4 = (ImageView) findViewById(R.id.img_choice4);
        img5 = (ImageView) findViewById(R.id.img_choice5);

        imgGuest1 = (ImageView) findViewById(R.id.img_guess_1);
        imgGuest2 = (ImageView) findViewById(R.id.img_guess_2);


        @SuppressLint("Recycle")
        TypedArray arrayLetter = getResources().obtainTypedArray(R.array.random_letter);

        for (int i = 0; i < 5; i++) {
            final Random rand = new Random();
            final int rndInt = rand.nextInt(arrayLetter.length());
            final String str = "letter_random_" + rndInt;
            if(i == 0 ){
                img1.setImageDrawable(getResources().getDrawable(getResourceID(str, "drawable",getApplicationContext())));
                img1.setTag(rndInt);
            }else if (i == 1){
                img2.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1", "drawable",getApplicationContext())));
                img2.setTag("1");
            }else if (i == 2 ){
                img3.setImageDrawable(getResources().getDrawable(getResourceID(str, "drawable",getApplicationContext())));
                img3.setTag(rndInt);
            }else if (i == 3){
                img4.setImageDrawable(getResources().getDrawable(getResourceID(str, "drawable",getApplicationContext())));
                img4.setTag(rndInt);
            }else if(i == 4){
                img5.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20", "drawable",getApplicationContext())));
                img5.setTag("20");
            }
        }

//        img1.setImageResource(Images[new Random().nextInt(Images.length)]);
//        img2.setImageResource(ImagesKey[0]);
//        img3.setImageResource(Images[new Random().nextInt(Images.length)]);
//        img4.setImageResource(ImagesKey[1]);
//        img5.setImageResource(Images[new Random().nextInt(Images.length)]);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_choice1:
                if(img1.getTag().toString() == "1"){
                    imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1", "drawable",getApplicationContext())));
                }else if (img1.getTag().toString() == "20"){
                    imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20", "drawable",getApplicationContext())));
                }
                break;
            case R.id.img_choice2:
                if(img2.getTag().toString() == "1"){
                    imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1", "drawable",getApplicationContext())));
                }else if (img2.getTag().toString() == "20"){
                    imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20", "drawable",getApplicationContext())));
                }
                break;
            case R.id.img_choice3:
                if(img3.getTag().toString() == "1"){
                    imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1", "drawable",getApplicationContext())));
                }else if (img3.getTag().toString() == "20"){
                    imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20", "drawable",getApplicationContext())));
                }
                break;
            case R.id.img_choice4:
                if(img4.getTag().toString() == "1"){
                    imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1", "drawable",getApplicationContext())));
                }else if (img4.getTag().toString() == "20"){
                    imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20", "drawable",getApplicationContext())));
                }
                break;
            case R.id.img_choice5:
                if(img5.getTag().toString() == "1"){
                    imgGuest1.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_1", "drawable",getApplicationContext())));
                }else if (img5.getTag().toString() == "20"){
                    imgGuest2.setImageDrawable(getResources().getDrawable(getResourceID("letter_random_20", "drawable",getApplicationContext())));
                }
                break;
        }
    }
}