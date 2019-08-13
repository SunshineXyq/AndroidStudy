package com.sunshinexu.mobilelearn.activity.fragment.navigation.ui;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.bean.NavigationData;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;

import java.util.List;

public class NavigationAdapter extends BaseQuickAdapter<NavigationData, BaseViewHolder> {


    public NavigationAdapter(int layoutResId, @Nullable List<NavigationData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationData item) {

    }
}
