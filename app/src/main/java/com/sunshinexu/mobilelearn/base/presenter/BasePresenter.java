package com.sunshinexu.mobilelearn.base.presenter;

import android.util.Log;

import com.sunshinexu.mobilelearn.base.view.IView;
import com.sunshinexu.mobilelearn.core.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * BasePresenter 是对Presenter的封装
 * @param <T>
 */

public class BasePresenter<T extends IView> implements IPresenter<T>{

    private static final String TAG = "BasePresenter";

    protected T mView;
    private CompositeDisposable compositeDisposable;

    @Inject
    public DataManager dataManager;

    public void attachView(T view) {
        Log.d(TAG, "attachView: ");
        this.mView = view;
        registerEventBus();
    }

    @Override
    public void detachView() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        unregisterEventBus();
    }

    @Override
    public void reload() {

    }

    @Override
    public void registerEventBus() {
    }

    @Override
    public void unregisterEventBus() {

    }

    @Override
    public void setLoginStatus(boolean loginStatus) {

    }

    @Override
    public void getLoginStatus() {

    }

    @Override
    public String getLoginAccount() {
        return null;
    }

    @Override
    public void setLoginAccount(String account) {

    }

    protected void addSubscribe(Disposable disposable){
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}
