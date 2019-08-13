package com.sunshinexu.mobilelearn.activity.fragment.knowledge.contract;

import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.CollectContract;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.core.eventbus.CollectEvent;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

public interface KnowledgeArticleContract {
    interface View extends CollectContract.View {
        void showKnowledgeArticleData(ArticleListData articleListData, boolean isRefresh);
    }

    interface Presenter extends CollectContract.Presenter<View> {
        void refreshLayout(int cid, boolean isShowStatusView);

        void getKnowledgeListData(boolean isShowStatusView);

        void loadMore();
    }
}
