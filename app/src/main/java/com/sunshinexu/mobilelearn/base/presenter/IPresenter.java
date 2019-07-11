package com.sunshinexu.mobilelearn.base.presenter;

import com.sunshinexu.mobilelearn.base.view.IView;

public interface IPresenter<T extends IView> {

    void attachView(T view);
    void detachView();
    void reload();
    void registerEventBus();
    void unregisterEventBus();

    void setLoginStatus(boolean loginStatus);
    void getLoginStatus();
    String getLoginAccount();
    void setLoginAccount(String account);

}
