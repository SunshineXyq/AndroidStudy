package com.sunshinexu.mobilelearn.activity.fragment.navigation.ui;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.bean.NavigationData;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.contract.NavigationContract;
import com.sunshinexu.mobilelearn.activity.fragment.navigation.presenter.NavigationPresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {

    @BindView(R.id.rv_navigation)
    RecyclerView rv_navigation;
    @BindView(R.id.vtl_navigation)
    VerticalTabLayout vtl_navigation;
    private NavigationAdapter adapter;
    private boolean scroll;
    private int index;
    private boolean isClickTab;
    private LinearLayoutManager mLinearLayoutManager;

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initView() {
        List<NavigationData> navigationDataList = new ArrayList<>();
        adapter = new NavigationAdapter(R.layout.item_navigation, navigationDataList);
        DividerItemDecoration divider = new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(_mActivity, R.drawable.shape_hp_rv_line));
        rv_navigation.setLayoutManager(new LinearLayoutManager(_mActivity));
        rv_navigation.setHasFixedSize(true);
        rv_navigation.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.getNavigationData();
    }

    @Override
    public void showNavigationData(List<NavigationData> navigationData) {
        vtl_navigation.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationData == null ? 0 : navigationData.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int i) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int i) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int i) {
                return new TabView.TabTitle.Builder()
                        .setContent(navigationData.get(i).getName())
                        .setTextColor(ContextCompat.getColor(_mActivity, R.color.colorAccent),
                                ContextCompat.getColor(_mActivity, R.color.Grey500))
                        .build();
            }

            @Override
            public int getBackground(int i) {
                return -1;
            }
        });
        adapter.replaceData(navigationData);

        rv_navigation.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (scroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView();
                }
                rightLinkageLeft(newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (scroll) {
                    scrollRecyclerView();
                }
            }
        });

        vtl_navigation.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tabView, int i) {
                isClickTab = true;
                selectTag(i);
            }

            @Override
            public void onTabReselected(TabView tabView, int i) {
            }
        });
    }

    /**
     * 滑动 RecyclerView
     */
    private void scrollRecyclerView() {
        scroll = false;
        int indexDistance = index - mLinearLayoutManager.findFirstVisibleItemPosition();
        if (indexDistance >= 0 && indexDistance < rv_navigation.getChildCount()) {
            int top = rv_navigation.getChildAt(indexDistance).getTop();
            rv_navigation.smoothScrollBy(0, top);
        }
    }

    private void rightLinkageLeft(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                isClickTab = false;
                return;
            }
            int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
            if (index != firstPosition) {
                index = firstPosition;
                setChecked(index);
            }
        }
    }

    private void setChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (vtl_navigation == null) {
                return;
            }
            vtl_navigation.setTabSelected(index);
        }
        index = position;
    }
    private void selectTag(int i) {
        index = i;
        rv_navigation.stopScroll();
        smoothScrollToPosition(i);
    }
    private void smoothScrollToPosition(int currentPosition) {
        int firstPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLinearLayoutManager.findLastVisibleItemPosition();
        if (currentPosition <= firstPosition) {
            rv_navigation.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = rv_navigation.getChildAt(currentPosition - firstPosition).getTop();
            rv_navigation.smoothScrollBy(0, top);
        } else {
            rv_navigation.smoothScrollToPosition(currentPosition);
            scroll = true;
        }
    }
}
