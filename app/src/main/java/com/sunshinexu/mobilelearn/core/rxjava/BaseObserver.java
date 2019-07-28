package com.sunshinexu.mobilelearn.core.rxjava;

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
    public void onNext(BaseResponse<T> tBaseResponse) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public abstract void success();
}
