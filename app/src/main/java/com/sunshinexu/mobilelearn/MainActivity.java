package com.sunshinexu.mobilelearn;

import android.os.Bundle;
import android.util.Log;

import com.sunshinexu.mobilelearn.base.activity.BaseActivity;
import com.sunshinexu.mobilelearn.modules.main.contract.MainContract;
import com.sunshinexu.mobilelearn.modules.main.presenter.MainPresenter;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }
    protected int getLayoutId(){
        return R.layout.activity_main;
    }


    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEventAndData() {

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
