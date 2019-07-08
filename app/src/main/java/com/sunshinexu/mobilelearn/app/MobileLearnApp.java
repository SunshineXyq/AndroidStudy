package com.sunshinexu.mobilelearn.app;

import android.app.Application;
import android.content.Context;

public class MobileLearnApp extends Application {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
