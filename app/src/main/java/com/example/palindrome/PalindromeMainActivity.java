package com.example.palindrome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomwrapperimplement.R;
import com.example.screens.ForgotPassword;
import com.example.screens.Home;
import com.example.screens.HomeScreen_two;
import com.example.screens.Homescreen;
import com.example.screens.Navigation;
import com.example.screens.ScreenSplash;
import com.example.screens.Setting;


public class PalindromeMainActivity extends AppCompatActivity {


    EditText word;
    Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palindrome_activity_main);

        word = (EditText) findViewById(R.id.et_word);

        check = (Button) findViewById(R.id.btn_check);
        //int totallength = text.length();


        //TODO::  resolve it . it's not working properly
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = word.getText().toString();

                String line = "";
                for (int t=0; t<text.length(); ){
                    line =line+text.charAt(t);
                    t++;
                }

                String reverse = "";
                for (int a=text.length(); a>0 ;){
                    reverse = reverse+ text.charAt(a);
                    a--;
                }


                if(line.equals(reverse)){
                    Toast.makeText(PalindromeMainActivity.this , "This is palindrome", Toast.LENGTH_LONG ).show();
                }else {
                    Toast.makeText(PalindromeMainActivity.this , "This isn't palindrome", Toast.LENGTH_LONG ).show();
                }
            }
        });



  /*      check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            String text = word.getText().toString();

            int len = text.length();

             String first = String.valueOf(text.charAt(0));
             String second = String.valueOf(text.charAt(1));

             String last = String.valueOf(text.charAt(len-1));
             String secondlast = String.valueOf(text.charAt(len-2));

              if(first.equals(last)  && second.equals(secondlast)){
              }else{
                  Toast.makeText(MainActivity.this , "This isn't palindrome", Toast.LENGTH_LONG ).show();
              }



            }
        });*/
    }

}
