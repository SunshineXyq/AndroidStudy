package com.sunshinexu.mobilelearn.activity.fragment.publicnum.ui;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.bean.PublicNumData;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.contract.PublicNumContract;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.presenter.PublicNumPresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

import java.util.List;

public class PublicNumFragment extends BaseFragment<PublicNumPresenter> implements PublicNumContract.View {


    public static PublicNumFragment newInstance(){
        return new PublicNumFragment();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_public_num;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showPublicNumData(List<PublicNumData> publicNumData) {

    }
}
