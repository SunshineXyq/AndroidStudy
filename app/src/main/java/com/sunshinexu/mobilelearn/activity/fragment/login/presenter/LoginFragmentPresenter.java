package com.sunshinexu.mobilelearn.activity.fragment.login.presenter;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/9
 */

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.login.bean.LoginData;
import com.sunshinexu.mobilelearn.activity.fragment.login.contract.LoginFragmentContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.eventbus.LoginEvent;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

public class LoginFragmentPresenter extends Presenter<LoginFragmentContract.View> implements LoginFragmentContract.Presenter {
    @Inject
    public LoginFragmentPresenter() {
    }

    @Override
    public void login(String username, String password) {
        addSubscribe(dataManager.login(username, password)
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(loginData -> mView != null)
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_login), true) {
                    @Override
                    public void success(LoginData loginData) {
                        setLoginStatus(true);
                        setLoginAccount(loginData.getUsername());
                        EventBus.getDefault().post(new LoginEvent());
                        mView.loginSuccess();
                    }
                }));
    }
}
