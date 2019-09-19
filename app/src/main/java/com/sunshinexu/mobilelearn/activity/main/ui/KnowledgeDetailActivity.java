package com.sunshinexu.mobilelearn.activity.main.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

/**
 * 知识体系详情页
 */
public class KnowledgeDetailActivity extends BaseActivity<KnowledgeDetailPresenter> implements KnowledgeDetailContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.tb_knowledge_detail)
    TabLayout tb_knowledge_detail;
    @BindView(R.id.vp_knowledge_detail)
    ViewPager vp_knowledge_detail;
    @BindView(R.id.toolbar_back)
    ImageView toolbar_back;

    private SparseArray<KnowledgeArticleFragment> knowledgeArticleFragmentArray= new SparseArray<>();
    private List<KnowledgeSystemData> dataChildren;

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
            toolbar_back.setVisibility(View.VISIBLE);
        }
        toolbar_back.setOnClickListener(view -> finish());
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white_smoke));
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    protected void initView() {

    }

    /**
     * 初始化点击事件和数据
     */
    @Override
    protected void initEventAndData() {
        KnowledgeSystemData knowledgeSystemData =
                (KnowledgeSystemData) getIntent().getSerializableExtra(Constants.KNOWLEDGE_DATA);
        toolbar_title.setText(knowledgeSystemData.getName());
        dataChildren = knowledgeSystemData.getChildren();
        vp_knowledge_detail.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                KnowledgeArticleFragment knowledgeArticleFragment = knowledgeArticleFragmentArray.get(position);
                if (knowledgeArticleFragment == null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("KnowledgeDataId", dataChildren.get(position).getId());
                    knowledgeArticleFragment = KnowledgeArticleFragment.newInstance(bundle);
                    knowledgeArticleFragmentArray.put(position,knowledgeArticleFragment);
                    return knowledgeArticleFragment;
                } else {
                    return knowledgeArticleFragment;
                }
            }

            @Override
            public int getCount() {
                return dataChildren.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return Html.fromHtml(dataChildren.get(position).getName());
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                System.out.println("destory");
            }
        });
        vp_knowledge_detail.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tb_knowledge_detail));
        tb_knowledge_detail.setupWithViewPager(vp_knowledge_detail);
        tb_knowledge_detail.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vp_knowledge_detail));
        tb_knowledge_detail.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp_knowledge_detail.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onDestroy() {
        if (knowledgeArticleFragmentArray != null) {
            knowledgeArticleFragmentArray.clear();
            knowledgeArticleFragmentArray = null;
        }
        if (dataChildren != null) {
            dataChildren.clear();
            dataChildren = null;
        }
        super.onDestroy();
    }
}
