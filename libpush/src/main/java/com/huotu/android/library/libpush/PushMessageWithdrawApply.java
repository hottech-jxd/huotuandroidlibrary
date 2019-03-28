package com.huotu.android.library.libpush;

/**
 * Created by Administrator on 2016/4/28.
 */
public class PushMessageWithdrawApply  extends  PushMessage{
    private String username;//(用户登录名)
    private String money;
    private String date;
    private String status;
    private String desc;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
