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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.Activity.LevelTahap_Activity;
import com.example.tebakkatagame.Activity.Tahap_Activity;
import com.example.tebakkatagame.R;
import com.example.tebakkatagame.Utils.SharePrefUtils;

import java.util.Objects;

public class WinDialogFragment extends DialogFragment {

    private boolean isBenar = false;
    private int nextLevel = 0;
    private String jenisTahap = "";
    private BaseApp baseApp;

    public static WinDialogFragment newInstance(int level, String jenisTahap, boolean isBenar) {
        WinDialogFragment frag = new WinDialogFragment();
        Bundle args = new Bundle();
        args.putInt("LEVEL", level);
        args.putString("TAHAP", jenisTahap);
        args.putBoolean("WRONG", isBenar);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nextLevel = getArguments().getInt("LEVEL");
            jenisTahap = getArguments().getString("TAHAP");
            isBenar = getArguments().getBoolean("WRONG");
            if(isBenar){
                switch (jenisTahap) {
                    case "TEBAK GAMBAR":
                        baseApp = (MainGameKataBergambar_Activity) getActivity();
                        SharePrefUtils.saveLevel(getContext(), "GAMBAR", nextLevel + 1);
                        break;
                    case "SUKU KATA":
                        baseApp = (MainGameSukuKata_Activity) getActivity();
                        SharePrefUtils.saveLevel(getContext(), "KATA", nextLevel + 1);
                        break;
                    case "TEBAK HURUF":
                        baseApp = (MainGameTebakHuruf_Acitivity) getActivity();
                        SharePrefUtils.saveLevel(getContext(), "HURUF", nextLevel + 1);
                        break;
                    case "MEMBACA":
                        baseApp = (MainGameKalimat_Activity) getActivity();
                        SharePrefUtils.saveLevel(getContext(), "MEMBACA", nextLevel + 1);
                        break;
                }
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!isBenar){
            ((ImageView)view.findViewById(R.id.img_stiker)).setImageResource(R.drawable.ic_stiker_salah);
            ((ImageView)view.findViewById(R.id.img_btn_next)).setImageResource(R.drawable.ic_repeat_resize);
            ((ImageView)view.findViewById(R.id.img_btn_next)).setTag("WRONG");
            view.findViewById(R.id.img_btn_close).setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.tv_info)).setText("Kamu Hampir Benar, Ayo Periksa Lagi!");
        }else{
            ((ImageView)view.findViewById(R.id.img_btn_next)).setTag("RIGHT");
        }

        view.findViewById(R.id.img_btn_next).setOnClickListener(v -> {
            if(((ImageView)view.findViewById(R.id.img_btn_next)).getTag().toString().equals("WRONG")){
                nextLevel -= 1;
            }

            switch (jenisTahap) {
                case "TEBAK GAMBAR":
                    setIntent(MainGameKataBergambar_Activity.class, "LEVEL", (nextLevel));
                    break;
                case "SUKU KATA":
                    setIntent(MainGameSukuKata_Activity.class, "LEVEL", (nextLevel));
                    break;
                case "TEBAK HURUF":
                    setIntent(MainGameTebakHuruf_Acitivity.class, "LEVEL", (nextLevel));
                    break;
                case "MEMBACA":
                    setIntent(MainGameKalimat_Activity.class, "LEVEL", (nextLevel));
                    break;
            }
        });

        view.findViewById(R.id.img_btn_close).setOnClickListener(v -> {
            startActivity(new Intent(getContext(), Tahap_Activity.class));
            getActivity().finish();
        });
    }

    public void showInfo(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void setIntent(Class<?> to, String key, int value) {
        Intent intent;
        if (value == 20){
            intent = new Intent(getActivity(), Tahap_Activity.class);
        }else {
            intent = new Intent(getActivity(), to);
            intent.putExtra(key, value);
        }
        startActivity(intent);
        getActivity().finish();
        dismiss();
    }

    private void setGreyTint(View view) {
        if (view instanceof ImageView) {
            ((ImageView) view).setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey_600), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

}
