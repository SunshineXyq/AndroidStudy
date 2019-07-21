package com.sunshinexu.mobilelearn.http.api;

import android.database.Observable;

import com.sunshinexu.mobilelearn.http.BaseResponse;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    String BASE_URL = "https://www.android.com/";

    //获取文章列表
    @GET("article/list/{pageNum}/json")
    Observable<BaseResponse<ArticleListData>> getArticleList(@Path("pageNum") int num);
}
