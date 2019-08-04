package com.sunshinexu.mobilelearn.activity.fragment.publicnum.presenter;

import com.sunshinexu.mobilelearn.activity.fragment.publicnum.contract.PublicNumContract;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;

import javax.inject.Inject;

public class PublicNumPresenter extends BasePresenter<PublicNumContract.View> implements PublicNumContract.Presenter {
    @Inject
    public PublicNumPresenter() {
    }

    @Override
    public void getPublicNumData() {

    }
}
