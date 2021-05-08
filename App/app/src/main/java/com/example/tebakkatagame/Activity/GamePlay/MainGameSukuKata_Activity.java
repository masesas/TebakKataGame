package com.example.tebakkatagame.Activity.GamePlay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.R;

import java.util.List;
import java.util.Locale;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static com.example.tebakkatagame.Activity.GamePlay.MainGameKataBergambar_Activity.getErrorText;
import static com.example.tebakkatagame.Utils.Constanst.WORD_1;
import static com.example.tebakkatagame.Utils.Constanst.WORD_2;
import static com.example.tebakkatagame.Utils.Constanst.WORD_3;

public class MainGameSukuKata_Activity extends BaseApp implements RecognitionListener {

    Locale localeIndonesia = new Locale("id", "ID");
    SpeechRecognizer mSpeechRecognizer;
    private KonfettiView konfettiView;
    private String word1,word3,word4;

    private int countSpeak = 0;
    private int level;
    private int countWrong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_suku_kata);
        setComponent();
        setImageLevel();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setComponent() {
        konfettiView = findViewById(R.id.viewKonfetti_sukukata);
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizer.setRecognitionListener(this);

        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, localeIndonesia);

        find(R.id.btn_start).setOnTouchListener((View v, MotionEvent event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    mSpeechRecognizer.stopListening();
                    find(R.id.tv_result_sukukata, TextView.class).setHint("You will see input here");
                    break;

                case MotionEvent.ACTION_DOWN:
                    mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                    find(R.id.tv_result_sukukata, TextView.class).setText("");
                    find(R.id.tv_result_sukukata, TextView.class).setHint("Listening...");
                    break;
            }
            return false;
        });
    }

    private void setResultSpech(String... eja) {
        countSpeak++;
        if (countSpeak == WORD_1) {
            if (setCorrectAnswer(eja[0])) {
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
        if(isBenar){
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
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                showWinDialog(level + 1, "SUKU KATA", true);
            }, 2000);
        }else{
            showWinDialog(level + 1, "SUKU KATA", false);
        }


    }

    private boolean setCorrectAnswer(String speech) {
        speech = speech.toLowerCase();
        switch (level) {
            case 0: //ibu
                if(speech.charAt(0) == 'i'){
                    setCorectMode(find(R.id.img_word_1));
                }else{
                    setWrongMode(find(R.id.img_word_1));
                }
                if(speech.charAt(1) == 'b'){
                    setCorectMode(find(R.id.img_word_3));
                }else{
                    setWrongMode(find(R.id.img_word_3));
                }
                if(speech.charAt(2) == 'u'){
                    setCorectMode(find(R.id.img_word_4));
                }else{
                    setWrongMode(find(R.id.img_word_4));
                }
                return speech.toLowerCase().contains("ibu");
            default:
                return false;
        }
    }

    private void setImageLevel() {
        level = getIntent().getIntExtra("LEVEL", 1);
        switch (level) {
            case 0: //ibu
                find(R.id.img_word_1, ImageView.class).setImageResource(R.drawable.letter_i);
                find(R.id.img_word_2, ImageView.class).setVisibility(View.GONE);
                find(R.id.img_word_3, ImageView.class).setImageResource(R.drawable.letter_b);
                find(R.id.img_word_4, ImageView.class).setImageResource(R.drawable.letter_u);
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

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {
        showInfo(getErrorText(error));
    }

    @Override
    public void onResults(Bundle results) {
        List<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String[] result = matches.toArray(new String[]{});
        setResultSpech(result);
        find(R.id.tv_result_sukukata, TextView.class).setText(matches.get(0));//just dummy
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}