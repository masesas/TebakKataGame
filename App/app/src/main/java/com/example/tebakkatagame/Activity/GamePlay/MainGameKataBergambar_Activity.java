package com.example.tebakkatagame.Activity.GamePlay;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.Activity.LevelTahap_Activity;
import com.example.tebakkatagame.R;
import com.example.tebakkatagame.Utils.STT;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import static com.example.tebakkatagame.Utils.Constanst.WORD_1;
import static com.example.tebakkatagame.Utils.Constanst.WORD_2;
import static com.example.tebakkatagame.Utils.Constanst.WORD_3;
import static com.example.tebakkatagame.Utils.Constanst.WORD_4;

public class MainGameKataBergambar_Activity extends BaseApp implements RecognitionListener, TextToSpeech.OnInitListener {

    Locale localeIndonesia = new Locale("id", "ID");
    SpeechRecognizer mSpeechRecognizer;
    private KonfettiView konfettiView;
    private GifImageView gifView;
    TextToSpeech textToSpeech;
    private int countSpeak = 0;
    private int level;
    private int countWrong = 0;

    private String word1, word2, word3, word4, word5, word6;

    STT stt;

    @SuppressLint({"QueryPermissionsNeeded", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_kata_bergambar);
        setComponent();
        setImageLevel();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setComponent() {
        konfettiView = findViewById(R.id.viewKonfetti);
        gifView = findViewById(R.id.gif_speak);
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        //stt = new STT(this);
        textToSpeech = new TextToSpeech(this, this);

        textToSpeech.setLanguage(localeIndonesia);
        mSpeechRecognizer.setRecognitionListener(this);
        //find(R.id.img_tebak).setVisibility(View.GONE);

        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "in-ID");

        find(R.id.ly_eja_1).setOnClickListener(v -> playWord(1));
        find(R.id.ly_eja_2).setOnClickListener(v -> playWord(2));
        find(R.id.btn_speak).setOnClickListener(v -> {
            setTimerGif();
            mSpeechRecognizer.stopListening();
            mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
            //setGoogleSpeechToText();
            // stt.speak();
        });
        find(R.id.img_btn_back).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LevelTahap_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

//        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
//            @Override
//            public void onReadyForSpeech(Bundle params) {
//
//            }
//
//            @Override
//            public void onBeginningOfSpeech() {
//                showInfo("Mulai");
//            }
//
//            @Override
//            public void onRmsChanged(float rmsdB) {
//
//            }
//
//            @Override
//            public void onBufferReceived(byte[] buffer) {
//
//            }
//
//            @Override
//            public void onEndOfSpeech() {
//                showInfo("Selesai");
//            }
//
//            @Override
//            public void onError(int error) {
//                showInfo("Error " + getErrorText(error));
//            }
//
//            @Override
//            public void onResults(Bundle results) {
//                ArrayList<String> matchess = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//                List<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//                if (matches != null) {
//                    String[] result = matches.toArray(new String[]{});
//                    setResultSpech(result);
//                }
//
//                //find(R.id.tv_output_suara, TextView.class).setText(matches.get(0));//just dummy
//            }
//
//            @Override
//            public void onPartialResults(Bundle partialResults) {
//
//            }
//
//            @Override
//            public void onEvent(int eventType, Bundle params) {
//
//            }
//        });

    }

