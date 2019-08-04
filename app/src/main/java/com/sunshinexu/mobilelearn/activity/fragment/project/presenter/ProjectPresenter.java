package com.sunshinexu.mobilelearn.activity.fragment.project.presenter;

import com.sunshinexu.mobilelearn.activity.fragment.project.contract.ProjectContract;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;

import javax.inject.Inject;

public class ProjectPresenter extends BasePresenter<ProjectContract.View> implements ProjectContract.Presenter {
    @Inject
    public ProjectPresenter() {
    }

    @Override
    public void getProjectData() {

    }
}
