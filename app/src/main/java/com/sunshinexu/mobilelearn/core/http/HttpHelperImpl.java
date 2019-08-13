package com.sunshinexu.mobilelearn.core.http;

import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.BannerData;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.bean.KnowledgeSystemData;
import com.sunshinexu.mobilelearn.activity.fragment.project.bean.ProjectData;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.bean.PublicNumData;
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


}
