package com.sunshinexu.mobilelearn.dagger.module;

import com.sunshinexu.mobilelearn.MainActivity;
import com.sunshinexu.mobilelearn.dagger.component.BaseActivityComponent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AllActivityModule {
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivityInjector();
}
