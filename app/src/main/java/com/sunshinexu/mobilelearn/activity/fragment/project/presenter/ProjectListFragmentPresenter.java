package com.sunshinexu.mobilelearn.activity.fragment.project.presenter;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/13
 */

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.activity.fragment.project.contract.ProjectListFragmentContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.core.eventbus.CollectEvent;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import javax.inject.Inject;

/**
 * 项目列表相关调用
 */
public class ProjectListFragmentPresenter extends CollectPresenter<ProjectListFragmentContract.View> implements ProjectListFragmentContract.Presenter {

    private int currentPage = 1;
    private boolean isRefresh = true;
    private int projectId;

    @Inject
    public ProjectListFragmentPresenter() {
    }

    @Override
    public void refreshLayout(int projectId, boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 1;
        this.projectId = projectId;
        getProjectListData(isShowStatusView);
    }

    @Override
    public void getProjectListData(boolean isShowStatusView) {
        addSubscribe(dataManager.getProjectListData(currentPage, projectId)
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_project),isShowStatusView) {
                    @Override
                    public void success(ArticleListData articleListData) {
                        if(isShowStatusView && currentPage == 1 &&
                                articleListData.getDatas().size() < 1) {
                            mView.showEmpty();
                        } else {
                            mView.showProjectListData(articleListData, isRefresh);
                        }
                    }
                }));
    }

    @Override
    public void reload() {
        refreshLayout(projectId, true);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getProjectListData(false);
    }
    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscriber(tag = "project")
    public void collectEvent(CollectEvent collectEvent) {
        if (mView == null) return;
        if (collectEvent.isCancel()) {
            mView.showCancelCollect(collectEvent.getArticlePosition());
        } else {
            mView.showCollectSuccess(collectEvent.getArticlePosition());
        }
    }
}
