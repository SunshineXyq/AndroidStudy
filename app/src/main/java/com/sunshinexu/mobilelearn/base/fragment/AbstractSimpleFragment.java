package com.sunshinexu.mobilelearn.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class AbstractSimpleFragment extends SupportFragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        unbinder = ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null && unbinder != Unbinder.EMPTY ) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    /**
     *Fragment 销毁时进行内存泄漏检测
     */

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MobileLearnApp.getRefWatcher(_mActivity);
        refWatcher.watch(this);
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();
}
