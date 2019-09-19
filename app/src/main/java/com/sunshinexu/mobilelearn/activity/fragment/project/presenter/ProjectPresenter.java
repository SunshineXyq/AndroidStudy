package com.sunshinexu.mobilelearn.activity.fragment.project.presenter;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.project.bean.ProjectData;
import com.sunshinexu.mobilelearn.activity.fragment.project.contract.ProjectContract;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.presenter.Presenter;
import com.sunshinexu.mobilelearn.core.rxjava.BaseObserver;
import com.sunshinexu.mobilelearn.utils.RxJavaUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * 项目相关调用
 */
public class ProjectPresenter extends Presenter<ProjectContract.View> implements ProjectContract.Presenter {
    @Inject
    public ProjectPresenter() {
    }

    @Override
    public void getProjectData() {
        addSubscribe(dataManager.getProjectData()
                .compose(RxJavaUtil.SchedulerTransformer())
                .filter(projectTreeDataList -> mView != null)
                .subscribeWith(new BaseObserver<List<ProjectData>>(mView,
                        MobileLearnApp.getContext().getString(R.string.failed_to_get_project), true) {
                    @Override
                    public void success(List<ProjectData> projectData) {
                        mView.showProjectData(projectData);
                    }
                }));
    }

    @Override
    public void reload() {
        getProjectData();
    }
}
