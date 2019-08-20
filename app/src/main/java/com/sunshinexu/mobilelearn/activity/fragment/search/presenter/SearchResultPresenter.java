package com.sunshinexu.mobilelearn.activity.fragment.search.presenter;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/20
 */

import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.activity.fragment.search.contract.SearchResultContract;

import javax.inject.Inject;

public class SearchResultPresenter extends CollectPresenter<SearchResultContract.View> implements SearchResultContract.Presenter {

    @Inject
    public SearchResultPresenter() {
    }

    @Override
    public void search(String k, boolean isShowStatusView) {

    }

    @Override
    public void getSearchResultList(boolean isShowStatusView) {

    }

    @Override
    public void loadMore() {

    }
}
