package com.sunshinexu.mobilelearn.activity.fragment.website.contract;

import com.sunshinexu.mobilelearn.activity.fragment.website.bean.WebsiteData;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;

import java.util.List;

public interface WebsiteContract {

    interface View extends BaseView {

        void showWebsiteList(List<WebsiteData> usefulSiteData);

    }

    interface Presenter extends BasePresenter<View> {

        void getWebsiteList();
    }
}
