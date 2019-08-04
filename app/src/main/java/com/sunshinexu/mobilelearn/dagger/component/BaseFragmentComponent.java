package com.sunshinexu.mobilelearn.dagger.component;

import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * 引入appComponent中的AndroidInjectionModule，每个继承 BaseFragment 的 Fragment 都继承同一个组件
 * 下一步创建 AllFragmentModule
 */

@Subcomponent(modules = AndroidInjectionModule.class)
public interface BaseFragmentComponent extends AndroidInjector<BaseFragment> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseFragment>{

    }

}
