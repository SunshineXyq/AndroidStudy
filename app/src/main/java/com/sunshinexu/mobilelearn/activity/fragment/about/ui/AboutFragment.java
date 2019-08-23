/*
 *     (C) Copyright 2019, ForgetSky.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.sunshinexu.mobilelearn.activity.fragment.about.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.about.contract.AboutContract;
import com.sunshinexu.mobilelearn.activity.fragment.about.presenter.AboutPresenter;
import com.sunshinexu.mobilelearn.activity.main.ui.ArticleDetailActivity;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.core.constant.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.Beta;


public class AboutFragment extends BaseFragment<AboutPresenter> implements AboutContract.View,
        View.OnClickListener {

    @BindView(R.id.about_suggest)
    RelativeLayout about_suggest;
    @BindView(R.id.about_upgrade)
    RelativeLayout about_upgrade;
    @BindView(R.id.about_website)
    RelativeLayout about_website;
    @BindView(R.id.about_version)
    TextView about_version;


    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    protected void initView() {
        about_suggest.setOnClickListener(this);
        about_upgrade.setOnClickListener(this);
        about_website.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initData() {
        showAboutContent();
    }

    private void showAboutContent() {
        try {
            String versionStr = "Version"
                    + " V" + MobileLearnApp.getContext().getPackageManager()
                    .getPackageInfo(MobileLearnApp.getContext().getPackageName(), 0).versionName;
            about_version.setText(versionStr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.about_upgrade:
                //Beta.checkUpgrade();
                break;
            case R.id.about_suggest:
                Intent intentSuggest = new Intent(_mActivity, ArticleDetailActivity.class);
                intentSuggest.putExtra(Constants.ARTICLE_ID, -1);
                intentSuggest.putExtra(Constants.ARTICLE_TITLE, getString(R.string.about_suggest));
                intentSuggest.putExtra(Constants.ARTICLE_LINK, "https://github.com/SunshineXyq/AndroidStudy/issues");
                intentSuggest.putExtra(Constants.IS_COLLECTED, false);
                intentSuggest.putExtra(Constants.IS_SHOW_COLLECT_ICON, false);
                intentSuggest.putExtra(Constants.ARTICLE_ITEM_POSITION, -1);
                intentSuggest.putExtra(Constants.EVENT_BUS_TAG, "default");
                startActivity(intentSuggest);
                break;
            case R.id.about_website:
                Intent intent = new Intent(_mActivity, ArticleDetailActivity.class);
                intent.putExtra(Constants.ARTICLE_ID, -1);
                intent.putExtra(Constants.ARTICLE_TITLE, getString(R.string.about_website));
                intent.putExtra(Constants.ARTICLE_LINK, "https://www.wanandroid.com/about");
                intent.putExtra(Constants.IS_COLLECTED, false);
                intent.putExtra(Constants.IS_SHOW_COLLECT_ICON, false);
                intent.putExtra(Constants.ARTICLE_ITEM_POSITION, -1);
                intent.putExtra(Constants.EVENT_BUS_TAG, "default");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
