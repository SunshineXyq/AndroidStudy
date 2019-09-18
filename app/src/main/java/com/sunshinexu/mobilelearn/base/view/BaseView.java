package com.sunshinexu.mobilelearn.base.view;

/**
 * View 基类接口
 */
public interface BaseView {
    void showErrorMessage(String message);
    void showLoading();
    void hideLoading();
    void showError();

    void showNoNetwork();
    void showEmpty();
    void showContent();
    void handleLoginSuccess();

    void handleLogoutSuccess();
}
