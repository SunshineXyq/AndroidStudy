package com.sunshinexu.mobilelearn.base.presenter;

import com.sunshinexu.mobilelearn.base.view.BaseView;

/**
 * Presenter 基类接口
 * @param <T>
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);
    void detachView();
    void reload();
    void registerEventBus();
    void unregisterEventBus();

    void setLoginStatus(boolean loginStatus);
    boolean getLoginStatus();
    String getLoginAccount();
    void setLoginAccount(String account);

}
