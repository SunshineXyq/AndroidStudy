package com.sunshinexu.mobilelearn.activity.main.presenter;

import com.sunshinexu.mobilelearn.activity.main.contract.LoginContract;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;

import javax.inject.Inject;

public class LoginPresenter extends Presenter<LoginContract.View> implements LoginContract.Presenter{
    @Inject
    public LoginPresenter() {
    }
}
