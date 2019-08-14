package com.sunshinexu.mobilelearn.activity.fragment.knowledge.presenter;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.bean.KnowledgeSystemData;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.contract.KnowledgeContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import java.util.List;

import javax.inject.Inject;

public class KnowledgePresenter extends Presenter<KnowledgeContract.View> implements KnowledgeContract.Presenter {

    @Inject
    public KnowledgePresenter() {
    }

    @Override
    public void getKnowledgeSystemData() {
        addSubscribe(dataManager.getKnowledgeData()
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(listBaseResponse -> mView != null)
                .subscribeWith(new BaseObserver<List<KnowledgeSystemData>>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_knowledge),true) {

                    @Override
                    public void success(List<KnowledgeSystemData> knowledgeSystemData) {
                        mView.showKnowledgeSystemData(knowledgeSystemData);
                    }
                }));
    }

    @Override
    public void reload() {
        getKnowledgeSystemData();
    }
}
