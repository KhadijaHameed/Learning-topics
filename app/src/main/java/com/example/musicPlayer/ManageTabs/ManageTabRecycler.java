package com.example.musicPlayer.ManageTabs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwrapperimplement.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Abdul Waheed on 5/18/2018.
 */

public class ManageTabRecycler extends RecyclerView.Adapter<ItemViewHolder> {

    private static final String[] STRINGS = new String[]{
            "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"
    };

    private final List<String> mItems = new ArrayList<>();

    public ManageTabRecycler () {
        mItems.addAll(Arrays.asList(STRINGS));
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mp_single_row_of_manage_tabs, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.textView.setText(mItems.get(position));

    }

    @Override
    public int getItemCount() {
      return mItems.size();








    }
}
