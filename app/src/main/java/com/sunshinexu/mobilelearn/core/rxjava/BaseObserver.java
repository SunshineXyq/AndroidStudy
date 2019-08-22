package com.sunshinexu.mobilelearn.core.rxjava;

import android.support.annotation.CallSuper;
import android.text.TextUtils;
import android.util.Log;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.view.BaseView;
import com.sunshinexu.mobilelearn.http.BaseResponse;
import com.sunshinexu.mobilelearn.http.exception.ServerException;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

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
//        Log.d(TAG, "onResponse" + tBaseResponse.getData().toString());
        if (tBaseResponse.getErrorCode() == BaseResponse.Success) {
            Log.d(TAG,"onSuccess");
            if (isShowStatusView) {
                view.hideLoading();
                view.showContent();
            }
            success(tBaseResponse.getData());
        } else {
            Log.d(TAG,"onFailed");
            if (isShowStatusView) {
                view.hideLoading();
                view.showContent();
            }
            onFailure(tBaseResponse.getErrorCode(),tBaseResponse.getErrorMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError: ");
        if (view == null) {
            return;
        }
        if (isShowStatusView) {
            view.hideLoading();
        }
        if (e instanceof HttpException) {
            view.showErrorMessage(MobileLearnApp.getContext().getString(R.string.http_error));
            if (isShowStatusView) {
                view.showNoNetwork();
            }
        } else if (e instanceof ServerException) {
            view.showErrorMessage(e.toString());
            if (isShowStatusView) {
                view.showError();
            }
        } else {
            if (!TextUtils.isEmpty(error)) {
                view.showErrorMessage(error);
            }
            if (isShowStatusView) {
                view.showError();
            }
            Log.e(TAG, e.toString());
        }
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
