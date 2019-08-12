package com.sunshinexu.mobilelearn.activity.fragment.knowledge.ui;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.contract.KnowledgeArticleContract;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.presenter.KnowledgeArticlePresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

import butterknife.BindView;

public class KnowledgeArticleFragment extends BaseFragment<KnowledgeArticlePresenter> implements KnowledgeArticleContract.View {

    @BindView(R.id.srl_knowledge_article)
    SmartRefreshLayout srl_knowledge_article;
    @BindView(R.id.rv_knowledge_article)
    SmartRefreshLayout rv_knowledge_article;

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

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showCollectSuccess(int position) {

    }

    @Override
    public void showCancelCollect(int position) {

    }
}
