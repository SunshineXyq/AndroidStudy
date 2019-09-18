package com.sunshinexu.mobilelearn.activity.fragment.homepager.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.BannerData;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.GlideImageLoader;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.HomepageContract;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.HomepagePresenter;
import com.sunshinexu.mobilelearn.activity.main.ui.ArticleDetailActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.LoginActivity;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HomePageFragment extends BaseFragment<HomepagePresenter> implements HomepageContract.View {

    private static final String TAG = "HomePageFragment";

    @BindView(R.id.hp_recycler_view)
    RecyclerView hpRecyclerView;
    @BindView(R.id.hp_refresh_layout)
    SmartRefreshLayout hpSmartRefreshLayout;
    private Banner banner;
    private ArticleListAdapter adapter;


    public static HomePageFragment newInstance() {
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
        adapter = new ArticleListAdapter(R.layout.item_article, articleList);
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
        DividerItemDecoration divider = new DividerItemDecoration(_mActivity,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(_mActivity,R.drawable.shape_hp_rv_line));
        hpRecyclerView.addItemDecoration(divider);
        hpRecyclerView.setHasFixedSize(true);
        //添加头布局 Banner 支持轮播效果
        LinearLayout mGroup = (LinearLayout) getLayoutInflater().inflate(R.layout.head_banner, null);
        banner = mGroup.findViewById(R.id.head_banner);
        mGroup.removeView(banner);
        adapter.setHeaderView(banner);
        hpRecyclerView.setAdapter(adapter);
    }

    private void childOnClick(View view, int position) {
        switch (view.getId()) {
            case R.id.iv_article_like:
                collectArticle(position);
                break;
            default:
                break;
        }
    }

    /**
     * 收藏文章
     *
     * @param position 传入点击的位置
     */
    private void collectArticle(int position) {
        if (presenter.getLoginStatus()) {
            if (adapter.getData().get(position).isCollect()) {
                presenter.cancelAddCollectArticle(position,adapter.getData().get(position).getId());
            } else {
                presenter.addCollectArticle(position,adapter.getData().get(position).getId());
            }
        } else {
            Intent intent = new Intent(_mActivity, LoginActivity.class);
            startActivity(intent);
        }

    }

    /**
     * 打开文章详情
     * @param view
     * @param position
     */
    private void openArticleDetail(View view, int position) {
        Intent intent = new Intent(_mActivity, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_LINK,adapter.getData().get(position).getLink());
        intent.putExtra(Constants.ARTICLE_TITLE,adapter.getData().get(position).getTitle());
        intent.putExtra(Constants.ARTICLE_ID,adapter.getData().get(position).getId());
        intent.putExtra(Constants.IS_COLLECTED,adapter.getData().get(position).isCollect());
        intent.putExtra(Constants.IS_SHOW_COLLECT_ICON,true);
        intent.putExtra(Constants.ARTICLE_ITEM_POSITION,position);
        intent.putExtra(Constants.EVENT_BUS_TAG,"homepage");
        startActivity(intent);
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
                presenter.refreshLayout(false);
                refreshLayout.finishRefresh();
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

    /**
     * 展示文章列表
     * @param listData  文章列表 list
     * @param isRefresh 是否刷新
     */

    @Override
    public void showList(ArticleListData listData, boolean isRefresh) {
        if (isRefresh) {
            adapter.replaceData(listData.getDatas());
        } else {
            adapter.addData(listData.getDatas());
        }
    }

    /**
     * 展示轮播图
     * @param bannerDataList 轮播图 list
     */

    @Override
    public void showBannerData(List<BannerData> bannerDataList) {
        ArrayList<String> imageList = new ArrayList<>();
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> urlList = new ArrayList<>();
        ArrayList<Integer> articleIdList = new ArrayList<>();
        for (BannerData bannerData : bannerDataList) {
            imageList.add(bannerData.getImagePath());
            titleList.add(bannerData.getTitle());
            urlList.add(bannerData.getUrl());
            articleIdList.add(bannerData.getId());
        }
        //显示圆形指示器和标题
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageList);
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        banner.setBannerTitles(titleList);
        banner.isAutoPlay(true);
        banner.setDelayTime(2800);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(_mActivity, ArticleDetailActivity.class);
                intent.putExtra(Constants.ARTICLE_LINK,urlList.get(position));
                intent.putExtra(Constants.ARTICLE_TITLE,titleList.get(position));
                intent.putExtra(Constants.ARTICLE_ID,articleIdList.get(position));
                intent.putExtra(Constants.IS_COLLECTED,false);
                intent.putExtra(Constants.IS_SHOW_COLLECT_ICON,false);
                intent.putExtra(Constants.ARTICLE_ITEM_POSITION,-1);
                intent.putExtra(Constants.EVENT_BUS_TAG,Constants.TAG_DEFAULT);
                startActivity(intent);
            }
        });
    }

    /**
     * 收藏成功
     * @param position 收藏位置
     */
    @Override
    public void showCollectSuccess(int position) {
        adapter.getData().get(position).setCollect(true);
        adapter.setData(position,adapter.getData().get(position));
        Toast.makeText(_mActivity,R.string.collect_success,Toast.LENGTH_SHORT).show();
    }

    /**
     * 取消收藏
     * @param position 取消位置
     */

    @Override
    public void showCancelCollect(int position) {
        adapter.getData().get(position).setCollect(false);
        adapter.setData(position,adapter.getData().get(position));
        Toast.makeText(_mActivity,R.string.cancel_collect,Toast.LENGTH_SHORT).show();
    }
}
