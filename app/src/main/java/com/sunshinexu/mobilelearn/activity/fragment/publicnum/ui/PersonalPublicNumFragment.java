package com.sunshinexu.mobilelearn.activity.fragment.publicnum.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.contract.PersonalPublicNumContract;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.presenter.PersonalPublicNumPresenter;
import com.sunshinexu.mobilelearn.activity.main.ui.ArticleDetailActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.LoginActivity;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.http.bean.ArticleItemData;
import com.sunshinexu.mobilelearn.http.bean.ArticleListData;

import java.util.ArrayList;

import butterknife.BindView;

public class PersonalPublicNumFragment extends BaseFragment<PersonalPublicNumPresenter>
        implements PersonalPublicNumContract.View {

    @BindView(R.id.srl_personal_public_num)
    SmartRefreshLayout srl_personal_public_num;
    @BindView(R.id.rv_public_num)
    RecyclerView rv_public_num;
    private PersonalPublicNumAdapter mAdapter;

    public static PersonalPublicNumFragment newInstance(Bundle bundle) {
        PersonalPublicNumFragment personalPublicNumFragment = new PersonalPublicNumFragment();
        personalPublicNumFragment.setArguments(bundle);
        return personalPublicNumFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_public_num;
    }

    @Override
    protected void initView() {
        ArrayList<ArticleItemData> articleItemData = new ArrayList<>();
        mAdapter = new PersonalPublicNumAdapter(R.layout.item_article, articleItemData);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetail(view,
                position));
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> childOnClick(view,
                position));
        DividerItemDecoration divider = new DividerItemDecoration(_mActivity,
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(_mActivity, R.drawable.shape_hp_rv_line));
        rv_public_num.addItemDecoration(divider);
        rv_public_num.setLayoutManager(new LinearLayoutManager(_mActivity));
        rv_public_num.setHasFixedSize(true);   //item 固定宽高，避免重复计算大小
        rv_public_num.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        int publicNumId = getArguments().getInt("publicNumId");
        presenter.refreshLayout(publicNumId, true);
        srl_personal_public_num.setOnRefreshListener(refreshLayout -> {
            presenter.refreshLayout(publicNumId, false);
            srl_personal_public_num.finishRefresh();
        });
        srl_personal_public_num.setOnLoadMoreListener(refreshLayout -> {
            presenter.loadMore();
            srl_personal_public_num.finishLoadMore();
        });
    }

    private void startArticleDetail(View view, int position) {
        Intent intent = new Intent(_mActivity, ArticleDetailActivity.class);
        intent.putExtra(Constants.ARTICLE_LINK, mAdapter.getData().get(position).getLink());
        intent.putExtra(Constants.ARTICLE_TITLE, mAdapter.getData().get(position).getTitle());
        intent.putExtra(Constants.ARTICLE_ID, mAdapter.getData().get(position).getId());
        intent.putExtra(Constants.IS_COLLECTED, mAdapter.getData().get(position).isCollect());
        intent.putExtra(Constants.IS_SHOW_COLLECT_ICON, true);
        intent.putExtra(Constants.ARTICLE_ITEM_POSITION, position);
        intent.putExtra(Constants.EVENT_BUS_TAG, "publicNum");
        startActivity(intent);
    }

    private void childOnClick(View view, int position) {
        if (presenter.getLoginStatus()) {
            if (mAdapter.getData().get(position).isCollect()) {
                presenter.cancelAddCollectArticle(position,
                        mAdapter.getData().get(position).getId());
            } else {
                presenter.addCollectArticle(position, mAdapter.getData().get(position).getId());
            }
        } else {
            Intent intent = new Intent(_mActivity, LoginActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void showPersonalPublicNum(ArticleListData articleListData, boolean isRefresh) {
        if (isRefresh) {
            mAdapter.replaceData(articleListData.getDatas());
        } else {
            mAdapter.addData(articleListData.getDatas());
        }

    }

    @Override
    public void showCollectSuccess(int position) {
        mAdapter.getData().get(position).setCollect(true);
        mAdapter.setData(position, mAdapter.getData().get(position));
    }

    @Override
    public void showCancelCollect(int position) {
        mAdapter.getData().get(position).setCollect(false);
        mAdapter.setData(position, mAdapter.getData().get(position));
    }
}
