package com.sunshinexu.mobilelearn.activity.main.presenter;

import android.Manifest;

import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.activity.main.contract.ArticleDetailContract;
import com.tbruyelle.rxpermissions2.RxPermissions;



import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class ArticleDetailPresenter extends CollectPresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {
    @Inject
    public ArticleDetailPresenter() {
    }

    @Override
    public void checkSharePermission(RxPermissions rxPermissions) {
        //检查是否授权
        addSubscribe(rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            mView.shareArticle();
                        } else {
                            mView.shareFailed();
                        }
                    }
                }));
    }
}
