package com.sunshinexu.mobilelearn.activity.fragment.knowledge.ui;

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
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.contract.KnowledgeArticleContract;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.presenter.KnowledgeArticlePresenter;
import com.sunshinexu.mobilelearn.activity.main.ui.ArticleDetailActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.LoginActivity;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import java.util.ArrayList;

import butterknife.BindView;

public class KnowledgeArticleFragment extends BaseFragment<KnowledgeArticlePresenter> implements KnowledgeArticleContract.View {

    @BindView(R.id.srl_knowledge_article)
    SmartRefreshLayout srl_knowledge_article;
    @BindView(R.id.rv_knowledge_article)
    RecyclerView rv_knowledge_article;
    private ArticleListAdapter adapter;

    public static KnowledgeArticleFragment newInstance(Bundle bundle) {
        KnowledgeArticleFragment knowledgeArticleFragment = new KnowledgeArticleFragment();
        knowledgeArticleFragment.setArguments(bundle);
        return knowledgeArticleFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_article;
    }

    @Override
    protected void initView() {
        ArrayList<ArticleItemData> articleDataList = new ArrayList<>();
        adapter = new ArticleListAdapter(R.layout.item_article, articleDataList);
        adapter.setOnItemClickListener((adapter1, view, position) -> openArticleDetail(view, position));
        adapter.setOnItemChildClickListener((adapter1, view, position) -> childClick(view, position));
        DividerItemDecoration divider = new DividerItemDecoration(_mActivity,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(_mActivity,R.drawable.shape_hp_rv_line));
        rv_knowledge_article.addItemDecoration(divider);
        rv_knowledge_article.setLayoutManager(new LinearLayoutManager(_mActivity));
        rv_knowledge_article.setHasFixedSize(true);
        rv_knowledge_article.setAdapter(adapter);
    }

    private void openArticleDetail(View view, int position) {
        Intent intent = new Intent(_mActivity, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_LINK, adapter.getData().get(position).getLink());
        intent.putExtra(Constants.ARTICLE_TITLE, adapter.getData().get(position).getTitle());
        intent.putExtra(Constants.ARTICLE_ID, adapter.getData().get(position).getId());
        intent.putExtra(Constants.IS_COLLECTED, adapter.getData().get(position).isCollect());
        intent.putExtra(Constants.IS_SHOW_COLLECT_ICON, true);
        intent.putExtra(Constants.ARTICLE_ITEM_POSITION, position);
        intent.putExtra(Constants.EVENT_BUS_TAG, "knowledge");
        startActivity(intent);
    }

    private void childClick(View view, int position) {
        if (presenter.getLoginStatus()) {
            if (adapter.getData().get(position).isCollect()) {
                presenter.cancelAddCollectArticle(position, adapter.getData().get(position).getId());
            } else {
                presenter.addCollectArticle(position, adapter.getData().get(position).getId());
            }
        } else {
            Intent intent = new Intent(_mActivity, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(_mActivity, R.string.please_login, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {
        int articleId = getArguments().getInt("KnowledgeDataId");
        presenter.refreshLayout(articleId,true);
        srl_knowledge_article.setOnRefreshListener(refreshLayout -> {
            presenter.refreshLayout(articleId,false);
            srl_knowledge_article.finishRefresh();
        });
        srl_knowledge_article.setOnLoadMoreListener(refreshLayout -> {
            presenter.loadMore();
            srl_knowledge_article.finishLoadMore();
        });
    }

    @Override
    public void showCollectSuccess(int position) {
        adapter.getData().get(position).setCollect(true);
        adapter.setData(position, adapter.getData().get(position));
        Toast.makeText(_mActivity, getString(R.string.collect_success),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCancelCollect(int position) {
        adapter.getData().get(position).setCollect(false);
        adapter.setData(position, adapter.getData().get(position));
        Toast.makeText(_mActivity, getString(R.string.cancel_collect),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showKnowledgeArticleData(ArticleListData articleListData, boolean isRefresh) {
        if (isRefresh) {
            adapter.replaceData(articleListData.getDatas());
        } else {
            adapter.addData(articleListData.getDatas());
        }
    }
}
