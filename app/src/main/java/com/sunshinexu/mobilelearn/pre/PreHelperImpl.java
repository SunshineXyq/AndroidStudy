package com.sunshinexu.mobilelearn.pre;

import android.content.Context;
import android.content.SharedPreferences;

import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.core.constant.Constants;

import javax.inject.Inject;

public class PreHelperImpl implements PreHelper {
    private final SharedPreferences sp;
    @Inject
    PreHelperImpl(){
        sp = MobileLearnApp.getContext().getSharedPreferences(Constants.MY_SP, Context.MODE_PRIVATE);
    }
    @Override
    public void setLoginStatus(boolean isLogin) {
        sp.edit().putBoolean(Constants.LOGIN_STATUS,isLogin).apply();
    }

    @Override
    public boolean getLoginStatus() {
        return sp.getBoolean(Constants.LOGIN_STATUS,false);
    }

    @Override
    public void setLoginAccount(String account) {
        sp.edit().putString(Constants.ACCOUNT,account).apply();
    }

    @Override
    public String getLoginAccount() {
        return sp.getString(Constants.ACCOUNT,"");
    }

    @Override
    public void setNightMode(boolean isNightMode) {

    }

    @Override
    public boolean isNightMode() {
        return false;
    }
}
