package com.sunshinexu.mobilelearn.activity.fragment.login.ui;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/9
 */

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.login.contract.LoginFragmentContract;
import com.sunshinexu.mobilelearn.activity.fragment.login.presenter.LoginFragmentPresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

public class LoginFragment extends BaseFragment<LoginFragmentPresenter> implements LoginFragmentContract.View {


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

    }

    @Override
    protected void initData() {

    }
}
