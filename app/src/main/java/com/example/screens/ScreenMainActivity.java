package com.example.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roomwrapperimplement.R;


public class ScreenMainActivity extends Activity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_activity_main);

    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.home_btn){
            Intent intent = new Intent(ScreenMainActivity.this, Home.class);
            startActivity(intent);
        }

        if(view.getId()==R.id.screen_one){
            Intent intent = new Intent(ScreenMainActivity.this, Homescreen.class);
            startActivity(intent);
        }

        if(view.getId()==R.id.screen_two){
            Intent intent = new Intent(ScreenMainActivity.this, HomeScreen_two.class);
            startActivity(intent);
        }

        if(view.getId()==R.id.forgot_pass){
            Intent intent = new Intent(ScreenMainActivity.this, ForgotPassword.class);
            startActivity(intent);
        }

        if(view.getId()==R.id.nav){
            Intent intent = new Intent(ScreenMainActivity.this, Navigation.class);
            startActivity(intent);
        }

        if(view.getId()==R.id.splash){
            Intent intent = new Intent(ScreenMainActivity.this, ScreenSplash.class);
            startActivity(intent);
        }

        if(view.getId()==R.id.setting_btn){
            Intent intent = new Intent(ScreenMainActivity.this, Setting.class);
            startActivity(intent);
        }

    }


}
