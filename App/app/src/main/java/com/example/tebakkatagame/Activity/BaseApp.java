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

}

