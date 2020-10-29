package com.example.installedapplist;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwrapperimplement.R;

import java.util.ArrayList;

/**
 * Created by Abdul Waheed on 11/30/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<AppNameModel> arrayList;
    private SharedPreferences.Editor sharedPreference;

    public Adapter (Context context , ArrayList<AppNameModel> arrayList,SharedPreferences.Editor editor ){
        this.context = context;
        this.arrayList = arrayList;
        this.sharedPreference = editor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.installed_applist_rv_custom_row , parent , false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppNameModel model = arrayList.get(position);
        holder.Name.setText(model.getAppname());
        holder.Icon.setImageDrawable(model.getAppIcon());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name;
        ImageView Icon ;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);

            Name =  itemView.findViewById(R.id.tv_app);
            Icon = itemView.findViewById(R.id.app_icon);
            checkBox = itemView.findViewById(R.id.checkbox);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                /*    AppNameModel appNameModel =  arrayList.get(getAdapterPosition());
                    sharedPreference.putBoolean(appNameModel.getAppname(), b);*/

                    PackageManager p = context.getPackageManager();
                    ComponentName componentName = new ComponentName(context, InstalledAppListMainActivity.class); // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
                    p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                }
            });
        }

    }
}



