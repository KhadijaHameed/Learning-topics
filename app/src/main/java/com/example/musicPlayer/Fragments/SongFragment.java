package com.example.musicPlayer.Fragments;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicPlayer.FragmentRecyclerViews.SongRecyclerviewAdapter;
import com.example.musicPlayer.SongMoudle;
import com.example.musicPlayer.Utilities.SongUtility;
import com.example.roomwrapperimplement.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdul Waheed on 5/16/2018.
 */

public class SongFragment extends Fragment {

    private View view;
    RecyclerView rv_songs;
    SongRecyclerviewAdapter adapter;

    String MEDIA_PATH = Environment.getExternalStorageDirectory() + "";
    ArrayList<SongMoudle> songList=new ArrayList<>();


    public SongFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.mp_fragment_songs, container, false);

        rv_songs = view.findViewById(R.id.rv_songs);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_songs.setLayoutManager(layoutManager);


        // SongList();

        SongUtility songUtility = new SongUtility();
        List<SongMoudle> songMoudleList = songUtility.getMp3Songs(getContext());

        adapter = new SongRecyclerviewAdapter(songMoudleList);
        rv_songs.setAdapter(adapter);


        return view;


/*

        int permissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            // OPCIONAL(explicaciones de poque pedimos los permisos)
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                //pedir permisos
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        permissionCheck);
            }}
*/


    } }

 /*   private void SongList() {

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        ContentResolver cr = getActivity().getContentResolver();
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION


    };
        Cursor cur = cr.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                sortOrder);

        SongRecyclerviewAdapter.songs = new ArrayList<HashMap<String, String>>();
        while (cur.moveToNext()) {
            HashMap<String, String> map = new HashMap<>();
  *//*  songs.add(cur.getString(0) + "||"
            + cur.getString(1) + "||"
            + cur.getString(2) + "||"
            + cur.getString(3) + "||"
            + cur.getString(4) + "||" );*//*

            map.put("ID", cur.getString(0));
            map.put("artist", cur.getString(1));
            map.put("title", cur.getString(2));
            map.put("displayname", cur.getString(3));
            map.put("duration", cur.getString(4));

            SongRecyclerviewAdapter.songs.add((map));
        }
    }}
*/