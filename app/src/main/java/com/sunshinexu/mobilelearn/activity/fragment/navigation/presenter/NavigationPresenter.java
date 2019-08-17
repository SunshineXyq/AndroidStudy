package com.sunshinexu.mobilelearn.activity.fragment.navigation.presenter;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.bean.NavigationData;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.contract.NavigationContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import java.util.List;

import javax.inject.Inject;

public class NavigationPresenter extends Presenter<NavigationContract.View> implements NavigationContract.Presenter {
    @Inject
    public NavigationPresenter() {
    }

    @Override
    public void getNavigationData() {
        addSubscribe(dataManager.getNavigationData()
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(navigationListData -> mView != null)
                .subscribeWith(new BaseObserver<List<NavigationData>>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_navigation_data), true) {

                    @Override
                    public void success(List<NavigationData> navigationData) {
                        mView.showNavigationData(navigationData);
                    }
                }));
    }

    @Override
    public void reload() {
        getNavigationData();
    }
}
