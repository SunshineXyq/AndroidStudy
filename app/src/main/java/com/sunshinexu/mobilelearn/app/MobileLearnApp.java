package com.sunshinexu.mobilelearn.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import com.sunshinexu.mobilelearn.core.DataManager;
import com.sunshinexu.mobilelearn.dagger.module.AppModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MobileLearnApp extends Application implements HasActivityInjector {

    private static Context context;
    @Inject
    DispatchingAndroidInjector<Activity> myAndroidInjector;

    @Inject
    public DataManager mDataManager;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .build().inject(this);

        //夜间模式切换
        if (mDataManager.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return myAndroidInjector;
    }

    public static Context getContext(){
        return context;
    }
}
