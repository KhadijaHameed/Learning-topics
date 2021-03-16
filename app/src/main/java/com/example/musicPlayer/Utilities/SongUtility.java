package com.example.musicPlayer.Utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;

import com.example.musicPlayer.SongMoudle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdul Waheed on 5/17/2018.
 */

public class SongUtility {

  //  private ArrayList<SongMoudle> songsList;
    public List<SongMoudle> getMp3Songs(Context context) {

         ArrayList<SongMoudle> songsList =null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {

                Uri allSongsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
                Cursor cursor = context.getContentResolver().query(allSongsUri, null, null, null, selection);
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        songsList = new ArrayList<>();
                        do {

                            SongMoudle songMoudle = new SongMoudle();

                    /*    Song song = new Song(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)),
                                cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)),
                                cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                                cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                        songsList.add(song);*/


                            String song_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                            String singer_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                            String song_duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));


                            songMoudle.setSongname(song_name);
                            songMoudle.setSingername(singer_name);
                            songMoudle.setDuration(song_duration);

                            songsList.add(songMoudle);
                       /* int album_id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                        int artist_id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));*/
                        } while (cursor.moveToNext());
                    }
                    cursor.close();

                }





            }
        }

        return songsList;
    }}