package com.sunshinexu.mobilelearn.modules.main.presenter;

import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.modules.main.contract.MainContract;

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
        System.out.println("子类重写的re");
    }
}
