package com.sunshinexu.mobilelearn.http.exception;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/22
 */

public class ServerException extends Exception {

    private int code;

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
