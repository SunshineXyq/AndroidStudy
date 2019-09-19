package com.sunshinexu.mobilelearn.activity.fragment.navigation.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.bean.NavigationData;
import com.sunshinexu.mobilelearn.activity.main.ui.ArticleDetailActivity;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;
import java.util.Random;

/**
 * 学习导航学习内容适配器
 */
public class NavigationAdapter extends BaseQuickAdapter<NavigationData, BaseViewHolder> {


    public NavigationAdapter(int layoutResId, @Nullable List<NavigationData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationData item) {
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.item_navigation_tv, item.getName());
        }
        TagFlowLayout mTagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        List<ArticleItemData> mArticles = item.getArticles();
        mTagFlowLayout.setAdapter(new TagAdapter<ArticleItemData>(mArticles) {
            @Override
            public View getView(FlowLayout parent, int position, ArticleItemData articleItemData) {
                TextView tv =
                        (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_flow_layout_tv,
                        mTagFlowLayout, false);
                if (articleItemData == null) {
                    return null;
                }
                String name = articleItemData.getTitle();
                tv.setText(name);
                tv.setTextColor(getRandomColor());
                mTagFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
                    Intent intent = new Intent(parent.getContext(), ArticleDetailActivity.class);
                    intent.putExtra(Constants.ARTICLE_LINK,mArticles.get(position).getLink());
                    intent.putExtra(Constants.ARTICLE_TITLE,mArticles.get(position).getTitle());
                    intent.putExtra(Constants.ARTICLE_ID,mArticles.get(position).getId());
                    intent.putExtra(Constants.IS_COLLECTED,mArticles.get(position).isCollect());
                    intent.putExtra(Constants.EVENT_BUS_TAG,"flow_layout");
                    intent.putExtra(Constants.IS_SHOW_COLLECT_ICON,true);
                    intent.putExtra(Constants.ARTICLE_ITEM_POSITION,-1);
                    parent.getContext().startActivity(intent);
                    return true;
                });
                return tv;
            }
        });
    }

    /**
     * 获取随机颜色
     * @return
     */
    public static int getRandomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red;
        int green;
        int blue;
        red = random.nextInt(190);
        green = random.nextInt(190);
        blue = random.nextInt(190);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue);
    }
}
