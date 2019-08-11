package com.sunshinexu.mobilelearn.activity.main.presenter;

import com.sunshinexu.mobilelearn.activity.main.contract.KnowledgeDetailContract;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;

import javax.inject.Inject;

public class KnowledgeDetailPresenter extends Presenter<KnowledgeDetailContract.View>
        implements KnowledgeDetailContract.Presenter {

    @Inject
    public KnowledgeDetailPresenter() {
    }
}
