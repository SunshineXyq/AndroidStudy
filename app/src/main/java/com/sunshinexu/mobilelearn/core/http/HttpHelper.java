package com.sunshinexu.mobilelearn.core.http;


import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.BannerData;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.bean.PublicNumData;
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


}
