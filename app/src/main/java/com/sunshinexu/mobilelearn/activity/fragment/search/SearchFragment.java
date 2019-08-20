package com.sunshinexu.mobilelearn.activity.fragment.search;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/20
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.ui.ArticleListAdapter;
import com.sunshinexu.mobilelearn.activity.fragment.search.contract.SearchResultContract;
import com.sunshinexu.mobilelearn.activity.fragment.search.presenter.SearchResultPresenter;
import com.sunshinexu.mobilelearn.activity.main.presenter.SearchPresenter;
import com.sunshinexu.mobilelearn.activity.main.ui.ArticleDetailActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.LoginActivity;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchFragment extends BaseFragment<SearchResultPresenter> implements SearchResultContract.View{

    @BindView(R.id.srl_search)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_search)
    RecyclerView mRecyclerView;
    private ArticleListAdapter mAdapter;
    private String mSearchKey = "";
    public static SearchFragment newInstance(Bundle bundle){
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setArguments(bundle);
        return searchFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        List<ArticleItemData> mArticleList = new ArrayList<>();
        mAdapter = new ArticleListAdapter(R.layout.item_article, mArticleList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> openArticleDetail(view, position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> collectClickEvent(position));
        DividerItemDecoration divider = new DividerItemDecoration(_mActivity,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(_mActivity,R.drawable.shape_hp_rv_line));
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        assert getArguments() != null;
        mSearchKey = getArguments().getString(Constants.TYPE_TITLE, "");
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            presenter.search(mSearchKey, false);
            refreshLayout.finishRefresh();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            presenter.loadMore();
            refreshLayout.finishLoadMore();
        });
        presenter.search(mSearchKey, true);
    }

    private void openArticleDetail(View view, int position) {
        Intent intent = new Intent(_mActivity, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_LINK,mAdapter.getData().get(position).getLink());
        intent.putExtra(Constants.ARTICLE_TITLE,mAdapter.getData().get(position).getTitle());
        intent.putExtra(Constants.ARTICLE_ID,mAdapter.getData().get(position).getId());
        intent.putExtra(Constants.IS_COLLECTED,mAdapter.getData().get(position).isCollect());
        intent.putExtra(Constants.IS_SHOW_COLLECT_ICON,true);
        intent.putExtra(Constants.ARTICLE_ITEM_POSITION,position);
        intent.putExtra(Constants.EVENT_BUS_TAG,Constants.SEARCH_PAGER);
        startActivity(intent);
    }

    private void collectClickEvent(int position) {
        if (presenter.getLoginStatus()) {
            if (mAdapter.getData().get(position).isCollect()) {
                presenter.cancelAddCollectArticle(position, mAdapter.getData().get(position).getId());
            } else {
                presenter.addCollectArticle(position, mAdapter.getData().get(position).getId());
            }
        } else {
            Intent intent = new Intent(_mActivity, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void showSearchResultList(ArticleListData articleListData, boolean isRefresh) {
        if (isRefresh) {
            mAdapter.replaceData(articleListData.getDatas());
        } else {
            mAdapter.addData(articleListData.getDatas());
        }
    }

    @Override
    public void showCollectSuccess(int position) {
        mAdapter.getData().get(position).setCollect(true);
        mAdapter.setData(position, mAdapter.getData().get(position));
        Toast.makeText(_mActivity, getString(R.string.collect_success),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCancelCollect(int position) {
        mAdapter.getData().get(position).setCollect(false);
        mAdapter.setData(position, mAdapter.getData().get(position));
        Toast.makeText(_mActivity, getString(R.string.cancel_collect),Toast.LENGTH_SHORT).show();
    }
}
