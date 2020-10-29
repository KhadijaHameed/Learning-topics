package com.example.roomwrapperimplement.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwrapperimplement.R;
import com.example.roomwrapperimplement.db.PersonInfoViewModel;
import com.example.roomwrapperimplement.pojo.PersonInfo;

import java.util.ArrayList;
import java.util.List;

public class AllDataAdapter extends RecyclerView.Adapter<AllDataAdapter.ViewHolder> {

    List<PersonInfo> personInfoList = new ArrayList<>();
    Context context;

    public AllDataAdapter(Context c){
        this.context = c;
    }
    /*  public AllDataAdapter(Context c,List<PersonInfo> personINfoList ){
        this.context = c;
        this.personInfoList = personINfoList;
    }*/

    public void setPersonInfoList(List<PersonInfo> personInfo){
        this.personInfoList = personInfo;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_data, parent, false);
        ViewHolder vh = new ViewHolder(mView);
        return vh;

    }

    public  PersonInfo getDataAt(int position){
        return personInfoList.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        PersonInfo personInfo = personInfoList.get(position);
        // Set item views based on your views and data model
        holder.tvFName.setText(personInfo.getFirstName());
       // holder.tvLName.setText(personInfo.getLastName());

        String fiestNAme = holder.tvFName.getText().toString();
        holder.tvDeleteThisRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = position;
                holder.getAdapterPosition();
                PersonInfoViewModel  personInfoViewModel = ViewModelProviders.of((FragmentActivity) context).get(PersonInfoViewModel.class);
                PersonInfo personInfo = new PersonInfo(fiestNAme);
                personInfoViewModel.delete(personInfo);
                Toast.makeText(context, "Deleted row"+ i+ " "+fiestNAme, Toast.LENGTH_SHORT).show();
                //dbRepo.delete(personInfo);
               //  PersonInfo personInfo1 = ViewModelProviders.of().get(PersonInfoViewModel.class);
              // repo.delete((holder.tvFName.getText().toString()));
            }
        });

        holder.ivEditThisRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PersonInfoViewModel  personInfoViewModel = ViewModelProviders.of((FragmentActivity) context).get(PersonInfoViewModel.class);

                Intent intent = new Intent(context, EditInfo.class);
                intent.putExtra("name", fiestNAme);
                context.startActivity(intent);



            }
        });
    }

    @Override
    public int getItemCount() {
        return personInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvFName, tvLName , tvDeleteThisRow ;
        ImageView ivEditThisRow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFName = itemView.findViewById(R.id.tv_fname);
            tvLName = itemView.findViewById(R.id.tv_lname);
            ivEditThisRow = itemView.findViewById(R.id.iv_edit__this_row);
            tvDeleteThisRow = itemView.findViewById(R.id.delete_this_row);
        }
    }
}