    private void setTimerGif() {
        GifDrawable gifDrawable = null;
        try {
            gifDrawable = new GifDrawable(getResources().openRawResource(R.raw.btn_speak_5s));
            gifView.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        find(R.id.btn_speak).setVisibility(View.GONE);
        gifView.setVisibility(View.VISIBLE);
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //implements count timer if need
            }

            @Override
            public void onFinish() {
                mSpeechRecognizer.stopListening();
                gifView.setVisibility(View.GONE);
                find(R.id.btn_speak).setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void setGoogleSpeechToText() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, localeIndonesia);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Ucapkan Kata Sesuai Gambar");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            showInfo("Perangkat tidak di dukung");
        }
    }

    private void playWord(int sukuKata) {
        MediaPlayer mediaPlayer = null;
        // AudioTrack audioTrack = new AudioTrack(3, 16000, 2, 2, 3);
        switch (level) {
            case 0: //bumi
                if (sukuKata == 1) {
                    //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.bu);
                    textToSpeech.speak("BU",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.mi);
                     textToSpeech.speak("MI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                break;
            case 1: //padi
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.pa);
                    // textToSpeech.speak("PA",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.di);
                    //textToSpeech.speak("DI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                // mediaPlayer = MediaPlayer.create(getActivity(), R.raw.padi);
                break;
            case 2: //gigi
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.gi);
                // textToSpeech.speak("GI",TextToSpeech.QUEUE_FLUSH,null,null);
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.gigi);
                break;
            case 3: //dadu
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.da);
                    // textToSpeech.speak("DA",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.du);
                    //textToSpeech.speak("DU",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.dadu);
                break;
            case 4: //biji
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.bi);
                    //textToSpeech.speak("BI",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ji);
                    //textToSpeech.speak("JI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.biji);
                break;
            case 5: //gula
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.gu);
                    //textToSpeech.speak("GU",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.la);
                    // textToSpeech.speak("LA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.gula);
                break;
            case 6: //pipi
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.pi);
                // textToSpeech.speak("PI",TextToSpeech.QUEUE_FLUSH,null,null);
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.pipi);
                break;
            case 7: //kopi
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ko);
                    //textToSpeech.speak("KO",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.pi);
                    //textToSpeech.speak("PI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.kopi);
                break;
            case 8: //duri
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.du);
                    // textToSpeech.speak("DU",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ri);
                    //textToSpeech.speak("RI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //  mediaPlayer = MediaPlayer.create(getActivity(), R.raw.duri);
                break;
            case 9: //kayu
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ka);
                    //textToSpeech.speak("KA",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.yu);
                    //textToSpeech.speak("YU",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.kayu);
                break;
            case 10: //rusa
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ru);
                    // textToSpeech.speak("RU",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sa);
                    //textToSpeech.speak("SA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                // mediaPlayer = MediaPlayer.create(getActivity(), R.raw.rusa);
                break;
            case 11: //tali
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ta);
                    // textToSpeech.speak("TA",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.li);
                    //textToSpeech.speak("LI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //   mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tali);
                break;
            case 12: //peta
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.pe);
                    //textToSpeech.speak("PE",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ta);
                    //textToSpeech.speak("TA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                // mediaPlayer = MediaPlayer.create(getActivity(), R.raw.peta);
                break;
            case 13: //desa
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.de);
                    // textToSpeech.speak("DE",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sa);
                    //textToSpeech.speak("SA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.desa);
                break;
            case 14: //roda
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ro);
//                    textToSpeech.speak("RO",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.da);
//                    textToSpeech.speak("DA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.roda);
                break;
            case 15: //buaya
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.bua);
//                    textToSpeech.speak("BUA",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ya);
                    //textToSpeech.speak("YA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                // mediaPlayer = MediaPlayer.create(getActivity(), R.raw.buaya);
                break;
            case 16: //rumah
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ru);
                    // textToSpeech.speak("RU",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.mah);
                    // textToSpeech.speak("MAH",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //  mediaPlayer = MediaPlayer.create(getActivity(), R.raw.rumah);
                break;
            case 17: //delima
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.de);
                    // textToSpeech.speak("DE",TextToSpeech.QUEUE_FLUSH,null,null);
                } else if (sukuKata == 2) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.li);
                    //  textToSpeech.speak("LI",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ma);
                    // textToSpeech.speak("MA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                // mediaPlayer = MediaPlayer.create(getActivity(), R.raw.delima);
                break;
            case 18: //keledai
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ke);
                    //textToSpeech.speak("KE",TextToSpeech.QUEUE_FLUSH,null,null);
                } else if (sukuKata == 2) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.le);
                    //textToSpeech.speak("LE",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.dai);
                    //textToSpeech.speak("DAI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.keledai);
                break;
            case 19: //kebaya
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ke);
                    //textToSpeech.speak("KE",TextToSpeech.QUEUE_FLUSH,null,null);
                } else if (sukuKata == 2) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ba);
                    //textToSpeech.speak("BA",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ya);
                    //textToSpeech.speak("YA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //  mediaPlayer = MediaPlayer.create(getActivity(), R.raw.kebaya);
                break;
            default:
                break;
        }

        if (mediaPlayer != null) {
            mediaPlayer.setVolume(100, 100);
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        }

    }

    //by ejakata
    private void setResultSpech(String... eja) {
        countSpeak++;
        if (countSpeak == WORD_1) {
            if (setCorrectAnswer(eja[0])) {
                shakesAnimate(find(R.id.img_tebak));
                find(R.id.img_tebak).setVisibility(View.VISIBLE);
                selebrateWin(true);
            } else {
                countWrong++;
                countSpeak = 0;
                selebrateWin(false);
            }
        }
    }

    private void selebrateWin(boolean isBenar) {
        find(R.id.view_blur).setVisibility(View.VISIBLE);
        if (isBenar) {
            find(R.id.ly_next).setOnClickListener(v -> setIntentFinish(MainGameKataBergambar_Activity.class, "LEVEL", (level + 1)));

            MediaPlayer mediaPlayerWin = MediaPlayer.create(getActivity(), R.raw.sound_applause);
            mediaPlayerWin.start();
            konfettiView.post(() -> konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                    .addSizes(new Size(12, 5f))
                    .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                    .streamFor(300, 5000L));

            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(() -> {
                showWinDialog(level + 1, "TEBAK GAMBAR", true);
                mediaPlayerWin.stop();
            }, 3000);

            handler.postDelayed(() -> {
                find(R.id.ly_next).setVisibility(View.VISIBLE);
            }, 6000);

        } else {
            showWinDialog(level + 1, "TEBAK GAMBAR", false);
        }
    }

    private boolean setCorrectAnswer(String speech) {
        speech = speech.toLowerCase();
        switch (level) {
            case 0: //bumi
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
            case 1: //padi
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
            case 2: //gigi
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
            case 3: //dadu
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
            case 4: //biji
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
            case 5: //gula
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
            case 6: //pipi
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
            case 7: //kopi
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
            case 8: //duri
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
            case 9: //kayu
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
            case 10: //rusa
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
            case 11: //tali
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
            case 12: //peta
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
            case 13: //desa
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
            case 14: //roda
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
            case 15: //buaya
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
                if (speech.length() > 2 && speech.charAt(2) == 'a') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'y') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.length() > 4 && speech.charAt(4) == 'a') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                return speech.toLowerCase().contains("buaya");
            case 16: //rumah
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
                if (speech.length() > 2 && speech.charAt(2) == 'm') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.length() > 4 && speech.charAt(4) == 'h') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                return speech.toLowerCase().contains("rumah");
            case 17: //delima
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
                if (speech.length() > 4 && speech.charAt(4) == 'm') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                if (speech.length() > 5 && speech.charAt(5) == 'a') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                return speech.toLowerCase().contains("delima");
            case 18: //keledai
                if (speech.length() > 0 && speech.charAt(0) == 'k') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'e') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'l') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'e') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.length() > 4 && speech.charAt(4) == 'd') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                if (speech.length() > 5 && speech.charAt(5) == 'a') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                if (speech.length() > 6 && speech.charAt(6) == 'i') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }
                return speech.toLowerCase().contains("keledai");
            case 19: //kebaya
                if (speech.length() > 0 && speech.charAt(0) == 'k') {
                    setCorectMode(find(R.id.img_word_1));
                } else {
                    setWrongMode(find(R.id.img_word_1));
                }
                if (speech.length() > 1 && speech.charAt(1) == 'e') {
                    setCorectMode(find(R.id.img_word_2));
                } else {
                    setWrongMode(find(R.id.img_word_2));
                }
                if (speech.length() > 2 && speech.charAt(2) == 'b') {
                    setCorectMode(find(R.id.img_word_3));
                } else {
                    setWrongMode(find(R.id.img_word_3));
                }
                if (speech.length() > 3 && speech.charAt(3) == 'a') {
                    setCorectMode(find(R.id.img_word_4));
                } else {
                    setWrongMode(find(R.id.img_word_4));
                }
                if (speech.length() > 4 && speech.charAt(4) == 'y') {
                    setCorectMode(find(R.id.img_word_5));
                } else {
                    setWrongMode(find(R.id.img_word_5));
                }
                if (speech.length() > 5 && speech.charAt(5) == 'a') {
                    setCorectMode(find(R.id.img_word_6));
                } else {
                    setWrongMode(find(R.id.img_word_6));
                }

                return speech.toLowerCase().contains("kebaya");
            default:
                return false;
        }
    }

    private void setImageLevel() {
        level = getIntent().getIntExtra("LEVEL", 1);
        switch (level) {
            case 0: //bumi
                word1 = getResources().getResourceEntryName(R.drawable.letter_b);
                word2 = getResources().getResourceEntryName(R.drawable.letter_u);
                word3 = getResources().getResourceEntryName(R.drawable.letter_m);
                word4 = getResources().getResourceEntryName(R.drawable.letter_i);

                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_earth);
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);

                break;
            case 1: //padi
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_rice);
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 2: //gigi
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_tooth);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 3: //dadu
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_dice);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 4: //biji
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_seed);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 5: //gula
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_sugar);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 6: //pipi
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_cheek);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 7: //kopi
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_coffee);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 8: //duri
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_thorn);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 9: //kayu
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_wood);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_y);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 10: //rusa
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_deer);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 11: //tali
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_rope);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 12: //peta
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_map);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 13: //desa
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_village);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 14: //roda
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_wheel);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 15: //buaya
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_crocodile);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_y);
                find(R.id.img_word_5, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 16: //rumah
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_house);
                find(R.id.img_limit_3, ImageView.class).setVisibility(View.VISIBLE);
                find(R.id.img_word_7, ImageView.class).setVisibility(View.VISIBLE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_h);

                break;
            case 17: //delima
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_pomegranate);
                find(R.id.ly_eja_3).setVisibility(View.VISIBLE);
                find(R.id.img_limit_2, ImageView.class).setVisibility(View.VISIBLE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_5, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 18: //keledai
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_donkey);
                find(R.id.ly_eja_3).setVisibility(View.VISIBLE);
                find(R.id.img_limit_2, ImageView.class).setVisibility(View.VISIBLE);
                find(R.id.img_limit_3, ImageView.class).setVisibility(View.VISIBLE);
                find(R.id.img_word_7, ImageView.class).setVisibility(View.VISIBLE);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_5, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_7, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 19: //kebaya
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_kebaya);
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_5, ImageView.class).setImageResource(R.drawable.letter_y);
                find(R.id.img_word_6, ImageView.class).setImageResource(R.drawable.letter_a);
                break;

        }
    }

    private void setCorectMode(View view) {
        if (view instanceof ImageView) {
            ((ImageView) view).setColorFilter(ContextCompat.getColor(getActivity(), R.color.green_500), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }

    private void setWrongMode(View view) {
        if (view instanceof ImageView) {
            ((ImageView) view).setColorFilter(ContextCompat.getColor(getActivity(), R.color.red_500), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }
    @Override
    public void onReadyForSpeech(Bundle params) {
    }

    @Override
    public void onBeginningOfSpeech() {
    }

    @Override
    public void onRmsChanged(float rmsdB) {
//        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
//        progressBar.setProgress((int) rmsdB);

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {
//        showInfo(getErrorText(error));
    }

    @Override
    public void onResults(Bundle results) {
        List<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String[] result = matches.toArray(new String[]{});
        setResultSpech(result);
        //find(R.id.tv_output_suara, TextView.class).setText(matches.get(0));//just dummy
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        stt.toPerformInOnActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 10) {
//            if (resultCode == RESULT_OK && data != null) {
//                List<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                if (matches != null) {
//                    String[] result = matches.toArray(new String[]{});
//                    setResultSpech(result);
//                } else {
//                    showInfo("Ucapkan Kata");
//                }
//            }
//        }
//    }

    @Override
    public void onInit(int status) {
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {

            }

            @Override
            public void onDone(String utteranceId) {

            }

            @Override
            public void onError(String utteranceId) {

            }

        });
    }
}

