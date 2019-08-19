package com.sunshinexu.mobilelearn.activity.main.ui;

import com.sunshinexu.mobilelearn.activity.main.bean.SearchData;
import com.sunshinexu.mobilelearn.activity.main.contract.SearchContract;
import com.sunshinexu.mobilelearn.activity.main.presenter.SearchPresenter;
import com.sunshinexu.mobilelearn.base.activity.BaseActivity;

import java.util.List;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {
    @Override
    public void showTopSearchData(List<SearchData> usefulSiteData) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEventAndData() {

    }
}
