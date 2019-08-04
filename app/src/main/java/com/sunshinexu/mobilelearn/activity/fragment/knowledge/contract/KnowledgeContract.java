package com.sunshinexu.mobilelearn.activity.fragment.knowledge.contract;

import com.sunshinexu.mobilelearn.activity.fragment.knowledge.bean.KnowledgeSystemData;
import com.sunshinexu.mobilelearn.base.presenter.IPresenter;
import com.sunshinexu.mobilelearn.base.view.IView;

import java.util.List;

public interface KnowledgeContract {
    interface View extends IView{
        void showKnowledgeSystemData(List<KnowledgeSystemData> knowledgeSystems);
    }
    interface Presenter extends IPresenter<View>{
        void getKnowledgeSystemData();
    }
}
