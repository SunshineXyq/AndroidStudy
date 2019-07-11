package com.sunshinexu.mobilelearn.base.presenter;

import com.sunshinexu.mobilelearn.base.view.IView;

public class BasePresenter<T extends IView> implements IPresenter<T> {
    @Override
    public void attachView(T view) {

    }

    @Override
    public void detachView() {

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
}
