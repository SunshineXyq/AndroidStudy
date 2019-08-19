package com.sunshinexu.mobilelearn.activity.fragment.about.presenter;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/19
 */

import com.sunshinexu.mobilelearn.activity.fragment.about.contract.AboutContract;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;

import javax.inject.Inject;

public class AboutPresenter extends Presenter<AboutContract.View> implements AboutContract.Presenter {
    @Inject
    public AboutPresenter() {
    }
}
