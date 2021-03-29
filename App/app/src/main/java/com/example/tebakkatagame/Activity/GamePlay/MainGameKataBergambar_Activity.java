package com.example.tebakkatagame.Activity.GamePlay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.R;
import com.example.tebakkatagame.Utils.Constanst;

import net.gotev.speech.GoogleVoiceTypingDisabledException;
import net.gotev.speech.Logger;
import net.gotev.speech.Speech;
import net.gotev.speech.SpeechDelegate;
import net.gotev.speech.SpeechRecognitionNotAvailable;
import net.gotev.speech.SpeechUtil;
import net.gotev.speech.ui.SpeechProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.tebakkatagame.Utils.Constanst.PERMISSIONS_REQUEST;
import static com.example.tebakkatagame.Utils.Constanst.WORD_1;
import static com.example.tebakkatagame.Utils.Constanst.WORD_2;

public class MainGameKataBergambar_Activity extends BaseApp implements SpeechDelegate {

    private SpeechProgressView speakProgress;
    private int countSpeak = 0;
    Locale localeIndonesia = new Locale("id", "ID");

    private TextToSpeech.OnInitListener mTttsInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(final int status) {
            switch (status) {
                case TextToSpeech.SUCCESS:

                    break;

                case TextToSpeech.ERROR:

                    break;

                default:

                    break;
            }
        }
    };


    @SuppressLint({"QueryPermissionsNeeded", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_kata_bergambar);
        checkPermission();
        Speech.init(this, getPackageName(), mTttsInitListener);
        setComponent();
    }

    private void setComponent() {
        speakProgress = findViewById(R.id.speak_progress_view);
        setColorProgress();
        speakProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startListening();
            }
        });
    }

    private void setColorProgress() {
        int[] colors = {
                ContextCompat.getColor(this, android.R.color.black),
                ContextCompat.getColor(this, android.R.color.darker_gray),
                ContextCompat.getColor(this, android.R.color.black),
                ContextCompat.getColor(this, android.R.color.holo_orange_dark),
                ContextCompat.getColor(this, android.R.color.holo_red_dark)
        };

        speakProgress.setColors(colors);
    }

    private void onRecordAudioPermissionGranted() {
        try {
            Speech.getInstance().stopTextToSpeech();
            Speech.getInstance().setLocale(localeIndonesia);
            Speech.getInstance().startListening(speakProgress, this);

        } catch (SpeechRecognitionNotAvailable exc) {
            showSpeechNotSupportedDialog();

        } catch (GoogleVoiceTypingDisabledException exc) {
            showEnableGoogleVoiceTyping();
        }
    }

    private void showSpeechNotSupportedDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        SpeechUtil.redirectUserToGoogleAppOnPlayStore(getActivity());
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Handphone Anda Tidak Support!")
                .setCancelable(false)
                .setPositiveButton("Ya", dialogClickListener)
                .setNegativeButton("Tidak", dialogClickListener)
                .show();
    }

    private void showEnableGoogleVoiceTyping() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Pakai Google Speak?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                    }
                })
                .show();
    }

    private void startListening() {
        if (Speech.getInstance().isListening()) {
            Speech.getInstance().stopListening();
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                onRecordAudioPermissionGranted();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST);
            }
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != PERMISSIONS_REQUEST) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } else {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay!
                onRecordAudioPermissionGranted();
            } else {
                // permission denied, boo!
                Toast.makeText(MainGameKataBergambar_Activity.this, "Aplikasi Memerlukan Izin Akses Suara", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onStartOfSpeech() {
        //implemented animation (must?)
    }

    @Override
    public void onSpeechRmsChanged(float value) {

    }

    @Override
    public void onSpeechPartialResults(List<String> results) {
        Log.e("speak__", "onSpeechPartialResults: " + results );
    }

    @Override
    public void onSpeechResult(String result) {
        find(R.id.tv_results, TextView.class).setText(result);//testing
        countSpeak++;
        if (countSpeak == WORD_1) {
            if (result.toLowerCase().contains("p")) {
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_1, ImageView.class).setColorFilter(ContextCompat.getColor(getActivity(), R.color.green_500), android.graphics.PorterDuff.Mode.MULTIPLY);
                bounceAnimate(find(R.id.img_word_1, ImageView.class));
            } else {
                countSpeak = 0;
                shakesAnimate(find(R.id.img_word_1));
            }
        }else if(countSpeak == WORD_2){
            if (result.toLowerCase().contains("i")) {
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_2, ImageView.class).setColorFilter(ContextCompat.getColor(getActivity(), R.color.green_500), android.graphics.PorterDuff.Mode.MULTIPLY);
                bounceAnimate(find(R.id.img_word_2, ImageView.class));
            } else {
                countSpeak = 1;
                shakesAnimate(find(R.id.img_word_2));
            }
        }
    }
}