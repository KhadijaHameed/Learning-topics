package com.example.detectingphoneincomenoutgoingcalls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class detectCall extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context, "RINGING", Toast.LENGTH_SHORT).show();
            }
            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                Toast.makeText(context, "RECIEVED(CALL STARTED....)", Toast.LENGTH_SHORT).show();
            }
            if (state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context, "IDLE((CALL ENDED....))", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
