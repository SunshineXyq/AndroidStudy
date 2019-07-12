package com.sunshinexu.mobilelearn.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MobileLearnApp extends Application implements HasActivityInjector {

    private Context context;
    @Inject
    DispatchingAndroidInjector<Activity> myAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return myAndroidInjector;
    }
}
