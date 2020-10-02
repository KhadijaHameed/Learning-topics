package com.example.installedapplist;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwrapperimplement.R;

import java.util.ArrayList;
import java.util.List;

public class InstalledAppListMainActivity extends AppCompatActivity {

    //constant
    public static final String INSTALLED_APPS_LIST = "installed_application_list";

    RecyclerView recyclerView;
    ArrayList<AppNameModel> appNamearrayList;
    Adapter recyclerAdapter;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.installed_app_list_activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        appNamearrayList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        sharedPreferences = getSharedPreferences(INSTALLED_APPS_LIST, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        readAppName();

        recyclerAdapter = new Adapter(this, appNamearrayList, editor);
        recyclerView.setAdapter(recyclerAdapter);
    }


    public void readAppName() {
        List<PackageInfo> packList = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packList.size(); i++) {
            PackageInfo packInfo = packList.get(i);
            if ((packInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String appName = packInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                editor.putBoolean(appName, false);
                Drawable icon = packInfo.applicationInfo.loadIcon(getPackageManager());
                AppNameModel model = new AppNameModel();
                model.setAppname(appName);
                model.setAppIcon(icon);
                appNamearrayList.add(model);
            }
        }
    }
}