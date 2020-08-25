package com.example.ageCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomwrapperimplement.MainActivity;
import com.example.roomwrapperimplement.R;

public class ACSplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.ac_splash_screen);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ACSplashScreen.this, ACMainActivity.class);
                ACSplashScreen.this.startActivity(mainIntent);
                ACSplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
