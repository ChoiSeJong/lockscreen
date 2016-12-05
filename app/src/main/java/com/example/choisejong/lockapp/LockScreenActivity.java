package com.example.choisejong.lockapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Created by choi se jong on 2016-12-04.
 */

//잠금화면
public class LockScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.lockscreen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }

    //이벤트

}
