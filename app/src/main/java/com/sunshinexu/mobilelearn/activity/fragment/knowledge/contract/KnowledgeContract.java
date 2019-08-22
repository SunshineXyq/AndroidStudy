package com.sunshinexu.mobilelearn.activity.fragment.knowledge.contract;

import com.sunshinexu.mobilelearn.activity.fragment.knowledge.bean.KnowledgeSystemData;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;

import java.util.List;

public interface KnowledgeContract {
    interface View extends BaseView {
        void showKnowledgeSystemData(List<KnowledgeSystemData> knowledgeSystems);
    }
    interface Presenter extends BasePresenter<View> {
        void getKnowledgeSystemData(boolean isShowViewStatus);
    }
}
