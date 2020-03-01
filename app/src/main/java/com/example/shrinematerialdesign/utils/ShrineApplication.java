package com.example.shrinematerialdesign.utils;

import android.app.Application;
import android.content.Context;

public class ShrineApplication extends Application {
    private static Context context;

    public static Context getAppContext() {
        return ShrineApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ShrineApplication.context= getApplicationContext();
    }
}
