package com.sunshinexu.mobilelearn.activity.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sunshinexu.mobilelearn.activity.main.bean.HistoryData;

import java.util.List;


/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/20
 */

public class SearchHistoryAdapter extends BaseQuickAdapter<HistoryData, BaseViewHolder>  {

    public SearchHistoryAdapter(int layoutResId, @Nullable List<HistoryData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryData item) {

    }
}
