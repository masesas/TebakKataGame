package com.example.tebakkatagame.Activity.GamePlay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

import static com.example.tebakkatagame.Activity.GamePlay.MainGameKataBergambar_Activity.getErrorText;
import static com.example.tebakkatagame.Utils.Constanst.WORD_1;
import static com.example.tebakkatagame.Utils.Constanst.WORD_2;
import static com.example.tebakkatagame.Utils.Constanst.WORD_3;

public class MainGameSukuKata_Activity extends BaseApp implements RecognitionListener {

    Locale localeIndonesia = new Locale("id", "ID");
    SpeechRecognizer mSpeechRecognizer;
    private KonfettiView konfettiView;

    private int countSpeak = 0;
    private int level;

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
        int counterWrong = 0;
        countSpeak++;
        if (countSpeak == WORD_1) {
            if (setCorrectAnswer(WORD_1, eja[0])) {
                setCorectMode(find(R.id.img_word_1));
                bounceAnimate(find(R.id.ly_eja_1));
                counterWrong = 0;
            } else {
                counterWrong++;
                countSpeak = 0;
                setWrongMode(find(R.id.img_word_1));
                shakesAnimate(find(R.id.ly_eja_1));
            }
        } else if (countSpeak == WORD_2) {
            if (setCorrectAnswer(WORD_2, eja[0])) {
                setCorectMode(find(R.id.img_word_3));
                setCorectMode(find(R.id.img_word_4));
                bounceAnimate(find(R.id.ly_eja_2));
                counterWrong = 0;
            } else {
                counterWrong++;
                countSpeak = 1;
                setWrongMode(find(R.id.img_word_3));
                setWrongMode(find(R.id.img_word_4));
                shakesAnimate(find(R.id.ly_eja_2));
            }
        }
    }

    private boolean setCorrectAnswer(int eja, String speech) {
        switch (level) {
            case 0: //ibu
                if (eja == WORD_1 && speech.toLowerCase().contains("i")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("bu");
            default:
                return false;
        }
    }

    private void setImageLevel() {
        level = getIntent().getIntExtra("LEVEL", 1);
        switch (level) {
            case 0: //bumi
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