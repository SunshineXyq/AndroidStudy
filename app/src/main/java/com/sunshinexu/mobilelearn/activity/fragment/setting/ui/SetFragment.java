package com.sunshinexu.mobilelearn.activity.fragment.setting.ui;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/19
 */

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.sunshinexu.mobilelearn.R;
import com.sunshinexu.mobilelearn.utils.CacheUtils;


public class SetFragment extends PreferenceFragmentCompat {

    public static SetFragment newInstance(){
        return new SetFragment();
    }
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.fragment_set);
        findPreference("clearCache").setSummary(CacheUtils.getTotalCacheSize());
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        switch (preference.getKey()) {
            case "clearCache":
                CacheUtils.clearAllCache();
                findPreference(preference.getKey()).setSummary(CacheUtils.getTotalCacheSize());
                break;
            default:
                break;
        }
        return super.onPreferenceTreeClick(preference);
    }

}
