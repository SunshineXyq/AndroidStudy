package com.sunshinexu.mobilelearn.core.rxjava;

import android.support.annotation.CallSuper;
import android.util.Log;

import com.sunshinexu.mobilelearn.base.view.BaseView;
import com.sunshinexu.mobilelearn.http.BaseResponse;

import io.reactivex.observers.ResourceObserver;

public abstract class BaseObserver<T> extends ResourceObserver<BaseResponse<T>> {
    private BaseView view;
    private String error;
    private boolean isShowStatusView;
    private static final String TAG = "BaseObserver";

    protected BaseObserver(BaseView view){
        this.view = view;
    }

    protected BaseObserver(BaseView view, String error){
        this.view = view;
        this.error = error;
    }

    protected BaseObserver(BaseView view, boolean isShowStatusView){
        this.view = view;
        this.isShowStatusView = isShowStatusView;
    }

    protected BaseObserver(BaseView view, String error, boolean isShowStatusView){
        this.view = view;
        this.error = error;
        this.isShowStatusView = isShowStatusView;
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        if (isShowStatusView) {
            view.showLoading();
        }
    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        Log.d(TAG, "onNext: ");
        if (tBaseResponse.getErrorCode() == BaseResponse.Success) {
            if (isShowStatusView) {
                view.hideLoading();
                view.showContent();
            }
            success(tBaseResponse.getData());
        } else {
            if (isShowStatusView) {
                view.hideLoading();
                view.showContent();
            }
            onFailure(tBaseResponse.getErrorCode(),tBaseResponse.getErrorMsg());
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
    }

    public abstract void success(T t);

    @CallSuper
    public void onFailure(int code, String message) {
        view.showErrorMessage(message);
    }

}
