package com.example.tebakkatagame.Activity.GamePlay;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;

import com.example.tebakkatagame.R;

import java.util.Objects;

public class HurufDialogFragment extends DialogFragment {

    String alphabet;

    public static HurufDialogFragment newInstance(String nextAlpha) {
        HurufDialogFragment frag = new HurufDialogFragment();
        Bundle args = new Bundle();
        args.putString("next", nextAlpha);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            alphabet = getArguments().getString("next");
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(dialog, "alpha", 3f, .5f);
        fadeOut.setDuration(2000);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(dialog, "alpha", .6f, 4f);
        fadeIn.setDuration(2000);

        @SuppressLint("Recycle") final AnimatorSet mAnimationSet = new AnimatorSet();
        mAnimationSet.play(fadeIn).after(fadeOut);

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(getDialog()).getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        return inflater.inflate(R.layout.dialog_huruf, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = Objects.requireNonNull(getDialog()).getWindow();
        if (window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.y = -100;
        params.width = 1000;
        params.height = 1200;
        window.setAttributes(params);
    }


}
