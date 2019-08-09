package com.sunshinexu.mobilelearn.activity.main.ui;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.login.ui.LoginFragment;
import com.sunshinexu.mobilelearn.activity.fragment.register.ui.RegisterFragment;
import com.sunshinexu.mobilelearn.activity.main.contract.LoginContract;
import com.sunshinexu.mobilelearn.activity.main.presenter.LoginPresenter;
import com.sunshinexu.mobilelearn.base.activity.BaseActivity;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white_smoke));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyBoard(LoginActivity.this.getWindow().getDecorView().getRootView());
                onBackPressedSupport();
            }
        });
    }

    public void hideKeyBoard(View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    protected void initView() {
        loadMultipleRootFragment(R.id.fl_login,0, LoginFragment.newInstance(), RegisterFragment.newInstance());
    }

    @Override
    protected void initEventAndData() {

    }

}
