package com.sunshinexu.mobilelearn.dagger.component;

import com.sunshinexu.mobilelearn.base.activity.BaseActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * 每一个继承于 BaseActivity 的 Activity 都继承于同一个子组件
 */
@Subcomponent(modules = {AndroidInjectionModule.class})
public interface BaseActivityComponent extends AndroidInjector<BaseActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseActivity>{}
}
