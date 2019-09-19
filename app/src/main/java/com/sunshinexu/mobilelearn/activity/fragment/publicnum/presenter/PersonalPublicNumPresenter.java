package com.sunshinexu.mobilelearn.activity.fragment.publicnum.presenter;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.contract.PersonalPublicNumContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.eventbus.CollectEvent;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import javax.inject.Inject;

/**
 * 个人公众号相关调用
 */
public class PersonalPublicNumPresenter extends CollectPresenter<PersonalPublicNumContract.View>
        implements PersonalPublicNumContract.Presenter {

    private int currentPage = 1;
    private boolean isRefresh = true;
    private int id;

    @Inject
    public PersonalPublicNumPresenter() {
    }


    @Override
    public void getPersonalPublicNum(boolean viewStatus) {
        addSubscribe(dataManager.getPersonalPublicNumData(id, currentPage)
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(articleListDataBaseResponse -> mView != null)
                .subscribeWith(new BaseObserver<ArticleListData>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_personal_public_num),viewStatus) {
                    @Override
                    public void success(ArticleListData listData) {
                        if(viewStatus && currentPage == 1 &&
                                listData.getDatas().size() < 1) {
                            mView.showEmpty();
                        } else {
                            mView.showPersonalPublicNum(listData, isRefresh);
                        }
                    }
                }));
    }

    /**
     * 加载更多
     */
    @Override
    public void loadMore() {
        currentPage++;
        isRefresh = false;
        getPersonalPublicNum(false);
    }

    /**
     * 刷新列表
     * @param id id
     * @param viewStatus 是否刷新
     */
    @Override
    public void refreshLayout(int id, boolean viewStatus) {
        currentPage = 1;
        isRefresh = true;
        this.id = id;
        getPersonalPublicNum(viewStatus);
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscriber(tag = "publicNum")
    public void getData(CollectEvent collectEvent){
        if (mView == null) {
            return;
        }
        if (collectEvent.isCancel()) {
            mView.showCancelCollect(collectEvent.getArticlePosition());
        } else {
            mView.showCollectSuccess(collectEvent.getArticlePosition());
        }
    }
}
