package com.sunshinexu.mobilelearn.activity.main.presenter;

import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.activity.main.contract.ArticleDetailContract;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

public class ArticleDetailPresenter extends CollectPresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {
    @Inject
    public ArticleDetailPresenter() {
    }

    @Override
    public void checkSharePermission(RxPermissions rxPermissions) {

    }
}
