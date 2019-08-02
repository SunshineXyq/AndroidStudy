package com.sunshinexu.mobilelearn.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.sunshinexu.mobilelearn.base.presenter.IPresenter;
import com.sunshinexu.mobilelearn.base.view.IView;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public abstract class BaseFragment<T extends IPresenter> extends AbstractSimpleFragment implements IView {

    @Inject
    protected T Presenter;

    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Presenter != null) {
            Presenter.attachView(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (Presenter != null) {
            Presenter = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (Presenter != null) {
            Presenter.detachView();
        }
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showNoNetwork() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void handleLoginSuccess() {

    }

    @Override
    public void handleLogoutSuccess() {

    }
}
