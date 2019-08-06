package com.sunshinexu.mobilelearn.activity.main.contract;

import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;

/**
 * 约束管理，方便类的查找和维护
 */
public interface MainContract {
    interface View extends BaseView {

    }

    //传递的是上个接口的 View，MainPresenter 继承 Presenter 实现此接口
    interface Presenter extends BasePresenter<View> {
        void logout();
        void setNightMode(boolean isNightMode);
        boolean isNightMode();
    }
}
