package com.sunshinexu.mobilelearn.core;

import com.sunshinexu.mobilelearn.core.http.HttpHelper;
import com.sunshinexu.mobilelearn.http.BaseResponse;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.sunshinexu.mobilelearn.pre.PreHelper;

import io.reactivex.Observable;

public class DataManager implements HttpHelper, PreHelper {

    private HttpHelper mHttpHelper;
    private PreHelper mPreHelper;

    public DataManager(HttpHelper httpHelper,PreHelper preHelper){
        mHttpHelper = httpHelper;
        mPreHelper = preHelper;
    }
    @Override
    public void setLoginStatus(boolean isLogin) {

    }

    @Override
    public boolean getLoginStatus() {
        return false;
    }

    @Override
    public void setLoginAccount(String account) {

    }

    @Override
    public String getLoginAccount() {
        return null;
    }

    @Override
    public void setNightMode(boolean isNightMode) {

    }

    @Override
    public boolean isNightMode() {
        return false;
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getArticleList(int num) {
        return mHttpHelper.getArticleList(num);
    }
}
