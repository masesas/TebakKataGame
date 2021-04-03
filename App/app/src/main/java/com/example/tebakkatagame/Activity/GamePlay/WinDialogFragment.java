package com.example.tebakkatagame.Activity.GamePlay;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.R;

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
            switch (jenisTahap){
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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_win, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.img_btn_next).setOnClickListener(v -> {
            switch (jenisTahap){
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

    public void setIntent(Class<?> to, String key, int value){
        Intent intent = new Intent(getActivity(), to);
        intent.putExtra(key, value);
        startActivity(intent);
        baseApp.finish();
        dismiss();
    }

}
