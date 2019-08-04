package com.sunshinexu.mobilelearn.activity.fragment.publicnum.contract;

import com.sunshinexu.mobilelearn.activity.fragment.publicnum.bean.PublicNumData;
import com.sunshinexu.mobilelearn.base.presenter.IPresenter;
import com.sunshinexu.mobilelearn.base.view.IView;

import java.util.List;

public interface PublicNumContract {
    interface View extends IView{
        void showPublicNumData(List<PublicNumData> publicNumData);
    }
    interface Presenter extends IPresenter<View>{
        void getPublicNumData();
    }
}
