package com.sunshinexu.mobilelearn.activity.fragment.knowledge.contract;

import com.sunshinexu.mobilelearn.activity.fragment.homepager.contract.CollectContract;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.presenter.CollectPresenter;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.core.eventbus.CollectEvent;

public interface KnowledgeArticleContract {
    interface View extends CollectContract.View {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
