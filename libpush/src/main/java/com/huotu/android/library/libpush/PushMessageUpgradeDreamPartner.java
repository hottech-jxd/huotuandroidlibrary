package com.huotu.android.library.libpush;

/**
 * Created by Administrator on 2016/4/28.
 */
public class PushMessageUpgradeDreamPartner  extends  PushMessage{
    private String consumeAmount;
    private String condtionAmount;
    private String gainStockNum;
    private String upgradeTime;
    private String account;//(用户登录名)

    public String getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(String consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public String getCondtionAmount() {
        return condtionAmount;
    }

    public void setCondtionAmount(String condtionAmount) {
        this.condtionAmount = condtionAmount;
    }

    public String getGainStockNum() {
        return gainStockNum;
    }

    public void setGainStockNum(String gainStockNum) {
        this.gainStockNum = gainStockNum;
    }

    public String getUpgradeTime() {
        return upgradeTime;
    }

    public void setUpgradeTime(String upgradeTime) {
        this.upgradeTime = upgradeTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
