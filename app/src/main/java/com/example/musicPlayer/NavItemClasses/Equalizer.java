package com.example.musicPlayer.NavItemClasses;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.roomwrapperimplement.R;
import com.sdsmdg.harjot.crollerTest.Croller;

/**
 * Created by Abdul Waheed on 5/15/2018.
 */

public class Equalizer extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_equalizer);

        Croller croller = findViewById(R.id.croller);
        croller.setIndicatorWidth(10);
        croller.setBackCircleColor(Color.parseColor("#EDEDED"));
        croller.setMainCircleColor(Color.WHITE);
        croller.setMax(25);
        croller.setStartOffset(45);
        croller.setIsContinuous(false);
        croller.setLabel("Bass");
        croller.setLabelSize(20);
        croller.setLabelColor(Color.BLACK);
        croller.setProgressPrimaryColor(Color.parseColor("#FF0000"));
        croller.setIndicatorColor(Color.parseColor("#FF0000"));
        croller.setProgressSecondaryColor(Color.parseColor("#EEEEEE"));

        Croller croller2 =  findViewById(R.id.croller2);
        croller2.setIndicatorWidth(10);
        croller2.setBackCircleColor(Color.parseColor("#EDEDED"));
        croller2.setMainCircleColor(Color.WHITE);
        croller2.setMax(25);
        croller2.setLabel("VIRTUAL");
        croller2.setLabelSize(20);
        croller2.setStartOffset(45);
        croller2.setIsContinuous(false);
        croller2.setLabelColor(Color.BLACK);
        croller2.setProgressPrimaryColor(Color.parseColor("#FF0000"));
        croller2.setIndicatorColor(Color.parseColor("#FF0000"));
        croller2.setProgressSecondaryColor(Color.parseColor("#EEEEEE"));

        Croller croller3 =  findViewById(R.id.croller3);
        croller3.setIndicatorWidth(10);
        croller3.setBackCircleColor(Color.parseColor("#EDEDED"));
        croller3.setMainCircleColor(Color.WHITE);
        croller3.setMax(25);
        croller3.setLabel("LOUDNESS");
        croller3.setLabelColor(Color.WHITE);
        croller3.setLabelSize(20);
        croller3.setStartOffset(45);
        croller3.setIsContinuous(false);
        croller3.setLabelColor(Color.BLACK);
        croller3.setProgressPrimaryColor(Color.parseColor("#FF0000"));
        croller3.setIndicatorColor(Color.parseColor("#FF0000"));
        croller3.setProgressSecondaryColor(Color.parseColor("#EEEEEE"));

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
