package com.sunshinexu.mobilelearn.dagger.module;

import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.core.DataManager;
import com.sunshinexu.mobilelearn.core.http.HttpHelper;
import com.sunshinexu.mobilelearn.core.http.HttpHelperImpl;
import com.sunshinexu.mobilelearn.pre.PreHelper;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Module 告诉 Component 可以从这个类中获取依赖对象，生成各种类
 * Provides 用来生成类的实例
 * Name 存在相同依赖，告知 Component 到底提供哪一个依赖对象，值只能是字符串
 * Singleton 局部单例，可以保证注入的相应类中是单例
 */
@Module
public class AppModule {
    private final MobileLearnApp application;

    public AppModule(MobileLearnApp application){
        this.application = application;
    }

    @Provides
    @Singleton
    MobileLearnApp provideApplicationContext(){
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelperImpl){
        return httpHelperImpl;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreHelper preHelper){
        return new DataManager(httpHelper,preHelper);
    }
}
