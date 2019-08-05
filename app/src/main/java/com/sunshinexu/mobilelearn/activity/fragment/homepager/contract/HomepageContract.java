package com.sunshinexu.mobilelearn.activity.fragment.homepager.contract;

import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.BannerData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import java.util.List;

public interface HomepageContract {
    interface View extends CollectContract.View {
        void showList(ArticleListData listData, boolean isRefresh);

        void showBannerData(List<BannerData> bannerDataList);
    }

    interface Presenter extends CollectContract.Presenter<View> {
        void getArticleData(boolean isShowStatusView);

        void getBannerData(boolean isShowStatusView);

        void getHomepageData(boolean isShowStatusView);

        void refreshLayout(boolean isShowStatusView);

        void loadMore();
    }
}
