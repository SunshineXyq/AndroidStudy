package com.sunshinexu.mobilelearn.pre;

import android.content.Context;
import android.content.SharedPreferences;

import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.core.constant.Constants;

public class PreHelperImpl implements PreHelper {
    private final SharedPreferences sp;
    PreHelperImpl(){
        sp = MobileLearnApp.getContext().getSharedPreferences(Constants.MY_SP, Context.MODE_PRIVATE);
    }
    @Override
    public void setLoginStatus(boolean isLogin) {

    }

    @Override
    public boolean getLoginStatus() {
        return false;
    }

    @Override
    public void setLoginAccount(String account) {

    }

    @Override
    public String getLoginAccount() {
        return null;
    }

    @Override
    public void setNightMode(boolean isNightMode) {

    }

    @Override
    public boolean isNightMode() {
        return false;
    }
}
