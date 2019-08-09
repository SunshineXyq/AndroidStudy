package com.sunshinexu.mobilelearn.activity.main.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.NestedScrollAgentWebView;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.main.contract.ArticleDetailContract;
import com.sunshinexu.mobilelearn.activity.main.presenter.ArticleDetailPresenter;
import com.sunshinexu.mobilelearn.base.activity.BaseActivity;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.core.eventbus.CollectEvent;
import com.tbruyelle.rxpermissions2.RxPermissions;


import org.simple.eventbus.EventBus;

import java.lang.reflect.Method;

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
    @BindView(R.id.cl_content)
     CoordinatorLayout cl_content;
    private AgentWeb agentWeb;
    private MenuItem collectItem;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initToolbar() {
        getIntentData();
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white_smoke));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);     //左上角图标左边加上返回图标
            actionBar.setDisplayShowTitleEnabled(false);
            toolbarTitle.setText(Html.fromHtml(title));
            toolbarTitle.setSelected(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        }) ;

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
        //集合了影响浏览器的事件到来时的回调方法
        WebChromeClient webChromeClient = new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
               // toolbar.setTitle(Html.fromHtml(title));
            }
        };
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(-1, -1);
        layoutParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        NestedScrollAgentWebView nestedScrollAgentWebView = new NestedScrollAgentWebView(this);
        //AgentWeb 是对 WebView 的封装
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(cl_content,layoutParams)
                .useDefaultIndicator()
                .setWebView(nestedScrollAgentWebView)
                .setWebChromeClient(webChromeClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(articleLink);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_detail_menu,menu);
        collectItem = menu.findItem(R.id.item_collect);
        collectItem.setVisible(isShowCollectIcon);
        collectItem.setIcon(isCollected ? R.drawable.ic_collect : R.drawable.ic_collect_not);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * 可以让打开的菜单同时显示图标和文字
     * @param featureId
     * @param menu
     * @return
     */

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if ("MenuBuilder".equalsIgnoreCase(menu.getClass().getSimpleName())) {
                try {
                    @SuppressLint("PrivateApi")
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_collect:
                dealCollect();
                break;
            case R.id.item_share:
                mPresenter.checkSharePermission(new RxPermissions(this));
                break;
            case R.id.item_open_browser:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(articleLink));
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dealCollect() {
        if (mPresenter.getLoginStatus()) {
            if (isCollected) {
                mPresenter.cancelAddCollectArticle(articleItemPosition,articleId);
            } else {
                mPresenter.addCollectArticle(articleItemPosition,articleId);
            }
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void shareArticle() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.share_type_url, "MobileLearn", title, articleLink));
        intent.setType("text/plain");
        startActivity(intent);
    }

    @Override
    public void shareFailed() {
        Toast.makeText(this,R.string.failed_to_share_failed,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCollectSuccess(int position) {
        isCollected = true;
        collectItem.setIcon(R.drawable.ic_collect);
        if (position < 0 ) {
            Toast.makeText(this,R.string.collect_success,Toast.LENGTH_SHORT).show();
        } else {
            EventBus.getDefault().post(new CollectEvent(false,position),eventBusTag);
        }
    }

    @Override
    public void showCancelCollect(int position) {
        isCollected = false;
        collectItem.setIcon(R.drawable.ic_collect_not);
        if (position < 0) {
            Toast.makeText(this,R.string.collect_failed,Toast.LENGTH_SHORT).show();
        } else {
            EventBus.getDefault().post(new CollectEvent(true, position), eventBusTag);
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (!agentWeb.back()) {
            super.onBackPressedSupport();
        }
    }

    @Override
    protected void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
