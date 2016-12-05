package com.example.choisejong.lockapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by choi se jong on 2016-12-04.
 */

//잠금화면 ON / OFF 설정
public class ConfigActivity extends Activity {

    private Button onBtn, offBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onBtn= (Button)findViewById(R.id.onBtn);
        offBtn= (Button)findViewById(R.id.offBtn);

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
    }
}
