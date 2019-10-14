package com.sunshinexu.mobilelearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.sunshinexu.mobilelearn.activity.fragment.homepager.ui.HomePageFragment;
import com.sunshinexu.mobilelearn.activity.fragment.knowledge.ui.KnowledgeFragment;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.ui.NavigationFragment;
import com.sunshinexu.mobilelearn.activity.fragment.project.ui.ProjectFragment;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.ui.PublicNumFragment;
import com.sunshinexu.mobilelearn.activity.main.ui.ItemActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.LoginActivity;
import com.sunshinexu.mobilelearn.activity.main.ui.SearchActivity;
import com.sunshinexu.mobilelearn.base.activity.BaseActivity;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.activity.main.contract.MainContract;
import com.sunshinexu.mobilelearn.activity.main.presenter.MainPresenter;


import butterknife.BindView;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private static final String TAG = "MainActivity";
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.navigation_view)
    NavigationView navigation_view;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottom_navigation_view;

    private int currentFgIndex = 0;
    private int lastFgIndex = -1;

    private HomePageFragment homePageFragment;
    private NavigationFragment navigationFragment;
    private KnowledgeFragment knowledgeFragment;
    private PublicNumFragment publicNumFragment;
    private ProjectFragment projectFragment;
    private TextView tv_welcome;
    private TextView tv_login_account;
    private long clickTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化 Toolbar
     */
    @Override
    protected void initToolbar() {
        Log.d(TAG, "initToolbar: ");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            toolbar_title.setText(R.string.home_page);
        }
        getWindow().setNavigationBarColor(getResources().getColor(R.color.navigation_bar));
    }

    @Override
    protected void initView() {
        initDrawerLayout();
        showFragment(currentFgIndex);
        initNavigationView();
        initBottomNavigationView();

    }

    /**
     * 初始化底部导航栏
     */
    private void initBottomNavigationView() {
        bottom_navigation_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_home_pager:
                        showFragment(Constants.FRAGMENT_HOME_PAGE);
                        break;
                    case R.id.tab_knowledge:
                        showFragment(Constants.FRAGMENT_KNOWLEDGE);
                        break;
                    case R.id.tab_navigation:
                        showFragment(Constants.FRAGMENT_NAIVIGATION);
                        break;
                    case R.id.tab_project:
                        showFragment(Constants.FRAGMENT_PRPJECT);
                        break;
                    case R.id.tab_public_num:
                        showFragment(Constants.FRAGMENT_PUBLIC_NUM);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * DrawerLayout
     */
    private void initNavigationView() {
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_login:
                        startLoginActivity();
                        break;
                    case R.id.item_collect:
                        if (mPresenter.getLoginStatus()) {   //判断当前是否处于登录状态
                            Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                            intent.putExtra("Type", Constants.TYPE_COLLECT);
                            startActivity(intent);
                        } else {
                            startLoginActivity();            //未登录跳转登录页
                            Toast.makeText(MainActivity.this, getString(R.string.please_login),
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.item_about:
                        Intent intentAbout = new Intent(MainActivity.this, ItemActivity.class);
                        intentAbout.putExtra("Type", Constants.TYPE_ABOUT);
                        startActivity(intentAbout);
                        break;
                    case R.id.item_logout:
                        mPresenter.logout();
                        break;
                }
                return true;
            }
        });
        tv_welcome = navigation_view.getHeaderView(0).findViewById(R.id.tv_welcome);
        tv_login_account = navigation_view.getHeaderView(0).findViewById(R.id.tv_login_account);
        if (mPresenter.getLoginStatus()) {
            tv_welcome.setVisibility(View.VISIBLE);
            tv_login_account.setText(mPresenter.getLoginAccount());
            navigation_view.getMenu().findItem(R.id.item_login).setVisible(false);
            navigation_view.getMenu().findItem(R.id.item_logout).setVisible(true);
        } else {
            tv_welcome.setVisibility(View.INVISIBLE);
            tv_login_account.setText("");
            navigation_view.getMenu().findItem(R.id.item_login).setVisible(true);
            navigation_view.getMenu().findItem(R.id.item_logout).setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Toolbar 两个按钮
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_usage:
                intent = new Intent(MainActivity.this, ItemActivity.class);
                intent.putExtra("Type", Constants.TYPE_WEBSITE);
                startActivity(intent);
                break;
            case R.id.action_search:
                intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 展示对应 Fragment
     * @param index 展示Fragment的索引
     */
    private void showFragment(int index) {
        Log.d(TAG, "showFragment: ");
        currentFgIndex = index;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        lastFgIndex = index;
        switch (index) {
            case Constants.FRAGMENT_HOME_PAGE:
                toolbar_title.setText(R.string.home_page);
                if (homePageFragment == null) {
                    homePageFragment = HomePageFragment.newInstance();
                    transaction.add(R.id.fragment_root, this.homePageFragment);
                }
                transaction.show(this.homePageFragment);
                break;
            case Constants.FRAGMENT_PUBLIC_NUM:
                toolbar_title.setText(R.string.public_num);
                if (publicNumFragment == null) {
                    publicNumFragment = PublicNumFragment.newInstance();
                    transaction.add(R.id.fragment_root, this.publicNumFragment);
                }
                transaction.show(this.publicNumFragment);
                break;
            case Constants.FRAGMENT_KNOWLEDGE:
                toolbar_title.setText(R.string.knowledge);
                if (knowledgeFragment == null) {
                    knowledgeFragment = KnowledgeFragment.newInstance();
                    transaction.add(R.id.fragment_root, this.knowledgeFragment);
                }
                transaction.show(this.knowledgeFragment);
                break;
            case Constants.FRAGMENT_PRPJECT:
                toolbar_title.setText(R.string.project);
                if (projectFragment == null) {
                    projectFragment = ProjectFragment.newInstance();
                    transaction.add(R.id.fragment_root, this.projectFragment);
                }
                transaction.show(this.projectFragment);
                break;
            case Constants.FRAGMENT_NAIVIGATION:
                toolbar_title.setText(R.string.navigation);
                if (navigationFragment == null) {
                    navigationFragment = NavigationFragment.newInstance();
                    transaction.add(R.id.fragment_root, this.navigationFragment);
                }
                transaction.show(this.navigationFragment);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏当前 Fragment
     * @param transaction 事务
     */
    private void hideFragment(FragmentTransaction transaction) {
        switch (lastFgIndex) {
            case Constants.FRAGMENT_HOME_PAGE:
                if (homePageFragment != null) {
                    transaction.hide(homePageFragment);
                }
                break;
            case Constants.FRAGMENT_NAIVIGATION:
                if (navigationFragment != null) {
                    transaction.hide(navigationFragment);
                }
                break;
            case Constants.FRAGMENT_KNOWLEDGE:
                if (knowledgeFragment != null) {
                    transaction.hide(knowledgeFragment);
                }
                break;
            case Constants.FRAGMENT_PUBLIC_NUM:
                if (publicNumFragment != null) {
                    transaction.hide(publicNumFragment);
                }
                break;
            case Constants.FRAGMENT_PRPJECT:
                if (projectFragment != null) {
                    transaction.hide(projectFragment);
                }
                break;
            default:
                break;
        }
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.open_drawer, R.string.close_drawer);
        toggle.syncState();
        drawer_layout.addDrawerListener(toggle);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
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
        tv_welcome.setVisibility(View.VISIBLE);
        tv_login_account.setText(mPresenter.getLoginAccount());
        //隐藏登录按钮
        navigation_view.getMenu().findItem(R.id.item_login).setVisible(false);
        //显示注销按钮
        navigation_view.getMenu().findItem(R.id.item_logout).setVisible(true);
    }

    @Override
    public void handleLogoutSuccess() {
        tv_welcome.setVisibility(View.INVISIBLE);
        tv_login_account.setText("");
        navigation_view.getMenu().findItem(R.id.item_login).setVisible(true);
        navigation_view.getMenu().findItem(R.id.item_logout).setVisible(false);
        Toast.makeText(this,getString(R.string.logout_success),Toast.LENGTH_SHORT).show();
    }
    /**
     * 处理回退事件
     */
    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - clickTime) > Constants.DOUBLE_INTERVAL_TIME) {
                Toast.makeText(MainActivity.this, getString(R.string.double_click_exit_toast),Toast.LENGTH_SHORT).show();
                clickTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
    }
}
