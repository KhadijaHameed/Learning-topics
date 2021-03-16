package com.example.musicPlayer.NavItemClasses;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicPlayer.MPMainActivity;
import com.example.roomwrapperimplement.R;

import yuku.ambilwarna.AmbilWarnaDialog;

/**
 * Created by Abdul Waheed on 5/15/2018.
 */

public class Settings extends AppCompatActivity {

    RelativeLayout rl_about,rl_theme,rl_primrary_color,mlayout,rl_manage_tabs,rl_change_font, rl_shake_action;


    RecyclerView rv_manage_tabs;

    View view ;

    int mDeafultColor;

    //Context context;
    ImageView iv_setting_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_settings);

        mlayout =findViewById(R.id.layout);
        mDeafultColor = ContextCompat.getColor(Settings.this,R.color.colorPrimaryDark);


        iv_setting_back = findViewById(R.id.iv_setting_back);

        rl_about = findViewById(R.id.rl_about);
        rl_theme = findViewById(R.id.rl_theme);
        rl_primrary_color = findViewById(R.id.rl_primary_colour);
        rl_manage_tabs = findViewById(R.id.rl_manage_tabs);
        rl_change_font = findViewById(R.id.rl_change_font);
        rl_shake_action = findViewById(R.id.rl_shake_action);


        iv_setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, MPMainActivity.class);
                startActivity(intent);
            }
        });

        rl_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutintent = new Intent(Settings.this,About.class);
                startActivity(aboutintent);
            }
        });

      rl_theme.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              final Dialog dialog = new Dialog(Settings.this);
              dialog.setContentView(R.layout.mp_theme_dailog);
              dialog.setTitle("Title...");
              dialog.show();
          }

      });

      rl_primrary_color.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              openColorPicker();


          }

      });

      rl_change_font.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Dialog dialog = new Dialog(Settings.this);
              dialog.setContentView(R.layout.mp_change_font_dailog);
              dialog.setTitle("Title...");
              dialog.show();
          }
      });

        rl_manage_tabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Settings.this);
                dialog.setContentView(R.layout.mp_manage_tab_dailog);
                dialog.setTitle("Title...");
                dialog.show();
            }
        });

        rl_shake_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Settings.this);
                dialog.setContentView(R.layout.mp_shake_action);
                dialog.setTitle("Title...");
                dialog.show();
            }
        });

  /*      rv_manage_tabs = findViewById(R.id.rv_manage_tab);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_manage_tabs.setLayoutManager(layoutManager);


        rv_manage_tabs.setLayoutManager(layoutManager);
        rv_manage_tabs.setHasFixedSize(true);

        rv_manage_tabs.setHasFixedSize(true);
        rv_manage_tabs.setLayoutManager(new LinearLayoutManager(Settings.this));

        ManageTabRecycler adapter = new ManageTabRecycler();
        rv_manage_tabs.setAdapter(adapter);*/

    }

    private void openColorPicker() {
        AmbilWarnaDialog colorpicker = new AmbilWarnaDialog(this, mDeafultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDeafultColor = color;
                mlayout.setBackgroundColor(mDeafultColor);

                Toast.makeText(Settings.this,"Color Has Been Changed", Toast.LENGTH_SHORT).show();

            }
        });
        colorpicker.show();



    }



}
