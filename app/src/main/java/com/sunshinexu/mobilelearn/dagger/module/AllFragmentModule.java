package com.sunshinexu.mobilelearn.dagger.module;

import com.sunshinexu.mobilelearn.activity.fragment.homepager.ui.HomePageFragment;
import com.sunshinexu.mobilelearn.dagger.component.BaseFragmentComponent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 下一步添加到 AppComponent 中
 */

@Module(subcomponents = BaseFragmentComponent.class)
public abstract class AllFragmentModule {

    @ContributesAndroidInjector(modules = HomepageFragmentModule.class)
    abstract HomePageFragment contributeHomepageFragmentInjector();

//    @ContributesAndroidInjector(modules = KnowledgeFragmentModule.class)
//    abstract HomePageFragment contributeKnowledgeFragmentInjector();
//
//    @ContributesAndroidInjector(modules = NavigationFragmentModule.class)
//    abstract HomePageFragment contributeNavigationFragmentInjector();
//
//    @ContributesAndroidInjector(modules = ProjectFragmentModule.class)
//    abstract HomePageFragment contributeProjectFragmentInjector();
//
//    @ContributesAndroidInjector(modules = PublicNumFragmentModule.class)
//    abstract HomePageFragment contributePublicNumFragmentInjector();
}
