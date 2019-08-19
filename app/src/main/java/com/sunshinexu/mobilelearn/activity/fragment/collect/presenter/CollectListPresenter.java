package com.sunshinexu.mobilelearn.activity.fragment.collect.presenter;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/19
 */

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.collect.contract.CollectListContract;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.core.eventbus.CollectEvent;
import com.sunshinexu.mobilelearn.core.eventbus.RefreshHomeEvent;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import javax.inject.Inject;

public class CollectListPresenter extends CollectPresenter<CollectListContract.View> implements CollectListContract.Presenter{
    @Inject
    public CollectListPresenter() {

    }

    private boolean isRefresh = false;
    private int currentPage = -1;
    private boolean isReCollected = false;
    @Override
    public void getCollectArticle(boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 0;
        getCollectList(isShowStatusView);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getCollectList(false);
    }

    @Override
    public void getCollectList(boolean isShowStatusView) {
        addSubscribe(dataManager.getCollectList(currentPage)
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_collect_list),
                        isShowStatusView) {
                    @Override
                    public void success(ArticleListData articleListData) {
                        mView.showCollectList(articleListData, isRefresh);
                    }
                }));
    }

    @Override
    public void cancelCollectInCollectPage(int position, int id, int originId) {
        addSubscribe(dataManager.cancelCollectInCollectPage(id, originId)
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_cancel_collect),
                        true) {
                    @Override
                    public void success(ArticleListData articleListData) {
                        mView.showCancelCollect(position);
                        EventBus.getDefault().post(new RefreshHomeEvent());
                    }
                }));
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscriber(tag = "collectList")
    public void collectEvent(CollectEvent collectEvent) {
        if (mView == null) return;
        if (collectEvent.isCancel()) {
            if (isReCollected) {
                isReCollected = false;
                mView.showCancelCollect(0);
            } else {
                mView.showCancelCollect(collectEvent.getArticlePosition());
            }
        } else {
            getCollectList(false);
            mView.showCollectSuccess(-1);
            isReCollected = true;
        }
        EventBus.getDefault().post(new RefreshHomeEvent());
    }
}
