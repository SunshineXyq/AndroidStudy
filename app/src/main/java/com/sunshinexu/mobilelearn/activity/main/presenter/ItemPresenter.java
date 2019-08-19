package com.sunshinexu.mobilelearn.activity.main.presenter;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/19
 */

import com.sunshinexu.mobilelearn.activity.main.contract.ItemContract;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;

import javax.inject.Inject;

public class ItemPresenter extends Presenter<ItemContract.View> implements ItemContract.Presenter {
    @Inject
    public ItemPresenter() {
    }
}
