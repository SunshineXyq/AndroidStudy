package com.sunshinexu.mobilelearn.activity.fragment.publicnum.presenter;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.bean.PublicNumData;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.contract.PublicNumContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import java.util.List;

import javax.inject.Inject;

public class PublicNumPresenter extends Presenter<PublicNumContract.View> implements PublicNumContract.Presenter {
    @Inject
    public PublicNumPresenter() {
    }

    @Override
    public void getPublicNumData() {
        addSubscribe(dataManager.getPublicNumData()
        .compose(RxJavaUtil.SchedulerTransformer())
        .filter(publicNumList -> mView != null)
        .subscribeWith(new BaseObserver<List<PublicNumData>>(mView, MobileLearnApp.getContext().getString(R.string.failed_to_get_public_num)) {
            @Override
            public void success(List<PublicNumData> publicNumData) {
                mView.showPublicNumData(publicNumData);
            }
        }));
    }
}
