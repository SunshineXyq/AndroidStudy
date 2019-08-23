package com.sunshinexu.mobilelearn.activity.fragment.project.ui;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/13
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.project.contract.ProjectListFragmentContract;
import com.sunshinexu.mobilelearn.activity.fragment.project.presenter.ProjectListFragmentPresenter;
import com.sunshinexu.mobilelearn.activity.main.ui.ArticleDetailActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.LoginActivity;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProjectListFragment extends BaseFragment<ProjectListFragmentPresenter> implements ProjectListFragmentContract.View {

    @BindView(R.id.srl_project_list)
    SmartRefreshLayout srl_project_list;
    @BindView(R.id.rv_project_list)
    RecyclerView rv_project_list;
    private ProjectListAdapter mAdapter;

    public static ProjectListFragment newInstance(Bundle bundle) {
        ProjectListFragment projectListFragment = new ProjectListFragment();
        projectListFragment.setArguments(bundle);
        return projectListFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    protected void initView() {
        List<ArticleItemData> mArticleList = new ArrayList<>();
        mAdapter = new ProjectListAdapter(R.layout.item_project_list, mArticleList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> openArticleDetail(view, position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> clickChild(view, position));
        DividerItemDecoration divider = new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(_mActivity, R.drawable.shape_hp_rv_line));
        rv_project_list.addItemDecoration(divider);
        rv_project_list.setLayoutManager(new LinearLayoutManager(_mActivity));
        rv_project_list.setHasFixedSize(true);

        rv_project_list.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        assert getArguments() != null;
        int projectId = getArguments().getInt("projectId");
        srl_project_list.setOnRefreshListener(refreshLayout -> {
            presenter.refreshLayout(projectId, false);
            refreshLayout.finishRefresh();
        });
        srl_project_list.setOnLoadMoreListener(refreshLayout -> {
            presenter.loadMore();
            refreshLayout.finishLoadMore();
        });
        presenter.refreshLayout(projectId,true);
    }
    private void openArticleDetail(View view,int position){
        Intent intent = new Intent(_mActivity, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_LINK,mAdapter.getData().get(position).getLink());
        intent.putExtra(Constants.ARTICLE_TITLE,mAdapter.getData().get(position).getTitle());
        intent.putExtra(Constants.ARTICLE_ID,mAdapter.getData().get(position).getId());
        intent.putExtra(Constants.IS_COLLECTED,mAdapter.getData().get(position).isCollect());
        intent.putExtra(Constants.IS_SHOW_COLLECT_ICON,true);
        intent.putExtra(Constants.ARTICLE_ITEM_POSITION,position);
        intent.putExtra(Constants.EVENT_BUS_TAG,"project");
        startActivity(intent);
    }

    private void clickChild(View view,int position){
        if (presenter.getLoginStatus()) {
            if (mAdapter.getData().get(position).isCollect()) {
                presenter.cancelAddCollectArticle(position,mAdapter.getData().get(position).getId());
            } else {
                presenter.addCollectArticle(position,mAdapter.getData().get(position).getId());
            }
        } else {
            Intent intent = new Intent(_mActivity, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void showProjectListData(ArticleListData articleListData, boolean isRefresh) {
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
    }

    @Override
    public void showCancelCollect(int position) {
        mAdapter.getData().get(position).setCollect(false);
        mAdapter.setData(position, mAdapter.getData().get(position));
    }
}
