package com.sunshinexu.mobilelearn.pre;

public interface PreHelper {
    void setLoginStatus(boolean isLogin);
    boolean getLoginStatus();

    void setLoginAccount(String account);
    String getLoginAccount();

    void setNightMode(boolean isNightMode);
    boolean isNightMode();
}
