package com.sunshinexu.mobilelearn.activity.fragment.collect.contract;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/19
 */

import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.CollectContract;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

public interface CollectListContract {
    interface View extends CollectContract.View {
        void showCollectList(ArticleListData articleListData, boolean isRefresh);

    }
    interface Presenter extends CollectContract.Presenter<View> {
        void getCollectArticle(boolean isShowStatusView);
        void loadMore();
        void getCollectList(boolean isShowStatusView);
        void cancelCollectInCollectPage(int position, int id, int originId);
    }

}
