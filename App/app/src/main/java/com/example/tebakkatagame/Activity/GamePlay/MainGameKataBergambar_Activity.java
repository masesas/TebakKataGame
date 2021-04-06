package com.example.tebakkatagame.Activity.GamePlay;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tebakkatagame.Activity.BaseApp;
import com.example.tebakkatagame.R;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static com.example.tebakkatagame.Utils.Constanst.WORD_1;
import static com.example.tebakkatagame.Utils.Constanst.WORD_2;
import static com.example.tebakkatagame.Utils.Constanst.WORD_3;
import static com.example.tebakkatagame.Utils.Constanst.WORD_4;

public class MainGameKataBergambar_Activity extends BaseApp implements RecognitionListener {

    Locale localeIndonesia = new Locale("id", "ID");
    SpeechRecognizer mSpeechRecognizer;
    private KonfettiView konfettiView;

    private int countSpeak = 0;
    private int level;

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
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizer.setRecognitionListener(this);

        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, localeIndonesia);

        find(R.id.img_tebak).setOnTouchListener((View v, MotionEvent event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    mSpeechRecognizer.stopListening();
                    find(R.id.tv_result, TextView.class).setHint("You will see input here");
                    break;

                case MotionEvent.ACTION_DOWN:
                    mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                    find(R.id.tv_result, TextView.class).setText("");
                    find(R.id.tv_result, TextView.class).setHint("Listening...");
                    break;
            }
            return false;
        });
    }

    //by ejakata
    private void setResultSpech(String... eja) {
        int counterWrong = 0;
        countSpeak++;
        if (countSpeak == WORD_1) {
            if (setCorrectAnswer(WORD_1, eja[0])) {
                setCorectMode(find(R.id.img_word_1));
                setCorectMode(find(R.id.img_word_2));
                bounceAnimate(find(R.id.ly_eja_1));
                counterWrong = 0;
            } else {
                counterWrong++;
                countSpeak = 0;
                setWrongMode(find(R.id.img_word_1));
                setWrongMode(find(R.id.img_word_2));
                shakesAnimate(find(R.id.ly_eja_1));
            }
        } else if (countSpeak == WORD_2) {
            if (setCorrectAnswer(WORD_2, eja[0])) {
                setCorectMode(find(R.id.img_word_3));
                setCorectMode(find(R.id.img_word_4));
                bounceAnimate(find(R.id.ly_eja_2));
                selebrateWin();
                counterWrong = 0;
            } else {
                counterWrong++;
                countSpeak = 1;
                setWrongMode(find(R.id.img_word_3));
                setWrongMode(find(R.id.img_word_4));
                shakesAnimate(find(R.id.ly_eja_2));
            }
        } else if (countSpeak == WORD_3) {
            if (setCorrectAnswer(WORD_3, eja[0])) {
                setCorectMode(find(R.id.img_word_5));
                setCorectMode(find(R.id.img_word_6));
                bounceAnimate(find(R.id.ly_eja_3));
                selebrateWin();
                counterWrong = 0;
            } else {
                counterWrong++;
                countSpeak = 2;
                setWrongMode(find(R.id.img_word_5));
                setWrongMode(find(R.id.img_word_6));
                shakesAnimate(find(R.id.ly_eja_3));
            }
        }
    }

    private void selebrateWin(){
        find(R.id.view_blur).setVisibility(View.VISIBLE);
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
            showWinDialog(level + 1, "TEBAK GAMBAR");
        }, 2000);
    }

    private boolean setCorrectAnswer(int eja, String speech) {
        switch (level) {
            case 0: //bumi
                if (eja == WORD_1 && speech.toLowerCase().contains("bu")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("mi");
            case 1: //padi
                if (eja == WORD_1 && speech.toLowerCase().contains("pa")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("di");
            case 2: //gigi
                if (eja == WORD_1 && speech.toLowerCase().contains("gi")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("gi");
            case 3: //dadu
                if (eja == WORD_1 && speech.toLowerCase().contains("da")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("du");
            case 4: //biji
                if (eja == WORD_1 && speech.toLowerCase().contains("bi")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("ji");
            case 5: //gula
                if (eja == WORD_1 && speech.toLowerCase().contains("gu")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("la");
            case 6: //pipi
                if (eja == WORD_1 && speech.toLowerCase().contains("pi")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("pi");
            case 7: //kopi
                if (eja == WORD_1 && speech.toLowerCase().contains("ko")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("pi");
            case 8: //duri
                if (eja == WORD_1 && speech.toLowerCase().contains("du")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("ri");
            case 9: //kayu
                if (eja == WORD_1 && speech.toLowerCase().contains("ka")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("yu");
            case 10: //rusa
                if (eja == WORD_1 && speech.toLowerCase().contains("ru")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("sa");
            case 11: //tali
                if (eja == WORD_1 && speech.toLowerCase().contains("ta")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("li");
            case 12: //peta
                if (eja == WORD_1 && speech.toLowerCase().contains("pe")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("ta");
            case 13: //desa
                if (eja == WORD_1 && speech.toLowerCase().contains("de")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("sa");
            case 14: //roda
                if (eja == WORD_1 && speech.toLowerCase().contains("ro")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("da");
            case 15: //buaya
                if (eja == WORD_1 && speech.toLowerCase().contains("bu")) return true;
                else if (eja == WORD_2 && speech.toLowerCase().contains("a")) return true;
                else return eja == WORD_3 && speech.toLowerCase().contains("ya");
            case 16: //rumah
                if (eja == WORD_1 && speech.toLowerCase().contains("ru")) return true;
                else return eja == WORD_2 && speech.toLowerCase().contains("mah");
            case 17: //delima
                if (eja == WORD_1 && speech.toLowerCase().contains("de")) return true;
                else if (eja == WORD_2 && speech.toLowerCase().contains("li")) return true;
                else return eja == WORD_3 && speech.toLowerCase().contains("ma");
            case 18: //keledai
                if (eja == WORD_1 && speech.toLowerCase().contains("ke")) return true;
                else if (eja == WORD_2 && speech.toLowerCase().contains("le")) return true;
                else return eja == WORD_3 && speech.toLowerCase().contains("dai");
            case 19: //kebaya
                if (eja == WORD_1 && speech.toLowerCase().contains("ke")) return true;
                else if (eja == WORD_2 && speech.toLowerCase().contains("ba")) return true;
                else return eja == WORD_3 && speech.toLowerCase().contains("ya");
            default:
                return false;
        }
    }

    private void setImageLevel() {
        level = getIntent().getIntExtra("LEVEL", 1);
        switch (level) {
            case 0: //bumi
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
        showInfo(getErrorText(error));
    }

    @Override
    public void onResults(Bundle results) {
        List<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String[] result = matches.toArray(new String[]{});
        setResultSpech(result);
        find(R.id.tv_result, TextView.class).setText(matches.get(0));//just dummy
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
}