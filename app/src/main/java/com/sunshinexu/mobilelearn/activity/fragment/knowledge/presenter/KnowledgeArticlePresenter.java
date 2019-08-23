package com.sunshinexu.mobilelearn.activity.fragment.knowledge.presenter;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.contract.KnowledgeArticleContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.core.eventbus.CollectEvent;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import javax.inject.Inject;

public class KnowledgeArticlePresenter extends CollectPresenter<KnowledgeArticleContract.View> implements KnowledgeArticleContract.Presenter {

    private int currentPage = 0;
    private boolean isRefresh = true;
    private int articleId;
    @Inject
    public KnowledgeArticlePresenter() {
    }

    @Override
    public void refreshLayout(int cid, boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 0;
        this.articleId = cid;
        getKnowledgeListData(isShowStatusView);
    }

    @Override
    public void getKnowledgeListData(boolean isShowStatusView) {
        addSubscribe(dataManager.getArticleList(articleId)
                .compose(RxJavaUtil.SchedulerTransformer())
        .filter(articleListDataBaseResponse -> mView != null)
        .subscribeWith(new BaseObserver<ArticleListData>(mView,
                MobileLearnApp.getContext().getString(R.string.failed_to_get_article),isShowStatusView) {
            @Override
            public void success(ArticleListData articleListData) {
                if(isShowStatusView && currentPage == 0 &&
                        articleListData.getDatas().size() < 1) {
                    mView.showEmpty();
                } else {
                    mView.showKnowledgeArticleData(articleListData, isRefresh);
                }
            }
        }));
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getKnowledgeListData(false);
    }
    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void reload() {
        refreshLayout(articleId,true);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscriber(tag = Constants.KNOWLEDGE_PAGER)
    public void collectEvent(CollectEvent collectEvent) {
        if (mView == null) return;
        if (collectEvent.isCancel()) {
            mView.showCancelCollect(collectEvent.getArticlePosition());
        } else {
            mView.showCollectSuccess(collectEvent.getArticlePosition());
        }
    }
}
