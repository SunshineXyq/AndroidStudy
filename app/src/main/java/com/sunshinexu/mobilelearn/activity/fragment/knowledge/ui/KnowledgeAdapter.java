package com.sunshinexu.mobilelearn.activity.fragment.knowledge.ui;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.bean.KnowledgeSystemData;

import java.util.List;


public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeSystemData, BaseViewHolder> {



    public KnowledgeAdapter(int layoutResId, @Nullable List<KnowledgeSystemData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeSystemData item) {
        if(item.getName() == null) {
            return;
        }
        helper.setText(R.id.knowledge_title, item.getName());
        if (item.getChildren() == null) {
            return;
        }
        StringBuilder childTitles = new StringBuilder();
        for (KnowledgeSystemData data: item.getChildren()) {
            childTitles.append(data.getName()).append("   ");
        }
        helper.setText(R.id.title_child, childTitles.toString());
    }
}
