package com.sunshinexu.mobilelearn.activity.main.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.main.contract.ArticleDetailContract;
import com.sunshinexu.mobilelearn.activity.main.presenter.ArticleDetailPresenter;
import com.sunshinexu.mobilelearn.base.activity.BaseActivity;
import com.sunshinexu.mobilelearn.core.constant.Constants;

import butterknife.BindView;

public class ArticleDetailActivity extends BaseActivity<ArticleDetailPresenter> implements ArticleDetailContract.View {


    private String title;
    private String articleLink;
    private int articleId;
    private boolean isCollected;
    private boolean isShowCollectIcon;
    private int articleItemPosition;
    private String eventBusTag;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @Override
    public void shareActivity() {

    }

    @Override
    public void shareFailed() {

    }

    @Override
    public void showCollectSuccess(int position) {

    }

    @Override
    public void showCancelCollect(int position) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initToolbar() {
        getIntentData();
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);     //左上角图标左边加上返回图标
            actionBar.setDisplayShowTitleEnabled(false);
            toolbarTitle.setText(Html.fromHtml(title));
            toolbarTitle.setSelected(true);
        }
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        title = bundle.getString(Constants.ARTICLE_TITLE);
        articleLink = bundle.getString(Constants.ARTICLE_LINK);
        articleId = bundle.getInt(Constants.ARTICLE_ID);
        isCollected = bundle.getBoolean(Constants.IS_COLLECTED);
        isShowCollectIcon = bundle.getBoolean(Constants.IS_SHOW_COLLECT_ICON);
        articleItemPosition = bundle.getInt(Constants.ARTICLE_ITEM_POSITION, -1);
        eventBusTag = bundle.getString(Constants.EVENT_BUS_TAG);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showNoNetwork() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void handleLoginSuccess() {

    }

    @Override
    public void handleLogoutSuccess() {

    }
}
