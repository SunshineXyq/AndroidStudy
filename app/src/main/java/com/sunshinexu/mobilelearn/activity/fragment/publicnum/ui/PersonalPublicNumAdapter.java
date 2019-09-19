package com.sunshinexu.mobilelearn.activity.fragment.publicnum.ui;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.core.glide.GlideUtil;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;

import java.util.List;

/**
 * 个人公众号适配器
 */
public class PersonalPublicNumAdapter extends BaseQuickAdapter<ArticleItemData, BaseViewHolder> {

    public PersonalPublicNumAdapter(int layoutResId, @Nullable List<ArticleItemData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleItemData item) {

        helper.setText(R.id.tv_article_title, item.getTitle()).setText(R.id.tv_article_author,
                "作者：" + item.getAuthor())
                .setImageResource(R.id.iv_article_like, item.isCollect() ? R.drawable.ic_collect :
                        R.drawable.ic_collect_not);
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_article_public_date, item.getNiceDate());
        }
        if (!TextUtils.isEmpty(item.getChapterName())) {
            String classifyName = item.getSuperChapterName() + " / " + item.getChapterName();
            helper.setText(R.id.tv_article_chapterName, classifyName);
        }
        //是否置顶
        helper.getView(R.id.iv_article_top).setVisibility(item.getType() == 1 ? View.VISIBLE :
                View.GONE);
        //是否是最新
        helper.getView(R.id.iv_article_fresh).setVisibility(item.isFresh() ? View.VISIBLE :
                View.GONE);

        if (item.getTags().size() > 0) {
            helper.setText(R.id.tv_article_tag, item.getTags().get(0).getName()).
                    getView(R.id.tv_article_tag).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.tv_article_tag).setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            helper.getView(R.id.iv_article_thumbnail).setVisibility(View.VISIBLE);
            GlideUtil.download(mContext, item.getEnvelopePic(),
                    helper.getView(R.id.iv_article_thumbnail));
        } else {
            helper.getView(R.id.iv_article_thumbnail).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.iv_article_like);
    }
}
