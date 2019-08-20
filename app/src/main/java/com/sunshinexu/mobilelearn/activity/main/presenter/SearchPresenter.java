package com.sunshinexu.mobilelearn.activity.main.presenter;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.main.bean.HotSearchData;
import com.sunshinexu.mobilelearn.activity.main.contract.SearchContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import java.util.List;

import javax.inject.Inject;

public class SearchPresenter extends Presenter<SearchContract.View> implements SearchContract.Presenter {

    @Inject
    public SearchPresenter() {
    }

    @Override
    public void getSearchData() {
        addSubscribe(dataManager.getSearchData()
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<List<HotSearchData>>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_hot_data),
                        false) {
                    @Override
                    public void success(List<HotSearchData> hotSearchData) {
                        mView.showTopSearchData(hotSearchData);
                    }
                }));
    }

    @Override
    public void addHistoryData(String data) {

    }

    @Override
    public void clearAllHistoryData() {

    }

    @Override
    public void deleteHistoryDataById(Long id) {

    }

    @Override
    public void loadAllHistoryData() {

    }
}
