package com.example.musicPlayer.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.roomwrapperimplement.R;

/**
 * Created by Abdul Waheed on 5/16/2018.
 */

public class AlbumFragments extends Fragment {

    private View view;


    public AlbumFragments() {
        //
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.mp_albums_fragment, container, false);


        return view;

    }

}
