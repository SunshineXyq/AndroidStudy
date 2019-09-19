package com.sunshinexu.mobilelearn.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;


import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.sunshinexu.mobilelearn.core.DataManager;
import com.sunshinexu.mobilelearn.dagger.component.DaggerAppComponent;
import com.sunshinexu.mobilelearn.dagger.module.AppModule;
import com.sunshinexu.mobilelearn.dagger.module.HttpModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * 自定义 Application
 */
public class MobileLearnApp extends Application implements HasActivityInjector{

    private static Context context;
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    public DataManager mDataManager;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build().inject(this);
        refWatcher = setLeakCanary();
    }

    /**
     * 内存泄漏检测工具
     * @return 如果当前进程是给LeakCanary堆分析则返回
     */
    private RefWatcher setLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context){
        MobileLearnApp mobileLearnApp = (MobileLearnApp) context.getApplicationContext();
        return mobileLearnApp.refWatcher;
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public static Context getContext(){
        return context;
    }
}
