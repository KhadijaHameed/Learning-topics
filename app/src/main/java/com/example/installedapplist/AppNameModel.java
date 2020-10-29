package com.example.installedapplist;

import android.graphics.drawable.Drawable;

/**
 * Created by Abdul Waheed on 11/30/2017.
 */

public class AppNameModel {

    String appname;
    Drawable AppIcon;

    public Drawable getAppIcon() {
        return AppIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        AppIcon = appIcon;
    }


    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }
}
