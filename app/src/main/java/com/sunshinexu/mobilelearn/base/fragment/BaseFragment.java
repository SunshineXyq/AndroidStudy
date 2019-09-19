package com.sunshinexu.mobilelearn.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.classic.common.MultipleStatusView;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.base.presenter.BasePresenter;
import com.sunshinexu.mobilelearn.base.view.BaseView;
import com.sunshinexu.mobilelearn.utils.dialog.LoadingDialog;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * 每个Fragment都需要继承此类
 * @param <T>
 */
public abstract class BaseFragment<T extends BasePresenter> extends AbstractSimpleFragment implements BaseView {

    private static final String TAG = "BaseFragment";

    @Inject
    protected T presenter;
    private MultipleStatusView custom_multiple_view;
    private LoadingDialog dialog;

    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG, "onAttach: ");
        AndroidSupportInjection.inject(this);     //必须在 onAttach 中完成
        super.onAttach(activity);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ");
        super.onViewCreated(view, savedInstanceState);
        custom_multiple_view = view.findViewById(R.id.custom_multiple_view);
        if (presenter != null) {
            presenter.attachView(this);
        }
        if (custom_multiple_view != null) {
            custom_multiple_view.setOnRetryClickListener(v -> presenter.reload());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter != null) {
            presenter = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(_mActivity, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出加载框
     */
    @Override
    public void showLoading() {
        System.out.println("showdialog");
        if (custom_multiple_view == null) {
            return;
        } else {
            System.out.println("showdialog");
            if (dialog == null) {
                dialog = new LoadingDialog(_mActivity, getString(R.string.loading_data),
                        R.mipmap.ic_dialog_loading);
            }
            dialog.show();
        }
    }

    /**
     * 隐藏加载框
     */
    @Override
    public void hideLoading() {
        if (dialog != null) {
            System.out.println("hidedialog");
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void showError() {
        if (custom_multiple_view == null) return;
        custom_multiple_view.showError();
    }

    @Override
    public void showNoNetwork() {
        if (custom_multiple_view == null) return;
        custom_multiple_view.showNoNetwork();
    }

    @Override
    public void showEmpty() {
        if (custom_multiple_view == null) return;
        custom_multiple_view.showEmpty();
    }

    @Override
    public void showContent() {
        if (custom_multiple_view == null) {
            return;
        } else {
            custom_multiple_view.showContent();
        }
    }

    @Override
    public void handleLoginSuccess() {

    }

    @Override
    public void handleLogoutSuccess() {

    }
}
