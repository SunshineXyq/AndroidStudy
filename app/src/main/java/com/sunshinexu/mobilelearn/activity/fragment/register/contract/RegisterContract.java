package com.sunshinexu.mobilelearn.activity.fragment.register.contract;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/9
 */

import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;

public interface RegisterContract {
    interface View extends BaseView {
        void registerSuccess();
    }

    interface Presenter extends BasePresenter<View>{
        void register(String username,String password);
    }
}
