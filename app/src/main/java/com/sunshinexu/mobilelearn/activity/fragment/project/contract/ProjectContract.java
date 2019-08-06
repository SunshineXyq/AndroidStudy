package com.sunshinexu.mobilelearn.activity.fragment.project.contract;

import com.sunshinexu.mobilelearn.activity.fragment.project.bean.ProjectData;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;

import java.util.List;

public interface ProjectContract {
    interface View extends BaseView {
        void showProjectData(List<ProjectData> projectData);
    }

    interface Presenter extends BasePresenter<View> {
        void getProjectData();
    }
}
