package com.sunshinexu.mobilelearn.core.http;


import com.sunshinexu.mobilelearn.http.BaseResponse;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import io.reactivex.Observable;

public interface HttpHelper {
    /**
     * 获取文章列表
     * num 页数
     * Observable 是被观察者
     */
    Observable<BaseResponse<ArticleListData>> getArticleList(int num);
}
