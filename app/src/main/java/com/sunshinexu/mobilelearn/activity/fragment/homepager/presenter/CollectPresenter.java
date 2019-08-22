package com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.CollectContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

public class CollectPresenter<V extends CollectContract.View> extends Presenter<V>
        implements CollectContract.Presenter<V> {
    @Override
    public void addCollectArticle(int position, int id) {
        addSubscribe(dataManager.addArticleCollect(id)
                .compose(RxJavaUtil.SchedulerTransformer())   //调度器
                .filter(articleListData -> mView != null)     //添加过滤
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        MobileLearnApp.getContext().getString(R.string.collect_failed),
                        false) {

                    @Override
                    public void success(ArticleListData articleListData) {
                        mView.showCollectSuccess(position);
                    }
                }));
    }

    @Override
    public void cancelAddCollectArticle(int position, int id) {
        addSubscribe(dataManager.cancelAddArticleCollect(id)
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(articleListData -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_cancel_collect)) {

                    @Override
                    public void success(ArticleListData articleListData) {
                        mView.showCancelCollect(position);
                    }
                }));
    }
}
