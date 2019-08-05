package com.sunshinexu.mobilelearn.core.http;

import com.sunshinexu.mobilelearn.http.BaseResponse;
import com.sunshinexu.mobilelearn.http.api.ApiService;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import java.util.List;

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

    @Override
    public Observable<BaseResponse<ArticleListData>> addArticleCollect(int id) {
        return mApiService.addArticleCollect(id);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> cancelAddArticleCollect(int id) {
        return mApiService.cancelAddArticleCollect(id);
    }

    @Override
    public Observable<BaseResponse<List<ArticleItemData>>> getUpArticles() {
        return mApiService.getUpArticles();
    }
}
