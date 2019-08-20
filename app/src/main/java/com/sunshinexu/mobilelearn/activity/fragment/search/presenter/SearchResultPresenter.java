package com.sunshinexu.mobilelearn.activity.fragment.search.presenter;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/20
 */

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.activity.fragment.search.contract.SearchResultContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.core.eventbus.CollectEvent;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import javax.inject.Inject;

public class SearchResultPresenter extends CollectPresenter<SearchResultContract.View> implements SearchResultContract.Presenter {

    private int currentPage;
    private boolean isRefresh = true;
    private String searchKey;

    @Inject
    public SearchResultPresenter() {
    }

    @Override
    public void search(String k, boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 0;
        searchKey = k;
        getSearchResultList(isShowStatusView);
    }

    @Override
    public void getSearchResultList(boolean isShowStatusView) {
        addSubscribe(dataManager.getSearchResultList(currentPage, searchKey)
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_article),
                        isShowStatusView) {
                    @Override
                    public void success(ArticleListData articleListData) {
                        mView.showSearchResultList(articleListData, isRefresh);
                    }
                }));
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getSearchResultList(false);
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscriber(tag = Constants.SEARCH_PAGER)
    public void collectEvent(CollectEvent collectEvent) {
        if (mView == null) return;
        if (collectEvent.isCancel()) {
            mView.showCancelCollect(collectEvent.getArticlePosition());
        } else {
            mView.showCollectSuccess(collectEvent.getArticlePosition());
        }
    }
}
