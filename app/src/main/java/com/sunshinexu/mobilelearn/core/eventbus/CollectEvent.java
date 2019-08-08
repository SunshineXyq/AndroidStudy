package com.sunshinexu.mobilelearn.core.eventbus;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/8
 */

public class CollectEvent {
    private boolean isCancel;
    private int articlePosition;

    public CollectEvent(boolean isCancel, int articlePosition) {
        this.isCancel = isCancel;
        this.articlePosition = articlePosition;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public int getArticlePosition() {
        return articlePosition;
    }

    public void setArticlePosition(int articlePosition) {
        this.articlePosition = articlePosition;
    }
}
