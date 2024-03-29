package com.sunshinexu.mobilelearn.core;

import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.BannerData;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.bean.KnowledgeSystemData;
import com.sunshinexu.mobilelearn.activity.fragment.login.bean.LoginData;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.bean.NavigationData;
import com.sunshinexu.mobilelearn.activity.fragment.project.bean.ProjectData;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.bean.PublicNumData;
import com.sunshinexu.mobilelearn.activity.fragment.website.bean.WebsiteData;
import com.sunshinexu.mobilelearn.activity.main.bean.HistoryData;
import com.sunshinexu.mobilelearn.activity.main.bean.HotSearchData;
import com.sunshinexu.mobilelearn.core.db.DbHelper;
import com.sunshinexu.mobilelearn.core.http.HttpHelper;
import com.sunshinexu.mobilelearn.http.BaseResponse;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;
import com.sunshinexu.mobilelearn.pre.PreHelper;

import java.util.List;

import io.reactivex.Observable;

public class DataManager implements PreHelper,HttpHelper,DbHelper {

    private HttpHelper mHttpHelper;
    private PreHelper mPreHelper;
    private DbHelper mDbHelper;

    public DataManager(HttpHelper httpHelper,PreHelper preHelper,DbHelper dbHelper){
        mHttpHelper = httpHelper;
        mPreHelper = preHelper;
        mDbHelper = dbHelper;
    }
    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreHelper.setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return mPreHelper.getLoginStatus();
    }

    @Override
    public void setLoginAccount(String account) {
        mPreHelper.setLoginAccount(account);
    }

    @Override
    public String getLoginAccount() {
        return mPreHelper.getLoginAccount();
    }

    @Override
    public void setNightMode(boolean isNightMode) {

    }

    @Override
    public boolean isNightMode() {
        return false;
    }


    @Override
    public Observable<BaseResponse<ArticleListData>> getArticleList(int num) {
        return mHttpHelper.getArticleList(num);
    }


    @Override
    public Observable<BaseResponse<ArticleListData>> addArticleCollect(int id) {
        return mHttpHelper.addArticleCollect(id);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> cancelAddArticleCollect(int id) {
        return mHttpHelper.cancelAddArticleCollect(id);
    }

    @Override
    public Observable<BaseResponse<List<ArticleItemData>>> getUpArticles() {
        return mHttpHelper.getUpArticles();
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mHttpHelper.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<PublicNumData>>> getPublicNumData() {
        return mHttpHelper.getPublicNumData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getPersonalPublicNumData(int id,int page) {
        return mHttpHelper.getPersonalPublicNumData(id,page);
    }

    @Override
    public Observable<BaseResponse<List<KnowledgeSystemData>>> getKnowledgeData() {
        return mHttpHelper.getKnowledgeData();
    }

    @Override
    public Observable<BaseResponse<List<ProjectData>>> getProjectData() {
        return mHttpHelper.getProjectData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getProjectListData(int page, int projectId) {
        return mHttpHelper.getProjectListData(page,projectId);
    }

    @Override
    public Observable<BaseResponse<List<NavigationData>>> getNavigationData() {
        return mHttpHelper.getNavigationData();
    }

    @Override
    public Observable<BaseResponse<LoginData>> login(String username, String password) {
        return mHttpHelper.login(username,password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> register(String username, String password,
                                                        String confirmPassword) {
        return mHttpHelper.register(username,password,confirmPassword);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logout() {
        return mHttpHelper.logout();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> cancelCollectInCollectPage(int id,
                                                                                int originId) {
        return mHttpHelper.cancelCollectInCollectPage(id, originId);
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getCollectList(int page) {
        return mHttpHelper.getCollectList(page);
    }

    @Override
    public Observable<BaseResponse<List<WebsiteData>>> getWebsiteList() {
        return mHttpHelper.getWebsiteList();
    }

    @Override
    public Observable<BaseResponse<List<HotSearchData>>> getSearchData() {
        return mHttpHelper.getSearchData();
    }

    @Override
    public Observable<BaseResponse<ArticleListData>> getSearchResultList(int pageNum, String key) {
        return mHttpHelper.getSearchResultList(pageNum,key);
    }

    @Override
    public List<HistoryData> addHistoryData(String data) {
        return mDbHelper.addHistoryData(data);
    }

    @Override
    public void clearHistoryData() {
        mDbHelper.clearHistoryData();
    }

    @Override
    public void deleteHistoryDataId(Long id) {
        mDbHelper.deleteHistoryDataId(id);
    }

    @Override
    public List<HistoryData> loadAllHistoryData() {
        return mDbHelper.loadAllHistoryData();
    }
}

