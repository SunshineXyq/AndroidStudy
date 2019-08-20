package com.sunshinexu.mobilelearn.activity.fragment.search;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/20
 */

import android.os.Bundle;

import com.sunshinexu.mobilelearn.activity.fragment.search.contract.SearchResultContract;
import com.sunshinexu.mobilelearn.activity.fragment.search.presenter.SearchResultPresenter;
import com.sunshinexu.mobilelearn.activity.main.presenter.SearchPresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

public class SearchFragment extends BaseFragment<SearchResultPresenter> implements SearchResultContract.View{

    public static SearchFragment newInstance(Bundle bundle){
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(bundle);
        return searchFragment;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showSearchResultList(ArticleListData articleListData, boolean isRefresh) {

    }

    @Override
    public void showCollectSuccess(int position) {

    }

    @Override
    public void showCancelCollect(int position) {

    }
}
