package com.sunshinexu.mobilelearn.activity.fragment.website.ui;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.website.bean.WebsiteData;
import com.sunshinexu.mobilelearn.activity.fragment.website.contract.WebsiteContract;
import com.sunshinexu.mobilelearn.activity.fragment.website.presenter.WebsitePresenter;
import com.sunshinexu.mobilelearn.activity.main.ui.ArticleDetailActivity;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class WebsiteFragment extends BaseFragment<WebsitePresenter> implements WebsiteContract.View {

    @BindView(R.id.tfl_website)
    TagFlowLayout tfl_website;
    private List<WebsiteData> mWebsiteList;

    public static WebsiteFragment newInstance(){
        return new WebsiteFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_website;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mWebsiteList = new ArrayList<>();
        presenter.getWebsiteList();
    }

    @Override
    public void showWebsiteList(List<WebsiteData> websiteList) {
        mWebsiteList = websiteList;
        tfl_website.setAdapter(new TagAdapter<WebsiteData>(websiteList) {
            @Override
            public View getView(FlowLayout parent, int position, WebsiteData websiteData) {
                TextView tv = (TextView) LayoutInflater.from(_mActivity)
                        .inflate(R.layout.flow_layout_website, parent, false);
                if (websiteData != null) {
                    tv.setText(websiteData.getName());
                    tv.setTextColor(getRandomColor());
                }
                return tv;
            }
        });
        tfl_website.setOnTagClickListener((view, position1, parent1) -> {
            Intent intent = new Intent(_mActivity, ArticleDetailActivity.class);
            intent.putExtra(Constants.ARTICLE_LINK,websiteList.get(position1).getId());
            intent.putExtra(Constants.ARTICLE_TITLE,websiteList.get(position1).getName().trim());
            intent.putExtra(Constants.ARTICLE_ID,websiteList.get(position1).getLink().trim());
            intent.putExtra(Constants.IS_COLLECTED,false);
            intent.putExtra(Constants.IS_SHOW_COLLECT_ICON,false);
            intent.putExtra(Constants.ARTICLE_ITEM_POSITION,-1);
            intent.putExtra(Constants.EVENT_BUS_TAG,"default");
            startActivity(intent);
            return true;
        });
    }

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
