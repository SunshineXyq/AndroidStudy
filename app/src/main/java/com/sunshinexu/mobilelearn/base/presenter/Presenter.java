package com.sunshinexu.mobilelearn.base.presenter;

import android.util.Log;

import com.sunshinexu.mobilelearn.base.view.BaseView;
import com.sunshinexu.mobilelearn.core.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @param <T>
 */

public class Presenter<T extends BaseView> implements BasePresenter<T> {

    private static final String TAG = "Presenter";

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
        dataManager.setLoginStatus(loginStatus);
    }

    @Override
    public boolean getLoginStatus() {
        return dataManager.getLoginStatus();
    }

    @Override
    public String getLoginAccount() {
        return dataManager.getLoginAccount();
    }

    @Override
    public void setLoginAccount(String account) {
        dataManager.setLoginAccount(account);
    }


    /**
     * CompositeDisposable 存放 disposable 的容器，视图销毁时，可切断所有订阅事件，防止内存泄露
     * @param disposable 可用来取消订阅事件
     */

    protected void addSubscribe(Disposable disposable){
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}
