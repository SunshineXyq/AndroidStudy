package com.sunshinexu.mobilelearn.activity.fragment.navigation.presenter;

import com.sunshinexu.mobilelearn.activity.fragment.navigation.contract.NavigationContract;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;

import javax.inject.Inject;

public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter{
    @Inject
    public NavigationPresenter() {
    }

    @Override
    public void getNavigationData() {

    }
}
