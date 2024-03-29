package com.sunshinexu.mobilelearn.http.api;


import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.BannerData;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.bean.KnowledgeSystemData;
import com.sunshinexu.mobilelearn.activity.fragment.login.bean.LoginData;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.bean.NavigationData;
import com.sunshinexu.mobilelearn.activity.fragment.project.bean.ProjectData;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.bean.PublicNumData;
import com.sunshinexu.mobilelearn.activity.fragment.website.bean.WebsiteData;
import com.sunshinexu.mobilelearn.activity.main.bean.HotSearchData;
import com.sunshinexu.mobilelearn.http.BaseResponse;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * 网络请求接口，采用注解描述网络请求参数和配置请求参数
     * @Field 用于Post，表示发送一个表单请求，并加上@FormUrlEncoded，表示使用表单网址编码
     * @Path  用于Get，表示URL路段中替换指定的参数值
     * @Query 用于Get，表示添加查询参数
     */
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

    //获取知识体系数据
    @GET("tree/json")
    Observable<BaseResponse<List<KnowledgeSystemData>>> getKnowledgeData();

    //获取项目分类数据
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectData>>> getProjectData();

    //获取项目列表数据
    @GET("project/list/{page}/json")
    Observable<BaseResponse<ArticleListData>> getProjectListData(@Path("page") int page,
                                                                 @Query("cid") int projectId);

    //获取知识导航数据
    @GET("navi/json")
    Observable<BaseResponse<List<NavigationData>>> getNavigationData();

    //登录
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> login(@Field("username") String username, @Field(
            "password") String password);

    //注册
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> register(@Field("username") String username, @Field(
            "password") String password, @Field("repassword") String confirmPassword);

    //注销
    @GET("user/logout/json")
    Observable<BaseResponse<LoginData>> logout();

    //收藏列表中取消收藏文章,id为文章id，originId为列表页下发
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse<ArticleListData>> cancelCollectInCollectPage(@Path("id") int id,
                                                                         @Field("originId") int originId);
    //获取收藏列表数据
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<ArticleListData>> getCollectList(@Path("page") int page);

    //获取常用网站
    @GET("friend/json")
    Observable<BaseResponse<List<WebsiteData>>> getWebsiteList();

    //搜索热词
    @GET("hotkey/json")
    @Headers("Cache-Control: public, max-age=36000")
    Observable<BaseResponse<List<HotSearchData>>> getSearchData();

    //搜索关键词
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<BaseResponse<ArticleListData>> getSearchResultList(@Path("page") int page, @Field("k") String key);
}
