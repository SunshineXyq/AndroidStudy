package com.sunshinexu.mobilelearn.activity.fragment.publicnum.contract;

import com.sunshinexu.mobilelearn.activity.fragment.publicnum.bean.PublicNumData;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;

import java.util.List;

public interface PublicNumContract {
    interface View extends BaseView {
        void showPublicNumData(List<PublicNumData> publicNumData);
    }
    interface Presenter extends BasePresenter<View> {
        void getPublicNumData();
    }
}
