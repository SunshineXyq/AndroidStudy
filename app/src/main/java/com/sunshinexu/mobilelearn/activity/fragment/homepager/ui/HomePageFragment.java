package com.sunshinexu.mobilelearn.activity.fragment.homepager.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.BannerData;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.HomepageContract;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.HomepagePresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomePageFragment extends BaseFragment<HomepagePresenter> implements HomepageContract.View {

    private static final String TAG = "HomePageFragment";

    @BindView(R.id.hp_recycler_view)
    RecyclerView hpRecyclerView;
    @BindView(R.id.hp_refresh_layout)
    SmartRefreshLayout hpSmartRefreshLayout;


    public static HomePageFragment newInstance(){
        HomePageFragment homePageFragment = new HomePageFragment();
        return homePageFragment;

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        List<ArticleItemData> articleList = new ArrayList<>();
        ArticleListAdapter adapter = new ArticleListAdapter(R.layout.item_article, articleList);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                openArticleDetail(view, position);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                childOnClick(view, position);
            }
        });
        hpRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        hpRecyclerView.setHasFixedSize(true);
        LinearLayout mGroup = (LinearLayout) getLayoutInflater().inflate(R.layout.head_banner, null);
        Banner banner = mGroup.findViewById(R.id.head_banner);
        mGroup.removeView(banner);
        adapter.setHeaderView(banner);
        hpRecyclerView.setAdapter(adapter);
    }

    private void childOnClick(View view, int position) {
        switch (view.getId()) {
            case R.id.tv_article_chapterName:
                break;
            case R.id.tv_article_tag:
                break;
            case R.id.iv_article_like:
                collectArticle(position);
                break;
            default:
                break;
        }
    }

    /**
     * 收藏文章
     * @param position 传入点击的位置
     */
    private void collectArticle(int position) {

    }

    private void openArticleDetail(View view, int position) {

    }

    @Override
    protected void initData() {
        initRefreshLayout();
        presenter.refreshLayout(true);
    }

    private void initRefreshLayout() {
        //下拉刷新
        hpSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.refreshLayout(true);
            }
        });
        //上拉加载更多
        hpSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.loadMore();
                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    public void showList(ArticleListData listData, boolean isRefresh) {
        System.out.println(listData.toString());
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
