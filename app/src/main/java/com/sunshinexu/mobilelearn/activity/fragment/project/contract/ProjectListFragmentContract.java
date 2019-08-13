package com.sunshinexu.mobilelearn.activity.fragment.project.contract;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/13
 */

import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.CollectContract;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

public interface ProjectListFragmentContract {
    interface View extends CollectContract.View {
        void showProjectListData(ArticleListData articleListData, boolean isRefresh);
    }

    interface Presenter extends CollectContract.Presenter<View> {
        void refreshLayout(int projectId, boolean isShowStatusView);

        void getProjectListData(boolean isShowStatusView);

        void loadMore();

    }
}
