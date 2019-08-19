package com.sunshinexu.mobilelearn.activity.fragment.collect.ui;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/19
 */

import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.GlideImageLoader;
import com.sunshinexu.mobilelearn.core.glide.GlideUtil;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;

import java.util.List;

public class CollectListAdapter extends BaseQuickAdapter<ArticleItemData, BaseViewHolder> {

    public CollectListAdapter(int layoutResId, @Nullable List<ArticleItemData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItemData item) {
        helper.setText(R.id.tv_article_title, Html.fromHtml(item.getTitle()))
                .setText(R.id.tv_article_author, item.getAuthor())
                .setImageResource(R.id.iv_article_like, R.drawable.ic_collect);
        if (!TextUtils.isEmpty(item.getChapterName())) {
            helper.setText(R.id.tv_article_chapterName, item.getChapterName());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_article_public_date, item.getNiceDate());
        }

        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            helper.getView(R.id.iv_article_thumbnail).setVisibility(View.VISIBLE);
            GlideUtil.download(mContext, item.getEnvelopePic(), helper.getView(R.id.iv_article_thumbnail));
        } else {
            helper.getView(R.id.iv_article_thumbnail).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.tv_article_chapterName);
        helper.addOnClickListener(R.id.iv_article_like);
    }
}
