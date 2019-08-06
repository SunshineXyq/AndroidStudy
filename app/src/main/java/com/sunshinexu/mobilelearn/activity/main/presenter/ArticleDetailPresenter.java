package com.sunshinexu.mobilelearn.activity.main.presenter;

import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.activity.main.contract.ArticleDetailContract;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class ArticleDetailPresenter extends CollectPresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {
    @Override
    public void checkSharePermission(RxPermissions rxPermissions) {

    }
}
