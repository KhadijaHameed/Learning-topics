package com.example.musicPlayer.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.roomwrapperimplement.R;


/**
 * Created by Abdul Waheed on 5/16/2018.
 */

public class ArtistsFragment extends Fragment {

    private View view;
    GridView gridView;


    public ArtistsFragment() {
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
        view = inflater.inflate(R.layout.mp_fragment_artists, container, false);


        gridView = view. findViewById(R.id.grid_view);


        return view;
    }

}
