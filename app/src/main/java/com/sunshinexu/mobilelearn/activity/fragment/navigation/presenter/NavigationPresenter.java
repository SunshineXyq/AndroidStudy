package com.sunshinexu.mobilelearn.activity.fragment.navigation.presenter;

import com.sunshinexu.mobilelearn.activity.fragment.navigation.contract.NavigationContract;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;

import javax.inject.Inject;

public class NavigationPresenter extends Presenter<NavigationContract.View> implements NavigationContract.Presenter{
    @Inject
    public NavigationPresenter() {
    }

    @Override
    public void getNavigationData() {

    }
}
