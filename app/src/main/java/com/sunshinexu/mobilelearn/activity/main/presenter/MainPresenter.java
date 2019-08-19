package com.sunshinexu.mobilelearn.activity.main.presenter;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.login.bean.LoginData;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.activity.main.contract.MainContract;
import com.sunshinexu.mobilelearn.core.eventbus.LoginEvent;
import com.sunshinexu.mobilelearn.core.eventbus.LogoutEvent;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import javax.inject.Inject;

public class MainPresenter extends Presenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    MainPresenter() {

    }

    @Override
    public void logout() {
        addSubscribe(dataManager.logout()
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(loginData -> mView != null)
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_logout), false) {
                    @Override
                    public void success(LoginData loginData) {
                        setLoginStatus(false);
                        setLoginAccount("");
                        EventBus.getDefault().post(new LogoutEvent());
                        mView.handleLogoutSuccess();
                    }
                }));
    }

    @Override
    public void setNightMode(boolean isNightMode) {

    }

    @Override
    public boolean isNightMode() {
        return false;
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscriber()
    public void loginSuccessEvent(LoginEvent loginEvent) {
        mView.handleLoginSuccess();
    }
}
