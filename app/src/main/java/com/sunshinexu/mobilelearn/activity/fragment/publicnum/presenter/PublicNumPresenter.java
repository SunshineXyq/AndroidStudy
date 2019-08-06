package com.sunshinexu.mobilelearn.activity.fragment.publicnum.presenter;

import com.sunshinexu.mobilelearn.activity.fragment.publicnum.contract.PublicNumContract;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;

import javax.inject.Inject;

public class PublicNumPresenter extends Presenter<PublicNumContract.View> implements PublicNumContract.Presenter {
    @Inject
    public PublicNumPresenter() {
    }

    @Override
    public void getPublicNumData() {

    }
}
