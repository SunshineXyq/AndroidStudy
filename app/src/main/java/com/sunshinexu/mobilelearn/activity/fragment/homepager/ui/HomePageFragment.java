package com.sunshinexu.mobilelearn.activity.fragment.homepager.ui;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.BannerData;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.HomepageContract;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.HomepagePresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends BaseFragment<HomepagePresenter> implements HomepageContract.View {

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        ArrayList<ArticleItemData> list = new ArrayList<>();
      //  ArticleListAdapter adapter = new ArticleListAdapter(R.layout.item_article,list);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showList(ArticleListData listData, boolean isRefresh) {

    }

    @Override
    public void showBannerData(List<BannerData> bannerDataList) {

    }

    @Override
    public void showCollectSuccess(int position) {

    }

    @Override
    public void showCancelCollect(int position) {

    }
}
