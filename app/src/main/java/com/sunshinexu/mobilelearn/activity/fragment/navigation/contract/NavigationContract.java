package com.sunshinexu.mobilelearn.activity.fragment.navigation.contract;

import com.sunshinexu.mobilelearn.activity.fragment.navigation.bean.NavigationData;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;

import java.util.List;

public interface NavigationContract {
    interface View extends BaseView {
        void showNavigationData(List<NavigationData> navigationData);
    }
    interface Presenter extends BasePresenter<View> {
        void getNavigationData();
    }
}
