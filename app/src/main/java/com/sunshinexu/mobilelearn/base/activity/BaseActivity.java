package com.sunshinexu.mobilelearn.base.activity;

import com.sunshinexu.mobilelearn.base.presenter.IPresenter;
import com.sunshinexu.mobilelearn.base.view.IView;

import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseActivity<T extends IPresenter> extends AbstractSimpleActivity
        implements HasSupportFragmentInjector, IView {

}
