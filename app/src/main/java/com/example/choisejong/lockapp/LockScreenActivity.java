package com.example.choisejong.lockapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by choi se jong on 2016-12-04.
 */

//잠금화면
public class LockScreenActivity extends Activity {

    Intent sIntent;
    SpeechRecognizer mRecognizer;
    TextView stextView;
    Button sButton;

    @Override
    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.lockscreen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        init();
    }

    //이벤트

    private RecognitionListener listener = new RecognitionListener() {

        @Override
        public void onReadyForSpeech(Bundle bundle) {
            Log.d("SPEECH","READY");
        }

        @Override
        public void onBeginningOfSpeech() {
            Log.d("SPEECH","START");
        }

        @Override
        public void onRmsChanged(float v) {
        }

        @Override
        public void onBufferReceived(byte[] bytes) {
        }

        @Override
        public void onEndOfSpeech() {
            Log.d("SPEECH","END");
        }

        @Override
        public void onError(int i) {
            Log.d("SPEECH","ERROR");
        }

        @Override
        public void onResults(Bundle results) {
            Log.d("SPEECH","RESULTS");

            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = results.getStringArrayList(key);
            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            stextView.setText(""+rs[0]);

            if(ConfigActivity.pwd.equals(rs[0])) {
                Log.d("APPSTATE","ONDESTROY");
                finish();
            }

        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    };

    private void init() {

        sIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        sIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        sIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(LockScreenActivity.this);
        mRecognizer.setRecognitionListener(listener);

        stextView = (TextView)findViewById(R.id.stv);
        sButton = (Button)findViewById(R.id.sbtn);

        sButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                start();
            }});
    }

    private void start() {
        mRecognizer.startListening(sIntent);
    }

    @Override
    public void onBackPressed() {
        //
    }

}
