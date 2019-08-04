package com.sunshinexu.mobilelearn.activity.fragment.knowledge.ui;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.presenter.KnowledgePresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> {

    public static KnowledgeFragment newInstance(){
        KnowledgeFragment knowledgeFragment = new KnowledgeFragment();
        return knowledgeFragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
