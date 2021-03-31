package com.example.tebakkatagame.Activity.GamePlay;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tebakkatagame.R;

public class WinDialogFragment extends DialogFragment {

    public static WinDialogFragment newInstance(int level) {
        WinDialogFragment frag = new WinDialogFragment();
        Bundle args = new Bundle();
        args.putInt("LEVEL", level);
        frag.setArguments(args);
        return frag;
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

        });

        view.findViewById(R.id.img_btn_close).setOnClickListener(v -> {

        });

        view.findViewById(R.id.img_btn_home).setOnClickListener(v -> {

        });
    }
}
