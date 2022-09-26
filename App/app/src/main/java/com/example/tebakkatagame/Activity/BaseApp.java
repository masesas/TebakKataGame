package com.example.tebakkatagame.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.tebakkatagame.Activity.GamePlay.WinDialogFragment;
import com.example.tebakkatagame.R;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.tebakkatagame.Utils.Constanst.ONESEC;


public class BaseApp extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }


    public <T extends View> T to(View v, Class<? super T> s) {
        return (T) (v);
    }

    public <T extends View> T find(int id) {
        return (T) findViewById(id);
    }

    public <T extends View> T find(int id, Class<? super T> s) {
        return (T) findViewById(id);
    }

    public <T extends View> T findView(View v, int id, Class<? super T> s) {
        return (T) v.findViewById(id);
    }

    public Activity getActivity() {
        return this;
    }

    public void showInfo(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setImageFromString(ImageView icon, String imageName) {
        Resources res = getResources();
        int resID = res.getIdentifier(imageName, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID);
        icon.setImageDrawable(drawable);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setImageFromString(ImageView icon, String imageName, int width, int height) {
        Resources res = getResources();
        int resID = res.getIdentifier(imageName, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID);
        icon.setImageDrawable(drawable);
        FrameLayout.LayoutParams frameLayout = new FrameLayout.LayoutParams(width, height);
        icon.setLayoutParams(frameLayout);
        icon.getLayoutParams().height = 260;
        icon.getLayoutParams().width = 260;
        icon.requestLayout();
    }

    public void setIntent(Class<?> to, String key, String value) {
        Intent intent = new Intent(getActivity(), to);
        intent.putExtra(key, value);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void setIntentFinish(Class<?> to, String key, String value) {
        Intent intent = new Intent(getActivity(), to);
        intent.putExtra(key, value);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void setIntentFinish(Class<?> to, String key, int value) {
        Intent intent = new Intent(getActivity(), to);
        intent.putExtra(key, value);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void setIntent(Class<?> to, String key, int value) {
        Intent intent = new Intent(getActivity(), to);
        intent.putExtra(key, value);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void setIntent(Class<?> to) {
        Intent intent = new Intent(getActivity(), to);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    public YoYo.YoYoString shakesAnimate(View view) {
        return YoYo.with(Techniques.Tada).duration(ONESEC).playOn(view);
    }

    public YoYo.YoYoString bounceAnimate(View view) {
        return YoYo.with(Techniques.BounceIn).duration(ONESEC).playOn(view);
    }

    public YoYo.YoYoString wafeAnimate(View view) {
        return YoYo.with(Techniques.Swing).duration(5000).repeat(10).playOn(view);
    }

    public YoYo.YoYoString rollAnimate(View view) {
        return YoYo.with(Techniques.BounceInDown).duration(3000).repeat(2).playOn(view);
    }

    public void setIntentLevel(Class context, String key, String value) {
        Intent intent = new Intent(getActivity(), context);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    public void setIntentLevel(Class context, String key1, String key2, String value1, String value2) {
        Intent intent = new Intent(getActivity(), context);
        intent.putExtra(key1, value1);
        intent.putExtra(key2, value2);
        startActivity(intent);
        finish();
    }

    public void showWinDialog(int level, String tahap, boolean isBenar) {
        FragmentManager fm = getSupportFragmentManager();
        WinDialogFragment winDialogFragment = WinDialogFragment.newInstance(level, tahap, isBenar);
        winDialogFragment.show(fm, "NEXT_LEVEL");
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = ((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void setViewAndChildrenEnabled(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                setViewAndChildrenEnabled(child, enabled);
            }
        }
    }

    public void clickSound() {
        MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sound_click_coin);
        mediaPlayer.start();
        Runnable runnable = mediaPlayer::stop;
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable, 1000);
    }

    protected void setCorectMode(View view) {
        if (view instanceof ImageView) {
            ((ImageView) view).setColorFilter(ContextCompat.getColor(getActivity(), R.color.green_500), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    protected void setWrongMode(View view) {
        if (view instanceof ImageView) {
            ((ImageView) view).setColorFilter(ContextCompat.getColor(getActivity(), R.color.red_500), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    protected boolean setCorrectWord(int level, String speech) {
        speech = speech.toLowerCase();
        switch (level) {
            case 0: //ibu
                if (speech.length() > 0 && speech.charAt(0) == 'i') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'b') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'u') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("ibu");
            case 1: //ubi
                if (speech.length() > 0 && speech.charAt(0) == 'u') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'b') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'i') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("ubi");
            case 2: //api
                if (speech.length() > 0 && speech.charAt(0) == 'a') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'p') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'i') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }

                return speech.toLowerCase().contains("api");
            case 3: //bumi
                if (speech.length() > 0 && speech.charAt(0) == 'b') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'u') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'm') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'i') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("bumi");
            case 4: //padi
                if (speech.length() > 0 && speech.charAt(0) == 'p') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'd') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'i') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("padi");
            case 5: //gigi
                if (speech.length() > 0 && speech.charAt(0) == 'g') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'i') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'g') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'i') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("gigi");
            case 6: //dadu
                if (speech.length() > 0 && speech.charAt(0) == 'd') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'd') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'u') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("dadu");
            case 7: //biji
                if (speech.length() > 0 && speech.charAt(0) == 'b') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'i') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'j') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'i') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("biji");
            case 8: //guru
                if (speech.length() > 0 && speech.charAt(0) == 'g') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'u') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'r') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'u') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("guru");
            case 9: //biji
                if (speech.length() > 0 && speech.charAt(0) == 'b') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'i') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'j') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'i') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("biji");
            case 10: //gula
                if (speech.length() > 0 && speech.charAt(0) == 'g') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'u') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'l') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("gula");
            case 11: //pipi
                if (speech.length() > 0 && speech.charAt(0) == 'p') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'i') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'p') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'i') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("pipi");
            case 12: //kopi
                if (speech.length() > 0 && speech.charAt(0) == 'k') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'o') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'p') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'i') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("kopi");
            case 13: //duri
                if (speech.length() > 0 && speech.charAt(0) == 'd') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'u') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'r') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'i') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("duri");
            case 14: //kayu
                if (speech.length() > 0 && speech.charAt(0) == 'k') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'y') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'u') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("kayu");
            case 15: //rusa
                if (speech.length() > 0 && speech.charAt(0) == 'r') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'u') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 's') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("rusa");
            case 16: //tali
                if (speech.length() > 0 && speech.charAt(0) == 't') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'a') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'l') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'i') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }

                return speech.toLowerCase().contains("tali");
            case 17: //peta
                if (speech.length() > 0 && speech.charAt(0) == 'p') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'e') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 't') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }

                return speech.toLowerCase().contains("peta");
            case 18: //desa
                if (speech.length() > 0 && speech.charAt(0) == 'd') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'e') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 's') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }

                return speech.toLowerCase().contains("desa");
            case 19: //roda
                if (speech.length() > 0 && speech.charAt(0) == 'r') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'o') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'd') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }

                return speech.toLowerCase().contains("roda");
            default:
                return false;
        }
    }

    @SuppressLint("ResourceType")
    protected void setWordLevelling(int level){
        @DrawableRes int drawableId = 0;
        switch (level) {
            case 0: //bumi
                drawableId = R.drawable.ic_ibu;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_2, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 1: //ubi
                drawableId = R.drawable.ic_ubi;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_2, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 2: //api
                drawableId = R.drawable.ic_api;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_2, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 3: //bumi
                drawableId = R.drawable.ic_earth;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 4: //padi
                drawableId = R.drawable.ic_rice;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 5: //gigi
                drawableId = R.drawable.ic_tooth;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 6: //dadu
                drawableId = R.drawable.ic_dice;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 7: //biji
                drawableId = R.drawable.ic_seed;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 8: //guru
                drawableId = R.drawable.ic_teacher;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 9: //biji
                drawableId = R.drawable.ic_seed;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 10: //gula
                drawableId = R.drawable.ic_sugar;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 11: //pipi
                drawableId = R.drawable.ic_cheek;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 12: //kopi
                drawableId = R.drawable.ic_coffee;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 13: //duri
                drawableId = R.drawable.ic_thorn;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 14: //kayu
                drawableId = R.drawable.ic_wood;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_y);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 15: //rusa
                drawableId = R.drawable.ic_deer;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 16: //tali
                drawableId = R.drawable.ic_rope;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 17: //peta
                drawableId = R.drawable.ic_map;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 18: //desa
                drawableId = R.drawable.ic_village;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 19: //roda
                drawableId = R.drawable.ic_wheel;

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
        }

        if (find(R.id.img_tebak, ImageView.class) != null && drawableId > 0) {
            find(R.id.img_tebak, ImageView.class).setImageResource(drawableId);
        }
    }
}

