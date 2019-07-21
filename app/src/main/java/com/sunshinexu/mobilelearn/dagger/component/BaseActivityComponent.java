package com.sunshinexu.mobilelearn.dagger.component;

import com.sunshinexu.mobilelearn.base.activity.BaseActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * 每一个继承于 BaseActivity 的 Activity 都继承于同一个子组件
 */
//与 appComponent 中 AndroidInjectionModule.class 对应使用
@Subcomponent(modules = {AndroidInjectionModule.class})    //此类可以帮助将Android四大组件及fragment绑定
public interface BaseActivityComponent extends AndroidInjector<BaseActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseActivity>{}
}
