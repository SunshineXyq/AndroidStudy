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

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mApiService.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<PublicNumData>>> getPublicNumData() {
        return mApiService.getPublicNumData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getPersonalPublicNumData(int id,int page) {
        return mApiService.getPersonalPublicNumData(id,page);
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeSystemData>>> getKnowledgeData() {
        return mApiService.getKnowledgeData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectData>>> getProjectData() {
        return mApiService.getProjectData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getProjectListData(int page, int projectId) {
        return mApiService.getProjectListData(page,projectId);
    }

    @Override
    public Observable<BaseResponse<List<NavigationData>>> getNavigationData() {
        return mApiService.getNavigationData();
    }

    @Override
    public Observable<BaseResponse<LoginData>> login(String username, String password) {
        return mApiService.login(username,password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> register(String username, String password,
                                                        String confirmPassword) {
        return mApiService.register(username,password,confirmPassword);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logout() {
        return mApiService.logout();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> cancelCollectInCollectPage(int id,
                                                                                int originId) {
        return mApiService.cancelCollectInCollectPage(id,originId);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getCollectList(int page) {
        return mApiService.getCollectList(page);
    }

    @Override
    public Observable<BaseResponse<List<WebsiteData>>> getWebsiteList() {
        return mApiService.getWebsiteList();
    }

    @Override
    public Observable<BaseResponse<List<HotSearchData>>> getSearchData() {
        return mApiService.getSearchData();
    }
}
