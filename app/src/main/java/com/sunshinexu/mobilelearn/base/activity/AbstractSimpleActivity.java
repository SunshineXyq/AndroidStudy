package com.sunshinexu.mobilelearn.base.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;


import com.gyf.barlibrary.ImmersionBar;
import com.sunshinexu.mobilelearn.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class AbstractSimpleActivity extends SupportActivity {

    private Unbinder unbinder;
    private static final String TAG = "AbstractSimpleActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //沉浸式状态栏
        ImmersionBar.with(this)
                .statusBarView(findViewById(R.id.status_bar)) //解决状态栏与布局重叠问题
                .keyboardEnable(true)
                .init();
        unbinder = ButterKnife.bind(this);
        Log.d(TAG, "onCreate: ");
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
