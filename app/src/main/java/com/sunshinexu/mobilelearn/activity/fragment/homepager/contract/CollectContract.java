package com.sunshinexu.mobilelearn.activity.fragment.homepager.contract;

import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;

public interface CollectContract {
    interface View extends BaseView {
        void showCollectSuccess(int position);

        void showCancelCollect(int position);
    }

    interface Presenter<V extends View> extends BasePresenter<V> {
        void addCollectArticle(int position, int id);

        void cancelAddCollectArticle(int position, int id);
    }

}
