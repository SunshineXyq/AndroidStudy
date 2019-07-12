package com.sunshinexu.mobilelearn.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.sunshinexu.mobilelearn.BuildConfig;
import com.sunshinexu.mobilelearn.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class AbstractSimpleActivity extends SupportActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //暂时不起作用
        ImmersionBar.with(this)
                .statusBarView(findViewById(R.id.status_bar))
                .keyboardEnable(true)
                .init();
        unbinder = ButterKnife.bind(this);
        onViewCreate();
        initToolbar();
        initView();
        initEventAndData();
    }

    protected abstract int getLayoutId();
    protected abstract void onViewCreate();
    protected abstract void initToolbar();
    protected abstract void initView();
    protected abstract void initEventAndData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY) {
            unbinder.unbind();
            unbinder = null;
        }
        //真机连接 Hierarchy Viewer方法
//        if (BuildConfig.DEBUG) {
//            ViewServer.get(this).removeWindow(this);
//        }
    }
}
