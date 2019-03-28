package com.huotu.android.library.libpush;

/**
 * Created by Administrator on 2016/4/28.
 */
public class PushMessageUpgradePartner  extends  PushMessage {
    private String username;
    private String date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
