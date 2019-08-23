package com.sunshinexu.mobilelearn.activity.fragment.publicnum.ui;

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

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.bean.PublicNumData;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.contract.PublicNumContract;
import com.sunshinexu.mobilelearn.activity.fragment.publicnum.presenter.PublicNumPresenter;
import com.sunshinexu.mobilelearn.base.fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class PublicNumFragment extends BaseFragment<PublicNumPresenter> implements PublicNumContract.View {

    @BindView(R.id.tb_public_num)
    TabLayout tb_public_num;
    @BindView(R.id.vp_public_num)
    ViewPager vp_public_num;

    private List<PublicNumData> mPublicNumList;
    private SparseArray<PersonalPublicNumFragment> personalFragment = new SparseArray<>();
    private PersonalPublicNumFragment currentFragment;

    public static PublicNumFragment newInstance() {
        return new PublicNumFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_public_num;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getPublicNumData();
    }

    @Override
    public void showPublicNumData(List<PublicNumData> publicNumData) {
        mPublicNumList = publicNumData;
        vp_public_num.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                PersonalPublicNumFragment personalPublicNumFragment = personalFragment.get(position);
                if (personalPublicNumFragment == null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("publicNumId", publicNumData.get(position).getId());
                    personalPublicNumFragment =
                            PersonalPublicNumFragment.newInstance(bundle);
                    return personalPublicNumFragment;
                } else {
                    return personalPublicNumFragment;
                }
            }

            @Override
            public int getCount() {
                return mPublicNumList == null ? 0 : mPublicNumList.size();
            }


            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return Html.fromHtml(mPublicNumList.get(position).getName());
            }

            //不添加会内存溢出
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            }
        });
        vp_public_num.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tb_public_num));
        tb_public_num.setupWithViewPager(vp_public_num);
        tb_public_num.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vp_public_num));
        tb_public_num.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp_public_num.setCurrentItem(tab.getPosition(), false);
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
        super.onDestroyView();
        if (mPublicNumList != null) {
            mPublicNumList.clear();
            mPublicNumList = null;
        }
        if (personalFragment != null) {
            personalFragment.clear();
            personalFragment = null;
        }
    }
}
