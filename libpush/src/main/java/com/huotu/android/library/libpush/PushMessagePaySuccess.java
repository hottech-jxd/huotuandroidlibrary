package com.huotu.android.library.libpush;

/**
 * Created by Administrator on 2016/4/28.
 */
public class PushMessagePaySuccess  extends  PushMessage{
    private  String orderId;
    private  String orderName;
    private  String money;
    private String  payTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
