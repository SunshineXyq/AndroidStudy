package com.sunshinexu.mobilelearn.activity.main.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.main.adapter.SearchHistoryAdapter;
import com.sunshinexu.mobilelearn.activity.main.bean.HistoryData;
import com.sunshinexu.mobilelearn.activity.main.bean.HotSearchData;
import com.sunshinexu.mobilelearn.activity.main.contract.SearchContract;
import com.sunshinexu.mobilelearn.activity.main.presenter.SearchPresenter;
import com.sunshinexu.mobilelearn.base.activity.BaseActivity;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * 搜索主页面
 */
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tfl_hot_data)
    TagFlowLayout tfl_hot_data;
    @BindView(R.id.rv_history_search)
    RecyclerView rv_history_search;
    @BindView(R.id.tv_clear_history)
    TextView tv_clear_history;
    private List<HotSearchData> mHotSearchDataList;
    private SearchHistoryAdapter mAdapter;
    private List<HistoryData> mSearchHistoryList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        getWindow().setNavigationBarColor(getResources().getColor(R.color.navigation_bar));
        toolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initView() {
        mSearchHistoryList = new ArrayList<>();
        mAdapter = new SearchHistoryAdapter(R.layout.item_search_history, mSearchHistoryList);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
                return;
            }
            goToSearchResult(mAdapter.getData().get(position).getData());
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            clickChildEvent(view, position);
        });
        rv_history_search.setLayoutManager(new LinearLayoutManager(this));
        rv_history_search.setHasFixedSize(true);
        mAdapter.bindToRecyclerView(rv_history_search);
        mAdapter.setEmptyView(R.layout.search_empty_view);
        tv_clear_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.clearAllHistoryData();
                mSearchHistoryList.clear();
                mAdapter.replaceData(mSearchHistoryList);
            }
        });
    }

    @Override
    protected void initEventAndData() {
        mHotSearchDataList = new ArrayList<>();
        mPresenter.getSearchData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadAllHistoryData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search_button);
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setMaxWidth(Integer.MAX_VALUE);
        mSearchView.onActionViewExpanded();
        mSearchView.setQueryHint(getString(R.string.search_more));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                goToSearchResult(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setSubmitButtonEnabled(true);

        Field field;
        try {
            field = mSearchView.getClass().getDeclaredField("mGoButton");
            field.setAccessible(true);
            ImageView mGoButton = (ImageView) field.get(mSearchView);
            mGoButton.setImageResource(R.drawable.ic_search);

            field = mSearchView.getClass().getDeclaredField("mSearchSrcTextView");
            field.setAccessible(true);
            TextView textView = (TextView) field.get(mSearchView);
            textView.setTextColor(getResources().getColor(R.color.Grey980));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 点击热门关键词
     * @param searchString
     */

    private void goToSearchResult(String searchString) {
        mPresenter.addHistoryData(searchString);
        Intent intent = new Intent(SearchActivity.this, ItemActivity.class);
        intent.putExtra("Type", Constants.TYPE_SEARCH);
        intent.putExtra(Constants.TYPE_TITLE, searchString);
        startActivity(intent);
    }

    /**
     * 删除一条记录
     *
     * @param view
     * @param position
     */
    private void clickChildEvent(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }
        switch (view.getId()) {
            case R.id.iv_clear:
                mPresenter.deleteHistoryDataById(mAdapter.getData().get(position).getId());
                mAdapter.remove(position);
                break;
            default:
                break;
        }
    }

    /**
     * 展示热门关键词
     * @param searchData 关键词
     */

    @Override
    public void showTopSearchData(List<HotSearchData> searchData) {
        mHotSearchDataList = searchData;
        tfl_hot_data.setAdapter(new TagAdapter<HotSearchData>(searchData) {
            @Override
            public View getView(FlowLayout parent, int position, HotSearchData hotSearchData) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this)
                        .inflate(R.layout.flow_layout_website, parent, false);
                if (hotSearchData != null) {
                    tv.setText(hotSearchData.getName());
                    tv.setTextColor(getRandomColor());
                }
                return tv;
            }
        });

        tfl_hot_data.setOnTagClickListener((view, position1, parent1) -> {
            goToSearchResult(mHotSearchDataList.get(position1).getName().trim());
            return true;
        });
    }

    /**
     * 展示搜索历史数据
     * @param historyDataList 历史数据
     */
    @Override
    public void showHistoryData(List<HistoryData> historyDataList) {
        mSearchHistoryList = historyDataList;
        mAdapter.replaceData(historyDataList);
    }


    /**
     * 随机获取颜色
     * @return 颜色
     */
    public static int getRandomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red;
        int green;
        int blue;
        red = random.nextInt(190);
        green = random.nextInt(190);
        blue = random.nextInt(190);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red, green, blue);
    }
}
