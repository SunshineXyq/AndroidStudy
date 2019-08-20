package com.sunshinexu.mobilelearn.activity.fragment.search.contract;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/20
 */

import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.CollectContract;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

public interface SearchResultContract {

    interface View extends CollectContract.View {
        void showSearchResultList(ArticleListData articleListData, boolean isRefresh);
    }

    interface Presenter extends CollectContract.Presenter<View> {
        void search(String k, boolean isShowStatusView);
        void getSearchResultList( boolean isShowStatusView);
        void loadMore();
    }

}
