package com.sunshinexu.mobilelearn.activity.fragment.publicnum.contract;

import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.CollectContract;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

public interface PersonalPublicNumContract {
    interface View extends CollectContract.View {
        void showPersonalPublicNum(ArticleListData articleListData, boolean isRefresh);
    }

    interface Presenter extends BasePresenter<View> {
        void getPersonalPublicNum(boolean viewStatus);

        void loadMore();

        void refreshLayout(int id, boolean viewStatus);

    }
}
