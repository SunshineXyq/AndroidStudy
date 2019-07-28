package com.sunshinexu.mobilelearn.http.api;



import com.sunshinexu.mobilelearn.http.BaseResponse;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    String BASE_URL = "https://www.android.com/";

    //获取文章列表
    @GET("article/list/{pageNum}/json")
    Observable<BaseResponse<ArticleListData>> getArticleList(@Path("pageNum") int num);

    //添加文章收藏
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse<ArticleListData>> addArticleCollect(@Path("id") int id);

    //取消文章收藏
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse<ArticleListData>> cancelAddArticleCollect(@Path("id") int id);
}
