package com.sunshinexu.mobilelearn.core.http;

import com.sunshinexu.mobilelearn.http.BaseResponse;
import com.sunshinexu.mobilelearn.http.api.ApiService;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import javax.inject.Inject;

import io.reactivex.Observable;

public class HttpHelperImpl implements HttpHelper {

    private ApiService mApiService;

    @Inject
    public HttpHelperImpl(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getArticleList(int num) {
        return null;
    }
}
