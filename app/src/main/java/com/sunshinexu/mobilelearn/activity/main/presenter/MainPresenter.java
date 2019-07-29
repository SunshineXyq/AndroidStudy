package com.sunshinexu.mobilelearn.activity.main.presenter;

import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.activity.main.contract.MainContract;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    MainPresenter(){

    }

    @Override
    public void logout() {

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
    public void loginSuccessEvent() {
    }
}
