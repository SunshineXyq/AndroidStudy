package com.sunshinexu.mobilelearn.activity.fragment.knowledge.ui;

import com.sunshinexu.mobilelearn.activity.fragment.knowledge.contract.KnowledgeArticleContract;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.presenter.KnowledgeArticlePresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

public class KnowledgeArticleFragment extends BaseFragment<KnowledgeArticlePresenter> implements KnowledgeArticleContract.View {


    @Override
    protected int getLayoutId() {
        return 0;
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
