package com.sunshinexu.mobilelearn.base.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sunshinexu.mobilelearn.base.presenter.IPresenter;
import com.sunshinexu.mobilelearn.base.view.IView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseActivity<T extends IPresenter> extends AbstractSimpleActivity
        implements HasSupportFragmentInjector, IView {
    private static final String TAG = "BaseActivity";

    @Inject
    protected T mPresenter;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
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
