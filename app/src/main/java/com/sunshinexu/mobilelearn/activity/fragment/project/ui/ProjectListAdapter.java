package com.sunshinexu.mobilelearn.activity.fragment.project.ui;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.homepager.bean.GlideImageLoader;
import com.sunshinexu.mobilelearn.core.glide.GlideUtil;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;

import java.util.List;


/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/13
 */

public class ProjectListAdapter extends BaseQuickAdapter<ArticleItemData, BaseViewHolder> {


    public ProjectListAdapter(int layoutResId, @Nullable List<ArticleItemData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItemData item) {
        helper.setText(R.id.item_project_list_title_tv, Html.fromHtml(item.getTitle()))
                .setText(R.id.item_project_list_author_tv, item.getAuthor())
                .setImageResource(R.id.item_project_list_like_iv, item.isCollect() ? R.drawable.ic_collect : R.drawable.ic_collect_not);
        if (!TextUtils.isEmpty(item.getDesc())) {
            helper.setText(R.id.item_project_list_content_tv, Html.fromHtml(item.getDesc()));
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.item_project_list_time_tv, item.getNiceDate());
        }

        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            GlideUtil.download(mContext, item.getEnvelopePic(),
                    helper.getView(R.id.item_project_list_iv));
        }

        helper.addOnClickListener(R.id.item_project_list_like_iv);
    }
}
