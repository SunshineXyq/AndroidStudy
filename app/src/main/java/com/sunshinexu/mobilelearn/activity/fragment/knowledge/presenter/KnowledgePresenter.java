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

/**
 * 知识体系相关调用
 */
public class KnowledgePresenter extends Presenter<KnowledgeContract.View> implements KnowledgeContract.Presenter {

    @Inject
    public KnowledgePresenter() {
    }

    @Override
    public void getKnowledgeSystemData(boolean isShowViewStatus) {
        addSubscribe(dataManager.getKnowledgeData()
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(listBaseResponse -> mView != null)
                .subscribeWith(new BaseObserver<List<KnowledgeSystemData>>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_knowledge),
                        isShowViewStatus) {
                 //知识体系数据请求成功后回调函数
                    @Override
                    public void success(List<KnowledgeSystemData> knowledgeSystemData) {
                        mView.showKnowledgeSystemData(knowledgeSystemData);
                    }
                }));
    }

    /**
     * 重新加载
     */
    @Override
    public void reload() {
        getKnowledgeSystemData(false);
    }
}
