package com.huotu.android.library.libpush;

/**
 * Created by Administrator on 2016/4/28.
 */
public class PushMessageGetRedPackets  extends  PushMessage{
    private String name;
    private String   money;
    private String          expireTime;
    private String  desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
