package com.example.tebakkatagame.Activity.GamePlay;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
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
import androidx.fragment.app.DialogFragment;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.R;

import java.util.Objects;

public class WinDialogFragment extends DialogFragment {

    private int nextLevel = 0;
    private String jenisTahap = "";
    private BaseApp baseApp;

    public static WinDialogFragment newInstance(int level, String jenisTahap) {
        WinDialogFragment frag = new WinDialogFragment();
        Bundle args = new Bundle();
        args.putInt("LEVEL", level);
        args.putString("TAHAP", jenisTahap);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nextLevel = getArguments().getInt("LEVEL");
            jenisTahap = getArguments().getString("TAHAP");
            switch (jenisTahap) {
                case "TEBAK GAMBAR":
                    baseApp = (MainGameKataBergambar_Activity) getActivity();
                    break;
                case "SUKU KATA":
                    baseApp = (MainGameSukuKata_Activity) getActivity();
                    break;
                case "TEBAK HURUF":
                    baseApp = (MainGameTebakHuruf_Acitivity) getActivity();
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = Objects.requireNonNull(getDialog()).getWindow();
        if (window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = 1000;
        params.height = 1000;
        window.setAttributes(params);
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

        final AnimatorSet mAnimationSet = new AnimatorSet();
        mAnimationSet.play(fadeIn).after(fadeOut);

        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(getDialog()).getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);
        return inflater.inflate(R.layout.dialog_fragment_win, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.img_btn_next).setOnClickListener(v -> {
            switch (jenisTahap) {
                case "TEBAK GAMBAR":
                    setIntent(MainGameKataBergambar_Activity.class, "LEVEL", nextLevel);
                    break;
                case "SUKU KATA":
                    setIntent(MainGameSukuKata_Activity.class, "LEVEL", nextLevel);
                    break;
                case "TEBAK HURUF":
                    setIntent(MainGameTebakHuruf_Acitivity.class, "LEVEL", nextLevel);
                    break;
            }
        });

        view.findViewById(R.id.img_btn_close).setOnClickListener(v -> {

        });

        view.findViewById(R.id.img_btn_home).setOnClickListener(v -> {

        });
    }

    public void setIntent(Class<?> to, String key, int value) {
        Intent intent = new Intent(getActivity(), to);
        intent.putExtra(key, value);
        startActivity(intent);
        baseApp.finish();
        dismiss();
    }

}
