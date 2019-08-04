package com.sunshinexu.mobilelearn.activity.fragment.navigation.contract;

import com.sunshinexu.mobilelearn.activity.fragment.navigation.bean.NavigationData;
import com.sunshinexu.mobilelearn.base.presenter.IPresenter;
import com.sunshinexu.mobilelearn.base.view.IView;

import java.util.List;

public interface NavigationContract {
    interface View extends IView{
        void showNavigationData(List<NavigationData> navigationData);
    }
    interface Presenter extends IPresenter<View>{
        void getNavigationData();
    }
}
