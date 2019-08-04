package com.sunshinexu.mobilelearn.activity.fragment.project.contract;

import com.sunshinexu.mobilelearn.activity.fragment.project.bean.ProjectData;
import com.sunshinexu.mobilelearn.base.presenter.IPresenter;
import com.sunshinexu.mobilelearn.base.view.IView;

import java.util.List;

public interface ProjectContract {
    interface View extends IView{
        void showProjectData(List<ProjectData> projectData);
    }

    interface Presenter extends IPresenter<View>{
        void getProjectData();
    }
}
