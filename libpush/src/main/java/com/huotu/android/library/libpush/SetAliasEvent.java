package com.huotu.android.library.libpush;

import cn.jpush.android.api.PushNotificationBuilder;

/**
 * Created by Administrator on 2016/4/21.
 */
public class SetAliasEvent {
    public static int SET_ALIAS_SUCCESS=0;
    public static int SET_ALIAS_FAIL=1;
    private int code;
    private String message;
    public SetAliasEvent(int code , String message){
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
