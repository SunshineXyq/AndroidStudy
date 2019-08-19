package com.sunshinexu.mobilelearn.activity.main.ui;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/19
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.about.ui.AboutFragment;
import com.sunshinexu.mobilelearn.activity.fragment.collect.ui.CollectFragment;
import com.sunshinexu.mobilelearn.activity.fragment.setting.ui.SetFragment;
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
    Fragment mFragment;

   @BindView(R.id.frame_layout)
   FrameLayout mFrameGroup;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_item;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolbar);
        status_bar.setBackgroundColor(getResources().getColor(R.color.main_white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.main_white));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        getWindow().setNavigationBarColor(getResources().getColor(R.color.navigation_bar));
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initView() {
        String type = getIntent().getStringExtra("Type");
        Bundle bundle = getIntent().getExtras();
        String title = "";
        switch (type) {
//            case "Collect":
//                mTargetFragment = UsefulSitesFragment.newInstance();
//                title = getString(R.string.useful_sites);
//                break;
//            case Constants.TYPE_SEARCH:
//                mTargetFragment = SearchFragment.newInstance(extras);
//                assert extras != null;
//                title = extras.getString(Constants.SEARCH_KEY, "");
//                break;
            case Constants.TYPE_COLLECT:
                mFragment = CollectFragment.newInstance();
                break;
            case Constants.TYPE_ABOUT:
                mFragment = AboutFragment.newInstance();
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
