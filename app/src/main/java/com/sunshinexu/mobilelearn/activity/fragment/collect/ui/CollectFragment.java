package com.sunshinexu.mobilelearn.activity.fragment.collect.ui;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/19
 */

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.collect.contract.CollectListContract;
import com.sunshinexu.mobilelearn.activity.fragment.collect.presenter.CollectListPresenter;
import com.sunshinexu.mobilelearn.activity.main.ui.ArticleDetailActivity;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CollectFragment extends BaseFragment<CollectListPresenter> implements CollectListContract.View {


    @BindView(R.id.srl_collect_list)
    SmartRefreshLayout srl_collect_list;
    @BindView(R.id.rv_collect_list)
    RecyclerView rv_collect_list;
    private List<ArticleItemData> mCollectList;
    private CollectListAdapter mAdapter;

    public static CollectFragment newInstance(){
        return new CollectFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect_list;
    }

    @Override
    protected void initView() {
        mCollectList = new ArrayList<>();
        mAdapter = new CollectListAdapter(R.layout.item_article, mCollectList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetail(position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> collectArticle(position));

        DividerItemDecoration divider = new DividerItemDecoration(_mActivity,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(_mActivity,R.drawable.shape_hp_rv_line));
        rv_collect_list.addItemDecoration(divider);

        rv_collect_list.setLayoutManager(new LinearLayoutManager(_mActivity));
        rv_collect_list.setHasFixedSize(true);

        rv_collect_list.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        srl_collect_list.setOnRefreshListener(refreshLayout -> {
            presenter.getCollectArticle(false);
            refreshLayout.finishRefresh();
        });
        srl_collect_list.setOnLoadMoreListener(refreshLayout -> {
            presenter.loadMore();
            refreshLayout.finishLoadMore();
        });
        presenter.getCollectArticle(true);
    }

    private void startArticleDetail(int position){
        Intent intent = new Intent(_mActivity, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_LINK,mAdapter.getData().get(position).getLink());
        intent.putExtra(Constants.ARTICLE_TITLE,mAdapter.getData().get(position).getTitle());
        intent.putExtra(Constants.ARTICLE_ID,mAdapter.getData().get(position).getOriginId());
        intent.putExtra(Constants.IS_COLLECTED,true);
        intent.putExtra(Constants.IS_SHOW_COLLECT_ICON,true);
        intent.putExtra(Constants.ARTICLE_ITEM_POSITION,position);
        intent.putExtra(Constants.EVENT_BUS_TAG,"collectList");
        startActivity(intent);
    }

    private void collectArticle(int position){
        presenter.cancelCollectInCollectPage(position, mAdapter.getData().get(position).getId(),
                mAdapter.getData().get(position).getOriginId());
    }


    @Override
    public void showCollectList(ArticleListData articleListData, boolean isRefresh) {
        if (isRefresh) {
            mAdapter.replaceData(articleListData.getDatas());
        } else {
            mAdapter.addData(articleListData.getDatas());
        }
    }

    @Override
    public void showCollectSuccess(int position) {
        Toast.makeText(_mActivity, getString(R.string.collect_success),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCancelCollect(int position) {
        mAdapter.remove(position);
        Toast.makeText(_mActivity, getString(R.string.cancel_collect),Toast.LENGTH_SHORT).show();
    }
}
