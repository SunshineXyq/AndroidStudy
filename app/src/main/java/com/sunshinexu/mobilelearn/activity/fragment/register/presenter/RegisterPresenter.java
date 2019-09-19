package com.sunshinexu.mobilelearn.activity.fragment.register.presenter;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/9
 */

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.login.bean.LoginData;
import com.sunshinexu.mobilelearn.activity.fragment.register.contract.RegisterContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.eventbus.RegisterEvent;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

/**
 * 注册主页面相关调用
 */
public class RegisterPresenter extends Presenter<RegisterContract.View> implements RegisterContract.Presenter {
    @Inject
    public RegisterPresenter() {
    }

    @Override
    public void register(String username, String password,String confirmPassword) {
        addSubscribe(dataManager.register(username,password,confirmPassword)
        .compose(RxJavaUtil.SchedulerTransformer())
        .filter(loginDataBaseResponse -> mView != null)
        .subscribeWith(new BaseObserver<LoginData>(mView,
                MobileLearnApp.getContext().getString(R.string.failed_to_register), true) {
            @Override
            public void success(LoginData loginData) {
                EventBus.getDefault().post(new RegisterEvent(loginData.getUsername(),loginData.getPassword()));
                mView.registerSuccess();
            }
        }));
    }
}
