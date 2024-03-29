package com.sunshinexu.mobilelearn.core.http;


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

public interface HttpHelper {

    /**
     * Observable 是被观察者，发送事件通知
     * Observer 观察者，
     * Subscriber 对 Observer 接口的扩展
     */

    Observable<BaseResponse<ArticleListData>> getArticleList(int num);

    Observable<BaseResponse<ArticleListData>> addArticleCollect(int id);

    Observable<BaseResponse<ArticleListData>> cancelAddArticleCollect(int id);

    Observable<BaseResponse<List<ArticleItemData>>> getUpArticles();

    Observable<BaseResponse<List<BannerData>>> getBannerData();

    Observable<BaseResponse<List<PublicNumData>>> getPublicNumData();

    Observable<BaseResponse<ArticleListData>> getPersonalPublicNumData(int id,int page);

    Observable<BaseResponse<List<KnowledgeSystemData>>> getKnowledgeData();

    Observable<BaseResponse<List<ProjectData>>> getProjectData();

    Observable<BaseResponse<ArticleListData>> getProjectListData(int page,int projectId);

    Observable<BaseResponse<List<NavigationData>>> getNavigationData();

    Observable<BaseResponse<LoginData>> login(String username, String password);

    Observable<BaseResponse<LoginData>> register(String username, String password,String confirmPassword);

    Observable<BaseResponse<LoginData>> logout();

    Observable<BaseResponse<ArticleListData>> cancelCollectInCollectPage(int id, int originId);

    Observable<BaseResponse<ArticleListData>> getCollectList(int page);

    Observable<BaseResponse<List<WebsiteData>>> getWebsiteList();

    Observable<BaseResponse<List<HotSearchData>>> getSearchData();

    Observable<BaseResponse<ArticleListData>> getSearchResultList(int pageNum, String key);
}
