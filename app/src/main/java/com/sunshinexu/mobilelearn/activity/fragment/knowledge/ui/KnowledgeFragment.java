package com.sunshinexu.mobilelearn.activity.fragment.knowledge.ui;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.bean.KnowledgeSystemData;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.contract.KnowledgeContract;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.presenter.KnowledgePresenter;
import com.sunshinexu.mobilelearn.activity.main.ui.KnowledgeDetailActivity;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;
import com.sunshinexu.mobilelearn.core.constant.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View {

    @BindView(R.id.rv_knowledge)
    RecyclerView rv_knowledge;
    @BindView(R.id.srl_knowledge)
    SmartRefreshLayout srl_knowledge;
    private KnowledgeAdapter mAdapter;

    public static KnowledgeFragment newInstance() {
        KnowledgeFragment knowledgeFragment = new KnowledgeFragment();
        return knowledgeFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        ArrayList<KnowledgeSystemData> knowledgeSystemData = new ArrayList<>();
        mAdapter = new KnowledgeAdapter(R.layout.item_knowledge,
                knowledgeSystemData);
        mAdapter.setOnItemClickListener((adapter, view, position) -> startKnowledgeDetail(view, position));
        DividerItemDecoration divider = new DividerItemDecoration(_mActivity,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(_mActivity,R.drawable.shape_hp_rv_line));
        rv_knowledge.addItemDecoration(divider);
        rv_knowledge.setHasFixedSize(true);
        rv_knowledge.setLayoutManager(new LinearLayoutManager(_mActivity));
        rv_knowledge.setAdapter(mAdapter);
        srl_knowledge.setOnRefreshListener(refreshLayout -> {
            presenter.getKnowledgeSystemData(false);
            refreshLayout.finishRefresh();
        });
    }

    @Override
    protected void initData() {
        presenter.getKnowledgeSystemData(true);
    }

    @Override
    public void showKnowledgeSystemData(List<KnowledgeSystemData> knowledgeSystems) {
        mAdapter.replaceData(knowledgeSystems);
    }

    public void startKnowledgeDetail(View view, int position) {
        Intent intent = new Intent(_mActivity, KnowledgeDetailActivity.class);
        intent.putExtra(Constants.KNOWLEDGE_DATA, mAdapter.getData().get(position));
        startActivity(intent);
    }
}
