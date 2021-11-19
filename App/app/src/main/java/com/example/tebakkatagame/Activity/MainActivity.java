package com.example.tebakkatagame.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.tebakkatagame.Activity.GamePlay.MainGameKataBergambar_Activity;
import com.example.tebakkatagame.Activity.GamePlay.MainGameTebakHuruf_Acitivity;
import com.example.tebakkatagame.Activity.GamePlay.TutorActivity;
import com.example.tebakkatagame.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseApp {

    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAndRequestPermissions();
        if(!isNetworkAvailable()){
            showConnectionDialog();
        }

        wafeAnimate(find(R.id.img_tittle));
        rollAnimate(find(R.id.img_charcters));

        find(R.id.btn_mulai).setOnClickListener(v -> {
            clickSound();
            if(!isNetworkAvailable()){
                showConnectionDialog();
            }else{
                setIntent(Tahap_Activity.class, "", "");
            }
        });

        find(R.id.btn_tutor).setOnClickListener(v -> setIntent(TutorActivity.class, "", ""));
    }

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        this.doubleBackToExitPressedOnce = false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        showInfo("Tekan sekali lagi untuk keluar");
        mHandler.postDelayed(mRunnable, 2000);
    }

    private void showConnectionDialog(){
        new AlertDialog.Builder(getActivity())
                .setMessage("Di perlukan Koneksi Internet Untuk Memulai Permainan :(")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


    private void checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            int permissionVoice = ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION);
            int permissionVoiceRecord = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (permissionVoice != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.ACTIVITY_RECOGNITION);
            }
            if (permissionVoiceRecord != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.RECORD_AUDIO);
            }

            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        }
    }

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {
            Map<String, Integer> perms = new HashMap<>();
            perms.put(Manifest.permission.ACTIVITY_RECOGNITION, PackageManager.PERMISSION_GRANTED);
            perms.put(Manifest.permission.RECORD_AUDIO, PackageManager.PERMISSION_GRANTED);

            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                if (perms.get(Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED
                        || perms.get(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACTIVITY_RECOGNITION) ||
                            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                        showDialogOK("Izin Rekam Suara dan Aktivitas Suara di Butuhkan untuk Aplikasi Ini",
                                (dialog, which) -> {
                                    switch (which) {
                                        case DialogInterface.BUTTON_POSITIVE:
                                            checkAndRequestPermissions();
                                            break;
                                        case DialogInterface.BUTTON_NEGATIVE:
                                            break;
                                    }
                                });
                    } else {
                        Toast.makeText(getActivity(), "Pergi Ke Pengaturan untuk Mengizinkan Aplikasi di Akses",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }


}