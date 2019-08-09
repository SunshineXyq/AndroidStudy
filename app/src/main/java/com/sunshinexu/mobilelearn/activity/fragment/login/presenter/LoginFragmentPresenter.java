package com.sunshinexu.mobilelearn.activity.fragment.login.presenter;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/9
 */

import com.sunshinexu.mobilelearn.activity.fragment.login.contract.LoginFragmentContract;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;

import javax.inject.Inject;

public class LoginFragmentPresenter extends Presenter<LoginFragmentContract.View> implements LoginFragmentContract.Presenter {
    @Inject
    public LoginFragmentPresenter() {
    }

    @Override
    public void login(String username, String password) {

    }
}
