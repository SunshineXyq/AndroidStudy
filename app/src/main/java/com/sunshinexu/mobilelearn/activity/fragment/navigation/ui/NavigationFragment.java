package com.sunshinexu.mobilelearn.activity.fragment.navigation.ui;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.bean.NavigationData;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.contract.NavigationContract;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.presenter.NavigationPresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

import java.util.List;

public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View{

    public static NavigationFragment newInstance(){
        return new NavigationFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showNavigationData(List<NavigationData> navigationData) {

    }
}
