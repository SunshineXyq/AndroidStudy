package com.sunshinexu.mobilelearn.activity.main.presenter;

import com.sunshinexu.mobilelearn.activity.main.contract.SearchContract;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;

import javax.inject.Inject;

public class SearchPresenter extends Presenter<SearchContract.View> implements SearchContract.Presenter {

    @Inject
    public SearchPresenter() {
    }

    @Override
    public void getSearchData() {

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
