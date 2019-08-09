package com.sunshinexu.mobilelearn.activity.fragment.register.ui;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/9
 */

import com.sunshinexu.mobilelearn.activity.fragment.register.contract.RegisterContract;
import com.sunshinexu.mobilelearn.activity.fragment.register.presenter.RegisterPresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

public class RegisterFragment extends BaseFragment<RegisterPresenter> implements RegisterContract.View {

    public static RegisterFragment newInstance(){
        return new RegisterFragment();
    }
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void registerSuccess() {

    }
}
