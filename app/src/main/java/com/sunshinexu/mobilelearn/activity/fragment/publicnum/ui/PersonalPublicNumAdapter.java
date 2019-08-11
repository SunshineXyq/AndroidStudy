package com.sunshinexu.mobilelearn.activity.fragment.publicnum.ui;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;

import java.util.List;

public class PersonalPublicNumAdapter extends BaseQuickAdapter<ArticleItemData, BaseViewHolder> {

    public PersonalPublicNumAdapter(int layoutResId, @Nullable List<ArticleItemData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItemData item) {

    }
}
