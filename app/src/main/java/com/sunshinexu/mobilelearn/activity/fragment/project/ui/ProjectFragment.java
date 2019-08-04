package com.sunshinexu.mobilelearn.activity.fragment.project.ui;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.project.bean.ProjectData;
import com.sunshinexu.mobilelearn.activity.fragment.project.contract.ProjectContract;
import com.sunshinexu.mobilelearn.activity.fragment.project.presenter.ProjectPresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

import java.util.List;

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    public static ProjectFragment newInstance(){
        return new ProjectFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @Override
    public void showProjectData(List<ProjectData> projectData) {

    }
}
