package com.example.tebakkatagame.Activity.GamePlay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.example.tebakkatagame.Activity.Tahap_Activity;
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

    private static final int PERMISSIONS_REQUEST = 999;
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
        level = getIntent().getIntExtra("LEVEL", 1);

        setComponent();
        setWordLevelling(level);
        //setImageLevel();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setComponent() {
        konfettiView = findViewById(R.id.viewKonfetti);
        gifView = findViewById(R.id.gif_speak);
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        //stt = new STT(this);
        textToSpeech = new TextToSpeech(this, this);

        find(R.id.ly_next).setVisibility(View.GONE);
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
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                setTimerGif();
                mSpeechRecognizer.stopListening();
                mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST);
            }
        });

        find(R.id.img_btn_back).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Tahap_Activity.class);
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
            case 0: //ibu
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.i);
                    //textToSpeech.speak("BU",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.bu);
                    // textToSpeech.speak("MI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                break;
            case 1: //ubi
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.u);
                    // textToSpeech.speak("PA",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.bi);
                    //textToSpeech.speak("DI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                // mediaPlayer = MediaPlayer.create(getActivity(), R.raw.padi);
                break;
            case 2: //api
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.a);
                    // textToSpeech.speak("PA",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.pi);
                    //textToSpeech.speak("DI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                // textToSpeech.speak("GI",TextToSpeech.QUEUE_FLUSH,null,null);
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.gigi);
                break;
            case 3: //bumi
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.bu);
                    // textToSpeech.speak("DA",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.mi);
                    //textToSpeech.speak("DU",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.dadu);
                break;
            case 4: //padi
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.pa);
                    //textToSpeech.speak("BI",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.di);
                    //textToSpeech.speak("JI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.biji);
                break;
            case 5: //gigi
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.gi);
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.gula);
                break;
            case 6: //dadu
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.da);
                    //textToSpeech.speak("BI",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.du);
                    //textToSpeech.speak("JI",TextToSpeech.QUEUE_FLUSH,null,null);
                }

                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.pipi);
                break;
            case 7: //biji
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.bi);
                    //textToSpeech.speak("KO",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ji);
                    //textToSpeech.speak("PI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.kopi);
                break;
            case 8: //guru
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.gu);
                    // textToSpeech.speak("DU",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ru);
                    //textToSpeech.speak("RI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //  mediaPlayer = MediaPlayer.create(getActivity(), R.raw.duri);
                break;
            case 9: //biji
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.bi);
                    //textToSpeech.speak("KA",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ji);
                    //textToSpeech.speak("YU",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.kayu);
                break;
            case 10: //gula
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.gu);
                    // textToSpeech.speak("RU",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.la);
                    //textToSpeech.speak("SA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                // mediaPlayer = MediaPlayer.create(getActivity(), R.raw.rusa);
                break;
            case 11: //pipi
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.pi);
                //   mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tali);
                break;
            case 12: //kopi
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ko);
                    //textToSpeech.speak("PE",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.pi);
                    //textToSpeech.speak("TA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                // mediaPlayer = MediaPlayer.create(getActivity(), R.raw.peta);
                break;
            case 13: //duri
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.du);
                    // textToSpeech.speak("DE",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ri);
                    //textToSpeech.speak("SA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.desa);
                break;
            case 14: //kayu
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ka);
//                    textToSpeech.speak("RO",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.yu);
//                    textToSpeech.speak("DA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.roda);
                break;
            case 15: //rusa
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ru);
//                    textToSpeech.speak("BUA",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sa);
                    //textToSpeech.speak("YA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                // mediaPlayer = MediaPlayer.create(getActivity(), R.raw.buaya);
                break;
            case 16: //tali
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ta);
                    // textToSpeech.speak("RU",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.li);
                    // textToSpeech.speak("MAH",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //  mediaPlayer = MediaPlayer.create(getActivity(), R.raw.rumah);
                break;
            case 17: //peta
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.pe);
                    // textToSpeech.speak("DE",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ta);
                    // textToSpeech.speak("MA",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                // mediaPlayer = MediaPlayer.create(getActivity(), R.raw.delima);
                break;
            case 18: //desa
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.de);
                    //textToSpeech.speak("KE",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sa);
                    //textToSpeech.speak("DAI",TextToSpeech.QUEUE_FLUSH,null,null);
                }
                //mediaPlayer = MediaPlayer.create(getActivity(), R.raw.keledai);
                break;
            case 19: //roda
                if (sukuKata == 1) {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.ro);
                    //textToSpeech.speak("KE",TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.da);
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
            if (setCorrectWord(level, eja[0])) {
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private void selebrateWin(boolean isBenar) {
        Handler handler = new Handler(Looper.getMainLooper());
        find(R.id.view_blur).setVisibility(View.VISIBLE);

        if (isBenar) {
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


            handler.postDelayed(() -> {
                showWinDialog(level + 1, "TEBAK GAMBAR", true);
                mediaPlayerWin.stop();
            }, 3000);
        } else {
            showWinDialog(level + 1, "TEBAK GAMBAR", false);
        }

        handler.postDelayed(() -> {
            if (!isBenar) {
                find(R.id.img_next_level, ImageView.class).setImageDrawable(getDrawable(R.drawable.ic_repeat));
                find(R.id.tv_next, TextView.class).setText("Coba Lagi");
                find(R.id.ly_next).setOnClickListener(v -> setIntentFinish(MainGameKataBergambar_Activity.class, "LEVEL", (level)));
            } else {
                find(R.id.img_next_level, ImageView.class).setImageDrawable(getDrawable(R.drawable.ic_next));
                find(R.id.tv_next, TextView.class).setText("Selanjutnya");
                find(R.id.ly_next).setOnClickListener(v -> setIntentFinish(MainGameKataBergambar_Activity.class, "LEVEL", (level + 1)));
            }

            find(R.id.ly_next).setVisibility(View.VISIBLE);
        }, 4000);
    }

    private void setImageLevel() {
        switch (level) {
            case 0: //bumi
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_ibu);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_2, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 1: //ubi
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_ubi);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_2, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 2: //api
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_api);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_2, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 3: //bumi
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_earth);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_m);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 4: //padi
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_rice);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 5: //gigi
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_tooth);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 6: //dadu
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_dice);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 7: //biji
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_seed);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 8: //guru
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_guru);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 9: //biji
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_seed);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_j);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 10: //gula
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_sugar);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_g);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 11: //pipi
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_cheek);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 12: //kopi
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_coffee);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 13: //duri
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_thorn);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 14: //kayu
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_wood);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_k);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_y);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
                break;
            case 15: //rusa
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_deer);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_u);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 16: //tali
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_rope);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_a);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_l);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_i);
                break;
            case 17: //peta
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_map);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_p);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_t);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 18: //desa
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_village);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_e);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_s);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;
            case 19: //roda
                find(R.id.img_tebak, ImageView.class).setImageResource(R.drawable.ic_wheel);

                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_r);
                find(R.id.img_word_2, ImageView.class).setImageResource(R.drawable.letter_o);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_d);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_a);
                break;

        }
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        showInfo("Mulai Ucapkan Kata");
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
        // showInfo(getErrorText(error));
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            showInfo("Ijinkan Aplikasi Untuk Mengakses Mikropon");
        }
    }
}

