package com.sunshinexu.mobilelearn.modules.main.presenter;

import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.modules.main.contract.MainContract;

import org.greenrobot.eventbus.EventBus;

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
}
