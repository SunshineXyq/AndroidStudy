package com.sunshinexu.mobilelearn.modules.main.contract;

import android.view.View;

import com.sunshinexu.mobilelearn.base.presenter.IPresenter;
import com.sunshinexu.mobilelearn.base.view.IView;

public interface MainContract {
    interface View extends IView{

    }

    //传递的是上个接口的 View
    interface Presenter extends IPresenter<View>{
        void logout();
        void setNightMode(boolean isNightMode);
        boolean isNightMode();
    }
}
