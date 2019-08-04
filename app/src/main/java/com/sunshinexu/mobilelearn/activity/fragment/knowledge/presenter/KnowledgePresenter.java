package com.sunshinexu.mobilelearn.activity.fragment.knowledge.presenter;

import com.sunshinexu.mobilelearn.activity.fragment.knowledge.contract.KnowledgeContract;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;

import javax.inject.Inject;

public class KnowledgePresenter extends BasePresenter<KnowledgeContract.View> implements KnowledgeContract.Presenter {

    @Inject
    public KnowledgePresenter() {
    }

    @Override
    public void getKnowledgeSystemData() {

    }
}
