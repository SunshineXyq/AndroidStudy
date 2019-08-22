package com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter;


import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.BannerData;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.HomepageContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.core.eventbus.LoginEvent;
import com.sunshinexu.mobilelearn.core.eventbus.LogoutEvent;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;


import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

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
     * 获取首页的 Banner 数据
     * @param isShowStatusView
     */

    @Override
    public void getBannerData(boolean isShowStatusView) {
        addSubscribe(dataManager.getBannerData()
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(articleBannerData -> mView != null)
                .subscribeWith(new BaseObserver<List<BannerData>>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_banner),
                        isShowStatusView) {
                    @Override
                    public void success(List<BannerData> bannerData) {
                        mView.showBannerData(bannerData);
                    }
                }));
    }

    /**
     * zip() 一个函数组合多个 Observable 发射的数据集合，可将多个请求结合
     * lambda 接收两个参数
     * subscribeWith  一般使用 Rx 请求接口的这种情况会用
     * @param isShowStatusView
     */
    @Override
    public void getHomepageData(boolean isShowStatusView) {
        getBannerData(isShowStatusView);
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
    public void refreshLayout(boolean isShowStatusView) {
        isRefresh = true;
        currentPage = 0;
        getHomepageData(isShowStatusView);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        currentPage++;
        getArticleData(false);
    }

    /**
     * 注销成功后刷新数据
     * @param logoutEvent
     */
    @Subscriber()
    public void logoutSuccessEvent(LogoutEvent logoutEvent) {
        getHomepageData(false);
    }

    /**
     * 登录成功后刷新数据
     * @param loginEvent
     */
    @Subscriber()
    public void loginSuccessEvent(LoginEvent loginEvent) {
        getHomepageData(false);
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }
}
