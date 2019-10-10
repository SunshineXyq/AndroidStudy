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

/**
 * 每个Activity都需要继承此类
 * @param <T>
 */
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
        //this 指的是继承 BaseActivity 的 Activity
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showNoNetwork() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void handleLoginSuccess() {

    }

    @Override
    public void handleLogoutSuccess() {

    }
}
