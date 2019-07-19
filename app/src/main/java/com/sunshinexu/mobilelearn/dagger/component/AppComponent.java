package com.sunshinexu.mobilelearn.dagger.component;

import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.dagger.module.AllActivityModule;
import com.sunshinexu.mobilelearn.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Component 注解来完成注入 接口名建议 目标类名 + Component
 * Singleton 保证注入到的类中是单例
 */

@Singleton
@Component(modules = {AppModule.class,
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        AllActivityModule.class})         //
public interface AppComponent {
    void inject(MobileLearnApp mobileLearnApp);
}
