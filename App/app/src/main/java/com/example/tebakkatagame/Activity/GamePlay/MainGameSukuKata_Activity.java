package com.example.tebakkatagame.Activity.GamePlay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.nfc.Tag;
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
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.Activity.LevelTahap_Activity;
import com.example.tebakkatagame.Activity.Tahap_Activity;
import com.example.tebakkatagame.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static com.example.tebakkatagame.Activity.GamePlay.MainGameKataBergambar_Activity.getErrorText;
import static com.example.tebakkatagame.Utils.Constanst.WORD_1;
import static com.example.tebakkatagame.Utils.Constanst.WORD_2;
import static com.example.tebakkatagame.Utils.Constanst.WORD_3;

public class MainGameSukuKata_Activity extends BaseApp implements RecognitionListener, TextToSpeech.OnInitListener {

    private static final String TAG = "main";
    Locale localeIndonesia = new Locale("id", "ID");
    SpeechRecognizer mSpeechRecognizer;
    private KonfettiView konfettiView;
    private GifImageView gifView;
    TextToSpeech textToSpeech;

    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_suku_kata);
        level = getIntent().getIntExtra("LEVEL", 1);

        setComponent();
        setWordLevelling(level);
        //setImageLevel();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setComponent() {
        konfettiView = findViewById(R.id.viewKonfetti_sukukata);
        gifView = findViewById(R.id.gif_speak);
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        find(R.id.ly_next).setVisibility(View.GONE);
        mSpeechRecognizer.setRecognitionListener(this);
        textToSpeech = new TextToSpeech(this, this);
        textToSpeech.setLanguage(localeIndonesia);
        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "in-ID");

//        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, localeIndonesia);
//        //Jumlah waktu yang diperlukan setelah kita berhenti mendengar ucapan untuk menganggap input selesai 7 detik.
//        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 7000);
//        //Jumlah waktu yang diperlukan setelah kita berhenti mendengar ucapan untuk mempertimbangkan kemungkinan input selesai.
////        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 7000000);
//        //Panjang minimal sebuah ujaran.
//        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 7000);


        find(R.id.btn_speak).setOnClickListener(v -> {
            setTimerGif();
            mSpeechRecognizer.stopListening();
            mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
        });

        find(R.id.img_btn_back).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Tahap_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        //setOpeningStart();
    }

    private void setOpeningStart() {
        RelativeLayout openingContainer = findViewById(R.id.container_opening);
        find(R.id.view_blur).bringToFront();
        openingContainer.bringToFront();
        RelativeLayout.LayoutParams frameParams = new RelativeLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );

        frameParams.width = 300;
        frameParams.height = 300;
        frameParams.bottomMargin = 200;
//        frameParams.gravity = Gravity.CENTER;

        Runnable runnable = () -> {
            find(R.id.view_blur).setVisibility(View.VISIBLE);
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(frameParams);
            imageView.setImageResource(R.drawable.number_1);
            openingContainer.addView(imageView);
            imageView.postDelayed(() -> {
                imageView.setImageResource(R.drawable.number_2);
                imageView.postDelayed(() -> {
                    imageView.setImageResource(R.drawable.number_3);
                    imageView.postDelayed(() -> {
                        openingContainer.setVisibility(View.GONE);
                        find(R.id.view_blur).setVisibility(View.GONE);
                        burstEffect();
                    }, 1500);
                }, 1500);
            }, 1500);
        };

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            //TransitionManager.beginDelayedTransition();
            handler.postDelayed(runnable, 0);
        }, 0);
    }

    private void setTimerGif() {
        GifDrawable gifDrawable;
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

    private void burstEffect(){
        konfettiView.post(() -> konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5))
                .setPosition(konfettiView.getX() + konfettiView.getWidth() / 2, konfettiView.getY()  + konfettiView.getHeight() / 3)
                .burst(100));
    }


    private void setResultSpech(String... eja) {
        selebrateWin(setCorrectWord(level, eja[0]));
    }

    private void selebrateWin(boolean isBenar) {
        Handler handler = new Handler(Looper.getMainLooper());
        find(R.id.view_blur).setVisibility(View.VISIBLE);

        if(isBenar){
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
                showWinDialog(level + 1, "SUKU KATA", true);
            }, 3000);
        }else{
            showWinDialog(level + 1, "SUKU KATA", false);
        }

        handler.postDelayed(() -> {
            if (!isBenar) {
                find(R.id.img_next_level, ImageView.class).setImageDrawable(getDrawable(R.drawable.ic_repeat));
                find(R.id.tv_next, TextView.class).setText("Coba Lagi");
                find(R.id.ly_next).setOnClickListener(v -> setIntentFinish(MainGameSukuKata_Activity.class, "LEVEL", (level)));
            } else {
                find(R.id.img_next_level, ImageView.class).setImageDrawable(getDrawable(R.drawable.ic_next));
                find(R.id.tv_next, TextView.class).setText("Selanjutnya");
                find(R.id.ly_next).setOnClickListener(v -> setIntentFinish(MainGameSukuKata_Activity.class, "LEVEL", (level + 1)));
            }
            find(R.id.ly_next).setVisibility(View.VISIBLE);
        }, 4000);
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
        /*String errorMessage = getErrorText(error);
        Log.d("LOG_onError" , errorMessage);
        if(errorMessage.contains("RecognitionService busy")){
            mSpeechRecognizer.stopListening();
            showInfo("Server Sedang Sibuk");
        }else if(errorMessage.contains("No speech input")){
            mSpeechRecognizer.stopListening();
            showInfo("Coba Lagi");
        }else if(errorMessage.contains("No match")){
            mSpeechRecognizer.stopListening();
            showInfo("Coba Lagi");
        }*/
    }

    @Override
    public void onResults(Bundle results) {

        List<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String[] result = matches.toArray(new String[]{});
        setResultSpech(result);

        find(R.id.tv_result_sukukata, TextView.class).setText(matches.get(0).toUpperCase());//just dummy

    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }


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