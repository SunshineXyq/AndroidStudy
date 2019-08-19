package com.sunshinexu.mobilelearn.activity.main.contract;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/19
 */

import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;

public interface ItemContract {
    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<View>{

    }
}
