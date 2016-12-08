package com.example.choisejong.lockapp;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by choi se jong on 2016-12-04.
 */

//현재 스마트폰 화면상태
public class ScreenStateReceiver extends BroadcastReceiver {

    private KeyguardManager km = null;
    private KeyguardManager.KeyguardLock kl = null;
    private TelephonyManager tpm = null;
    private boolean isPhoneIdle = true;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            if(km == null)
                km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            if(kl == null)
                kl = km.newKeyguardLock(Context.KEYGUARD_SERVICE);
            if(tpm == null){
                tpm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
                tpm.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
            }

            if(isPhoneIdle){
                disableKeyguard();

                Intent i = new Intent(context,LockScreenActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    }

    public void reenableKeyguard() {
        kl.reenableKeyguard();
    }

    public void disableKeyguard() {
        kl.disableKeyguard();
    }

    private PhoneStateListener phoneListener = new PhoneStateListener(){
        @Override
        public void onCallStateChanged(int state, String incomingNumber){
            switch(state){
                case TelephonyManager.CALL_STATE_IDLE :
                    isPhoneIdle = true;
                    break;
                case TelephonyManager.CALL_STATE_RINGING :
                    isPhoneIdle = false;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK :
                    isPhoneIdle = false;
                    break;
            }
        }
    };
}
