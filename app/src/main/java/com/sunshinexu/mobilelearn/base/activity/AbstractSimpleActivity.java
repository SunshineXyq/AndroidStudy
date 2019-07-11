package com.sunshinexu.mobilelearn.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.gyf.barlibrary.ImmersionBar;
import com.sunshinexu.mobilelearn.R;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class AbstractSimpleActivity extends SupportActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutId());
        ImmersionBar.with(this)
                .statusBarView(findViewById(R.id.status_bar))
                .keyboardEnable(true)
                .init();
    }

    protected abstract int getLayoutId();
}
