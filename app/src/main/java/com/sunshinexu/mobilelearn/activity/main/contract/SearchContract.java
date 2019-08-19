package com.sunshinexu.mobilelearn.activity.main.contract;

import com.sunshinexu.mobilelearn.activity.main.bean.SearchData;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView {
        void showTopSearchData(List<SearchData> usefulSiteData);
       // void showHistoryData(List<HistoryData> historyDataList);
    }

    interface Presenter extends BasePresenter<View> {
        void getSearchData();
        void addHistoryData(String data);
        void clearAllHistoryData();
        void deleteHistoryDataById(Long id);
        void loadAllHistoryData();
    }
}
