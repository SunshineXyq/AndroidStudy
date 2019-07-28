package com.sunshinexu.mobilelearn.activity.fragment.homepager.contract;

import com.sunshinexu.mobilelearn.base.presenter.IPresenter;
import com.sunshinexu.mobilelearn.base.view.IView;

public interface CollectContract {
    interface View extends IView {
        void showCollectSuccess(int position);

        void showCancelCollect(int position);
    }

    interface Presenter<V extends View> extends IPresenter<V> {
        void addCollectArticle(int position, int id);

        void cancelAddCollectArticle(int position, int id);
    }

}
