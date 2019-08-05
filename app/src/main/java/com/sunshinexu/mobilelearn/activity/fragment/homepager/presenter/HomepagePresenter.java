package com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter;


import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.HomepageContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;


import javax.inject.Inject;

import io.reactivex.Observable;

public class HomepagePresenter extends CollectPresenter<HomepageContract.View>
        implements HomepageContract.Presenter {

    private int currentPage;
    private boolean isRefresh;

    @Inject
    HomepagePresenter() {

    }

    @Override
    public void getArticleData(boolean isShowStatusView) {
        addSubscribe(dataManager.getArticleList(currentPage)
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView, MobileLearnApp
                        .getContext().getString(R.string.failed_to_get_article), isShowStatusView) {
                    @Override
                    public void success(ArticleListData articleListData) {
                        mView.showList(articleListData, isRefresh);
                    }
                }));
    }

    /**
     * zip() 一个函数组合多个 Observable 发射的数据集合，可将多个请求结合
     * lambda 接收两个参数
     *
     * @param isShowStatusView
     */

    @Override
    public void getBannerData(boolean isShowStatusView) {
        addSubscribe(Observable.zip(dataManager.getUpArticles(), dataManager.getArticleList(0),
                (upArticlesResponse, articleListResponse) -> {
                    articleListResponse.getData().getDatas().addAll(0,
                            upArticlesResponse.getData());
                    return articleListResponse;
                })
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView, MobileLearnApp
                        .getContext().getString(R.string.failed_to_get_article), isShowStatusView) {

                    @Override
                    public void success(ArticleListData articleListData) {
                        mView.showList(articleListData, isRefresh);
                    }
                }));
    }

    @Override
    public void getHomepageData(boolean isShowStatusView) {
        getBannerData(isShowStatusView);
    }

    @Override
    public void refreshLayout(boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 0;
        getHomepageData(true);
    }

    @Override
    public void loadMore() {
        isRefresh = true;
        currentPage++;
        getArticleData(false);
    }
}
