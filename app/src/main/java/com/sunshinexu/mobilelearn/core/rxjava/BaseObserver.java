package com.sunshinexu.mobilelearn.core.rxjava;

import android.support.annotation.CallSuper;

import com.sunshinexu.mobilelearn.base.view.IView;
import com.sunshinexu.mobilelearn.http.BaseResponse;

import io.reactivex.observers.ResourceObserver;

public abstract class BaseObserver<T> extends ResourceObserver<BaseResponse<T>> {
    private IView view;
    private String error;
    private boolean isShowStatusView;

    protected BaseObserver(IView view){
        this.view = view;
    }

    protected BaseObserver(IView view,String error){
        this.view = view;
        this.error = error;
    }

    protected BaseObserver(IView view,boolean isShowStatusView){
        this.view = view;
        this.isShowStatusView = isShowStatusView;
    }

    protected BaseObserver(IView view,String error,boolean isShowStatusView){
        this.view = view;
        this.error = error;
        this.isShowStatusView = isShowStatusView;
    }

    @Override
    protected void onStart() {
        if (isShowStatusView) {
            view.showLoading();
        }
    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
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

    }

    public abstract void success(T t);

    @CallSuper
    public void onFailure(int code, String message) {
        view.showErrorMessage(message);
    }

}
