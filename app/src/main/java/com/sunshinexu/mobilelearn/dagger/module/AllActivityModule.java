package com.sunshinexu.mobilelearn.dagger.module;

import com.sunshinexu.mobilelearn.MainActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.ArticleDetailActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.ItemActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.KnowledgeDetailActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.LoginActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.SearchActivity;
import com.sunshinexu.mobilelearn.dagger.component.BaseActivityComponent;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = {BaseActivityComponent.class})
public abstract class AllActivityModule {
    //ContributesAndroidInjector 解决每个类都需要xxx.inject(this),以及类需要知道注入器
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector(modules = ArticleDetailActivityModule.class)
    abstract ArticleDetailActivity contributesArticleDetailActivityInjector();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributesLoginActivityInjector();

    @ContributesAndroidInjector(modules = KnowledgeDetailActivityModule.class)
    abstract KnowledgeDetailActivity contributesKnowledgeActivityInjector();

    @ContributesAndroidInjector(modules = ItemActivityModule.class)
    abstract ItemActivity contributesItemActivityInjector();

    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    abstract SearchActivity contributesSearchActivityInjector();

}
