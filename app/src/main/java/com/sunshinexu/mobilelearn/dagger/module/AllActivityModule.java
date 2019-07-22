package com.sunshinexu.mobilelearn.dagger.module;

import com.sunshinexu.mobilelearn.MainActivity;
import com.sunshinexu.mobilelearn.dagger.component.BaseActivityComponent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AllActivityModule {
    //ContributesAndroidInjector 解决每个类都需要xxx.inject(this),以及类需要知道注入器
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivityInjector();
}
