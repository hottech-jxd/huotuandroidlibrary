package com.huotu.android.library.libpush;

/**
 * 推送消息类型
 * Created by Administrator on 2016/4/22.
 */
public enum  PushMessageType {

    DownRegisterSuccess("DownRegisterSuccess","下线会员注册成功"),
    UpgradePartner("UpgradePartner","会员升级成小伙伴"),
    PaySuccess("PaySuccess","订单支付成功"),
    SendRedPackets("SendRedPackets","小伙伴发送红包"),
    DownPaySuccess("DownPaySuccess","下线订单支付成功"),
    GetStock("GetStock","获得股数"),
    WithdrawApply("WithdrawApply","余额提现申请"),
    OrderShip("OrderShip","订单发货"),
    GetRedPackets("GetRedPackets","会员获得红包通知"),
    UpgradeDreamPartner("UpgradeDreamPartner","升级成为梦想合伙人"),
    ActiveMessage("ActiveMessage","普通消息");


    private String name;
    private String description;

    private PushMessageType(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
