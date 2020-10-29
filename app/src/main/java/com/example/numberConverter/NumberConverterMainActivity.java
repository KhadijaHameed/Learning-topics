package com.example.numberConverter;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomwrapperimplement.R;

import java.util.Locale;

public class NumberConverterMainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private TextView tv_converted_no;
    private EditText et_number;
    private Button btn_binary_convert, btn_octal_convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.num_converter_activity_main);

        initViews();
        btnBinaryConvert();
        btnOctalConvert();

    }

    private void btnOctalConvert() {
        btn_octal_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_number.getText().toString().isEmpty()) {
                    et_number.setError("Plese Enter Any Number");
                    Toast.makeText(NumberConverterMainActivity.this, "First Enter Number Then Clicked", Toast.LENGTH_SHORT).show();
                } else {
                    String number = et_number.getText().toString();
                    int yournumber = Integer.parseInt(number);

                    StringBuffer buffer = new StringBuffer();

                    while (yournumber != 0) {

                        int digit = yournumber % 8;
                        buffer.append(digit);
                        yournumber = yournumber / 8;
                    }
                    buffer.reverse();

                    tv_converted_no.setText("Your No :" + number + "\n\n" + "Your Number in Octal : " + buffer);

                    et_number.setText("");
                    speakOut();
                }

            }
        });
    }

    private void btnBinaryConvert() {
        btn_binary_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_number.getText().toString().isEmpty()) {
                    et_number.setError("Plese Enter Any Number");
                    Toast.makeText(NumberConverterMainActivity.this, "First Enter Number Then Clicked", Toast.LENGTH_SHORT).show();
                } else {
                    String number = et_number.getText().toString();
                    int yournumber = Integer.parseInt(number);

                 /*
                    this is the simple way but that was java own method and i awas creat my own methods
                    String bin = Integer.toBinaryString(yournumber);
                    String oct = Integer.toOctalString(yournumber);
                    String hex = Integer.toHexString(yournumber);

                    tv_converted_no.setText("Your No :" + yournumber + "\n\n" + "Your No In Binary :" + bin + "\n\n" + "Your No In Octal :"
                                            + oct + "\n\n" + "Your No In Hexa :" + hex );*/

                    StringBuffer buffer = new StringBuffer();

                    while (yournumber != 0) {

                        int digit = yournumber % 2;
                        buffer.append(digit);
                        yournumber = yournumber / 2;
                    }
                    buffer.reverse();

                    tv_converted_no.setText("Your No :" + number + "\n\n" + "Your Number in Binary : " + buffer);
                    et_number.setText("");

                    speakOut();

                }
            }
        });

    }

    private void initViews() {
        textToSpeech = new TextToSpeech(NumberConverterMainActivity.this, this);

        tv_converted_no = findViewById(R.id.tv_converterd_number);
        et_number = findViewById(R.id.et_number);
        btn_binary_convert = findViewById(R.id.btn_binary_convert);
        btn_octal_convert = findViewById(R.id.btn_octal_convert);
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    private void speakOut() {
        String text = tv_converted_no.getText().toString();
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                btn_binary_convert.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
}

