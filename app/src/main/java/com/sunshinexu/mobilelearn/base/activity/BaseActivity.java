package com.sunshinexu.mobilelearn.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;


import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import android.support.v4.app.Fragment;

public abstract class BaseActivity<T extends BasePresenter> extends AbstractSimpleActivity
        implements HasSupportFragmentInjector, BaseView {
    private static final String TAG = "BaseActivity";

    @Inject
    protected T mPresenter;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);     //实现 Activity 的注入
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    /**
     * 父类onCreate调用onViewCreate
     * 先执行父类onCreate，在执行子类实现的 onViewCreate
     * 最后执行子类的 onCreate
     */
    @Override
    protected void onViewCreate() {
        Log.d(TAG, "onViewCreate: onViewCreate");
        //this 是 MainActivity，mPresenter 是 MainPresenter
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
