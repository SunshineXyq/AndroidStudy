package com.sunshinexu.mobilelearn;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;


import com.sunshinexu.mobilelearn.activity.fragment.homepager.ui.HomePageFragment;
import com.sunshinexu.mobilelearn.base.activity.BaseActivity;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.activity.main.contract.MainContract;
import com.sunshinexu.mobilelearn.activity.main.presenter.MainPresenter;


import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private static final String TAG = "MainActivity";
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView barTitle;

    private int currentFgIndex = 0;
    private int lastFgIndex = -1;

    private HomePageFragment homePagerFragment;
//    private NavigationFragment navigationFragment;
//    private KnowledgeFragment knowledgeFragment;
//    private PublicNumFragment publicNumFragment;
//    private ProjectFragment projectFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            barTitle.setText(R.string.home_page);
        }
    }

    @Override
    protected void initView() {
        initDrawerLayout();
        showFragment(currentFgIndex);
//        initNavigationView();
//        initBottomNavigationView();
    }

    private void showFragment(int index) {
        currentFgIndex = index;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        lastFgIndex = index;
        switch (index) {
            case Constants.FRAGMENT_HOME_PAGE:
                if (homePagerFragment == null) {
                }
        }
    }

    private void hideFragment(FragmentTransaction transaction) {
        switch (lastFgIndex) {
            case Constants.FRAGMENT_HOME_PAGE:
                if (homePagerFragment != null) {
                    transaction.hide(homePagerFragment);
                }
                break;
//            case Constants.FRAGMENT_NAIVIGATION:
//                if (navigationFragment != null) {
//                    transaction.hide(navigationFragment);
//                }
//                break;
//            case Constants.FRAGMENT_KNOWLEDGE:
//                if (knowledgeFragment != null) {
//                    transaction.hide(knowledgeFragment);
//                }
//                break;
//            case Constants.FRAGMENT_PUBLIC_NUM:
//                if (publicNumFragment != null) {
//                    transaction.hide(publicNumFragment);
//                }
//                break;
//            case Constants.FRAGMENT_PRPJECT:
//                if (projectFragment != null) {
//                    transaction.hide(projectFragment);
//                }
//                break;
            default:
                break;
        }
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    protected void initEventAndData() {

    }


    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showNoNetwork() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void handleLoginSuccess() {

    }

    @Override
    public void handleLogoutSuccess() {

    }
}
