package com.sunshinexu.mobilelearn.dagger.component;

import com.sunshinexu.mobilelearn.base.activity.BaseActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface BaseActivityComponent extends AndroidInjector<BaseActivity> {
    @Subcomponent.Builder
    abstract class BaseBuilder extends AndroidInjector.Builder<BaseActivity>{}
}
