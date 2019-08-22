package com.sunshinexu.mobilelearn.activity.fragment.login.contract;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/9
 */

import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;
import com.sunshinexu.mobilelearn.core.eventbus.RegisterEvent;

public interface LoginFragmentContract {
    interface View extends BaseView {
        void loginSuccess();

        void registerSuccess(RegisterEvent registerEvent);
    }

    interface Presenter extends BasePresenter<View> {
        void login(String username,String password);
    }
}
