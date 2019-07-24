package com.sunshinexu.mobilelearn.core.http;

import com.sunshinexu.mobilelearn.http.BaseResponse;
import com.sunshinexu.mobilelearn.http.api.ApiService;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import javax.inject.Inject;

import io.reactivex.Observable;

public class HttpHelperImpl implements HttpHelper {

    private ApiService mApiService;

    @Inject
    HttpHelperImpl(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getArticleList(int num) {
        return mApiService.getArticleList(num);
    }
}
