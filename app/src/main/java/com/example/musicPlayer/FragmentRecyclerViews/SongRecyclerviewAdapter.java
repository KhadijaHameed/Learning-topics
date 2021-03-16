package com.example.musicPlayer.FragmentRecyclerViews;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicPlayer.SongMoudle;
import com.example.roomwrapperimplement.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdul Waheed on 5/17/2018.
 */

public class SongRecyclerviewAdapter extends RecyclerView.Adapter<SongRecyclerviewAdapter.MyViewHolder> {

   // static ArrayList<HashMap<String, String>> song = new ArrayList<>();
   // public static ArrayList<HashMap<String, String>> songs;

    public ArrayList<SongMoudle> songMoudleArrayList =  new ArrayList<>();



    public SongRecyclerviewAdapter(List<SongMoudle> songMoudleList) {
        this.songMoudleArrayList = (ArrayList<SongMoudle>) songMoudleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.mp_song_single_row,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull SongRecyclerviewAdapter.MyViewHolder holder, int position) {



        SongMoudle songMoudle =songMoudleArrayList.get(position);
        holder.song_name.setText(songMoudle.getSongname());
        holder.singer_name.setText(songMoudle.getSingername());
        holder.duration.setText(songMoudle.getDuration());
    }

    @Override
    public int getItemCount() {
        return songMoudleArrayList.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView song_name,singer_name,duration;


        public MyViewHolder(View itemView) {
            super(itemView);

            song_name = (TextView) itemView.findViewById(R.id.tv_song_name);
            singer_name = (TextView) itemView.findViewById(R.id.tv_singer_name);
            duration = (TextView) itemView.findViewById(R.id.tv_duration);
        }
    }
}
