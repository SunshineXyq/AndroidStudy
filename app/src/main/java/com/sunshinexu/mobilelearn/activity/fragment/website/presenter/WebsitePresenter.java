package com.sunshinexu.mobilelearn.activity.fragment.website.presenter;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.website.bean.WebsiteData;
import com.sunshinexu.mobilelearn.activity.fragment.website.contract.WebsiteContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import java.util.List;

import javax.inject.Inject;

public class WebsitePresenter extends Presenter<WebsiteContract.View> implements WebsiteContract.Presenter {

    @Inject
    public WebsitePresenter() {
    }

    @Override
    public void getWebsiteList() {
        addSubscribe(dataManager.getWebsiteList()
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<List<WebsiteData>>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_website_data),
                        true) {
                    @Override
                    public void success(List<WebsiteData> websiteData) {
                        mView.showWebsiteList(websiteData);
                    }
                }));
    }
}
