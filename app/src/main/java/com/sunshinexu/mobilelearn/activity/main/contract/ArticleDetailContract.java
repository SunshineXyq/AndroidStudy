package com.sunshinexu.mobilelearn.activity.main.contract;

import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.CollectContract;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;
import com.tbruyelle.rxpermissions2.RxPermissions;

public interface ArticleDetailContract {
    interface View extends CollectContract.View {
        void shareActivity();
        void shareFailed();
    }

    interface Presenter extends CollectContract.Presenter<View> {
        void checkSharePermission(RxPermissions rxPermissions);
    }
}
