package com.sunshinexu.mobilelearn.activity.fragment.login.ui;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/9
 */

import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.login.contract.LoginFragmentContract;
import com.sunshinexu.mobilelearn.activity.fragment.login.presenter.LoginFragmentPresenter;
import com.sunshinexu.mobilelearn.activity.fragment.register.ui.RegisterFragment;
import com.sunshinexu.mobilelearn.activity.main.ui.LoginActivity;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

import java.util.Objects;

import butterknife.BindView;

public class LoginFragment extends BaseFragment<LoginFragmentPresenter> implements LoginFragmentContract.View {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_login)
    TextView btn_login;
    @BindView(R.id.tv_sign_up)
    TextView tv_sign_up;
    private AlertDialog dialog;

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    /**
     * 登录
     */
    private void login() {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(_mActivity, getString(R.string.username_not_empty),Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(_mActivity, getString(R.string.password_not_empty),Toast.LENGTH_SHORT).show();
        } else {
            presenter.login(username, password);
        }
    }

    /**
     * 注册
     */
    private void register(){
        RegisterFragment registerFragment = findFragment(RegisterFragment.class);
        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance();
        }
        getSupportDelegate().showHideFragment(registerFragment, LoginFragment.this);
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

    @Override
    protected void initData() {

    }
}
