package com.sunshinexu.mobilelearn.base.presenter;

import com.sunshinexu.mobilelearn.base.view.IView;

public class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mView;
    @Override
    public void attachView(T view) {
        this.mView = view;
        registerEventBus();
    }

    @Override
    public void detachView() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void registerEventBus() {
        System.out.println("子类重写了");
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
}
