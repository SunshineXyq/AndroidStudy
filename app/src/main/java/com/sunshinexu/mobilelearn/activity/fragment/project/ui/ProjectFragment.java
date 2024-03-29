package com.sunshinexu.mobilelearn.activity.fragment.project.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.project.bean.ProjectData;
import com.sunshinexu.mobilelearn.activity.fragment.project.contract.ProjectContract;
import com.sunshinexu.mobilelearn.activity.fragment.project.presenter.ProjectPresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * 项目模块主页面
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R.id.tb_project)
    TabLayout tb_project;
    @BindView(R.id.vp_project)
    ViewPager vp_project;

    private SparseArray<ProjectListFragment> fragmentSparseArray = new SparseArray<>();
    private List<ProjectData> mProjectTreeData;

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getProjectData();
    }

    /**
     * 加载项目列表
     * @param projectData 列表数据
     */
    @Override
    public void showProjectData(List<ProjectData> projectData) {
        mProjectTreeData = projectData;
        vp_project.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                ProjectListFragment projectListFragment = fragmentSparseArray.get(position);
                if (projectListFragment == null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("projectId", mProjectTreeData.get(position).getId());
                    projectListFragment = ProjectListFragment.newInstance(bundle);
                    fragmentSparseArray.put(position, projectListFragment);
                    return projectListFragment;
                } else {
                    return projectListFragment;
                }
            }

            @Override
            public int getCount() {
                return projectData.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return Html.fromHtml(mProjectTreeData.get(position).getName());
            }
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            }

        });
        vp_project.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tb_project));
        tb_project.setupWithViewPager(vp_project);
        tb_project.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vp_project));
        tb_project.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp_project.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        if (fragmentSparseArray != null) {
            fragmentSparseArray.clear();
            fragmentSparseArray = null;
        }
        if (mProjectTreeData != null) {
            mProjectTreeData.clear();
            mProjectTreeData = null;
        }
        super.onDestroyView();
    }
}
