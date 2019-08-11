package com.sunshinexu.mobilelearn.activity.fragment.publicnum.presenter;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.contract.PersonalPublicNumContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import javax.inject.Inject;

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
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_personal_public_num)) {
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

    @Override
    public void loadMore() {

    }

    @Override
    public void refreshLayout(int id, boolean viewStatus) {
        currentPage = 1;
        isRefresh = true;
        this.id = id;
        getPersonalPublicNum(viewStatus);
    }
}
