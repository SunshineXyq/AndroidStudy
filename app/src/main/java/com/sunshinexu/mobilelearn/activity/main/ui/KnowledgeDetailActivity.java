package com.sunshinexu.mobilelearn.activity.main.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.bean.KnowledgeSystemData;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.ui.KnowledgeArticleFragment;
import com.sunshinexu.mobilelearn.activity.main.contract.KnowledgeDetailContract;
import com.sunshinexu.mobilelearn.activity.main.presenter.KnowledgeDetailPresenter;
import com.sunshinexu.mobilelearn.base.activity.BaseActivity;
import com.sunshinexu.mobilelearn.core.constant.Constants;

import java.util.List;

import butterknife.BindView;

public class KnowledgeDetailActivity extends BaseActivity<KnowledgeDetailPresenter> implements KnowledgeDetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.tb_knowledge_detail)
    TabLayout tb_knowledge_detail;
    @BindView(R.id.vp_knowledge_detail)
    ViewPager vp_knowledge_detail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_detail;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white_smoke));
        toolbar.setNavigationOnClickListener(view -> onBackPressedSupport());
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEventAndData() {
        KnowledgeSystemData knowledgeSystemData =
                (KnowledgeSystemData) getIntent().getSerializableExtra(Constants.KNOWLEDGE_DATA);
        toolbar_title.setText(knowledgeSystemData.getName());
        List<KnowledgeSystemData> dataChildren = knowledgeSystemData.getChildren();
        vp_knowledge_detail.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("KnowledgeDataId",dataChildren.get(position).getId());
                return null;
            }

            @Override
            public int getCount() {
                return dataChildren.size();
            }
        });
    }
}
