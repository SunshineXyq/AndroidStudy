package com.sunshinexu.mobilelearn.base.view;

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
