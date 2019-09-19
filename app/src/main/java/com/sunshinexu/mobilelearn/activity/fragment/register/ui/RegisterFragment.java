package com.sunshinexu.mobilelearn.activity.fragment.register.ui;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/9
 */

import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.login.ui.LoginFragment;
import com.sunshinexu.mobilelearn.activity.fragment.register.contract.RegisterContract;
import com.sunshinexu.mobilelearn.activity.fragment.register.presenter.RegisterPresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

import java.util.Objects;

import butterknife.BindView;

/**
 * 注册主页面
 */
public class RegisterFragment extends BaseFragment<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.register_username)
    EditText register_username;
    @BindView(R.id.register_password)
    EditText register_password;
    @BindView(R.id.confirm_password)
    EditText confirm_password;
    @BindView(R.id.btn_register)
    TextView btn_register;
    @BindView(R.id.tv_login)
    TextView tv_login;
    private AlertDialog dialog;

    public static RegisterFragment newInstance(){
        return new RegisterFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLogin();
            }
        });

    }

    /**
     * 注册
     */
    private void register() {
        String username = register_username.getText().toString();
        String password = register_password.getText().toString();
        String password2 = confirm_password.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(_mActivity, getString(R.string.username_not_empty),Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(_mActivity, getString(R.string.password_not_empty),Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password2)) {
            Toast.makeText(_mActivity, getString(R.string.confirm_password_not_empty),Toast.LENGTH_SHORT).show();
        } else if (!TextUtils.equals(password, password2)) {
            Toast.makeText(_mActivity, getString(R.string.twice_password_not_same),Toast.LENGTH_SHORT).show();
        } else {
            presenter.register(username, password, password2);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void registerSuccess() {
        Toast.makeText(_mActivity,getString(R.string.register_success),Toast.LENGTH_SHORT).show();
        goLogin();
    }

    private void goLogin() {
        LoginFragment loginFragment = findFragment(LoginFragment.class);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
        }
        getSupportDelegate().showHideFragment(loginFragment, RegisterFragment.this);
    }

    @Override
    public void showLoading() {
        if (dialog == null) {
            View view = LayoutInflater.from(_mActivity).inflate(R.layout.login_progress_bar, null, false);
            TextView loadingText = view.findViewById(R.id.loading_text);
            loadingText.setText(getResources().getString(R.string.logining));
            dialog = new AlertDialog.Builder(_mActivity).setView(view).create();
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        }
        dialog.show();
    }

    @Override
    public void hideLoading() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
