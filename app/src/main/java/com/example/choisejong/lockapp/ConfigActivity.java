package com.example.choisejong.lockapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by choi se jong on 2016-12-04.
 */

//잠금화면 ON / OFF 설정
public class ConfigActivity extends Activity {

    private Button onBtn, offBtn;
    private Button pBtn;
    private EditText pEt;
    private TextView pTv;

   static String pwd = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBtn= (Button)findViewById(R.id.onBtn);
        offBtn= (Button)findViewById(R.id.offBtn);
        pBtn = (Button)findViewById(R.id.pButton);
        pEt = (EditText)findViewById(R.id.pEditText);
        pTv = (TextView)findViewById(R.id.pTextView);

        onBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ConfigActivity.this , ScreenService.class);
                startService(intent);
            }});

        offBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigActivity.this , ScreenService.class);
                stopService(intent);
        }});

        pBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pwd = pEt.getText().toString();

                Log.d("PassWord",""+pwd);

                pTv.setText(pEt.getText());
            }});
    }

}
