package com.sunshinexu.mobilelearn.core.eventbus;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/18
 */

import java.io.Serializable;

public class RegisterEvent implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private String password;

    public RegisterEvent(String username, String password) {
        this.username = username;
        this.password = password;

    }
}
