package com.sunshinexu.mobilelearn.base.view;

public interface IView {
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
