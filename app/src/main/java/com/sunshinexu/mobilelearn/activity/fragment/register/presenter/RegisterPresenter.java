package com.sunshinexu.mobilelearn.activity.fragment.register.presenter;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/9
 */

import com.sunshinexu.mobilelearn.activity.fragment.register.contract.RegisterContract;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;

import javax.inject.Inject;

public class RegisterPresenter extends Presenter<RegisterContract.View> implements RegisterContract.Presenter {
    @Inject
    public RegisterPresenter() {
    }

    @Override
    public void register(String username, String password) {

    }
}
