package com.sunshinexu.mobilelearn.activity.fragment.navigation.bean;

import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;

import java.io.Serializable;
import java.util.List;

public class NavigationData implements Serializable {

    private List<ArticleItemData> articles;
    private int cid;
    private String name;

    public List<ArticleItemData> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleItemData> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NavigationData{" +
                "articles=" + articles +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }
}
