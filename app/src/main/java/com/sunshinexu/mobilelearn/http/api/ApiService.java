package com.sunshinexu.mobilelearn.http.api;


import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.BannerData;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.bean.KnowledgeSystemData;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.bean.PublicNumData;
import com.sunshinexu.mobilelearn.http.BaseResponse;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    String BASE_URL = "https://www.wanandroid.com/";

    //获取文章列表
    @GET("article/list/{pageNum}/json")
    Observable<BaseResponse<ArticleListData>> getArticleList(@Path("pageNum") int num);

    //添加文章收藏
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse<ArticleListData>> addArticleCollect(@Path("id") int id);

    //取消文章收藏
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse<ArticleListData>> cancelAddArticleCollect(@Path("id") int id);

    //获取置顶文章列表
    @GET("article/top/json")
    Observable<BaseResponse<List<ArticleItemData>>> getUpArticles();

    //获取首页 Banner 数据
    @GET("banner/json")
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    //获取微信公众号列表
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<PublicNumData>>> getPublicNumData();

    //获取个人微信公众号发布的文章
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<ArticleListData>> getPersonalPublicNumData(@Path("id") int id,
                                                                       @Path("page") int page);

    @GET("tree/json")
    Observable<BaseResponse<List<KnowledgeSystemData>>> getKnowledgeData();
}
