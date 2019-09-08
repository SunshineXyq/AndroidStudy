package com.sunshinexu.mobilelearn.activity.main.ui;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/19
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.about.ui.AboutFragment;
import com.sunshinexu.mobilelearn.activity.fragment.collect.ui.CollectFragment;
import com.sunshinexu.mobilelearn.activity.fragment.search.SearchFragment;
import com.sunshinexu.mobilelearn.activity.fragment.setting.ui.SetFragment;
import com.sunshinexu.mobilelearn.activity.fragment.website.ui.WebsiteFragment;
import com.sunshinexu.mobilelearn.activity.main.contract.ItemContract;
import com.sunshinexu.mobilelearn.activity.main.presenter.ItemPresenter;
import com.sunshinexu.mobilelearn.base.activity.BaseActivity;
import com.sunshinexu.mobilelearn.core.constant.Constants;

import butterknife.BindView;

public class ItemActivity extends BaseActivity<ItemPresenter> implements ItemContract.View {

    @BindView(R.id.status_bar)
    View status_bar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.toolbar_back)
    ImageView toolbar_back;
    Fragment mFragment;

   @BindView(R.id.frame_layout)
   FrameLayout mFrameGroup;
    private ActionBar actionBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_item;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            toolbar_back.setVisibility(View.VISIBLE);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        getWindow().setNavigationBarColor(getResources().getColor(R.color.navigation_bar));
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
        toolbar_back.setOnClickListener(view -> finish());
    }

    @Override
    protected void initView() {
        String type = getIntent().getStringExtra("Type");
        Bundle bundle = getIntent().getExtras();
        switch (type) {
            case Constants.TYPE_WEBSITE:
                mFragment = WebsiteFragment.newInstance();
                mTitle.setText(R.string.about_website);
                break;
            case Constants.TYPE_SEARCH:
                mFragment = SearchFragment.newInstance(bundle);
                assert bundle != null;
                String title = bundle.getString(Constants.TYPE_TITLE, "");
                mTitle.setText(title);
                break;
            case Constants.TYPE_COLLECT:
                mFragment = CollectFragment.newInstance();
                mTitle.setText(R.string.nav_item_my_collect);
                break;
            case Constants.TYPE_ABOUT:
                status_bar.setBackgroundColor(getResources().getColor(R.color.main_white));
                toolbar.setBackgroundColor(getResources().getColor(R.color.main_white));
                mFragment = AboutFragment.newInstance();
                mTitle.setText(R.string.nav_item_about);
                break;
            default:
                break;
        }

        if (mFragment == null) {
            finish();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, mFragment)
                    .commit();

        }
    }

    @Override
    protected void initEventAndData() {

    }
}
