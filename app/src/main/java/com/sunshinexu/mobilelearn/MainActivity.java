package com.sunshinexu.mobilelearn;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;


import com.sunshinexu.mobilelearn.base.activity.BaseActivity;
import com.sunshinexu.mobilelearn.modules.main.contract.MainContract;
import com.sunshinexu.mobilelearn.modules.main.presenter.MainPresenter;

import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private static final String TAG = "MainActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView barTitle;


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
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            barTitle.setText(R.string.home_page);
        }
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
