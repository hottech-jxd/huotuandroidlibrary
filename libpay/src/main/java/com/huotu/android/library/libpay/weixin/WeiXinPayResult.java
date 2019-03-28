package com.huotu.android.library.libpay.weixin;

/**
 * Created by Administrator on 2016/7/27.
 */
public class WeiXinPayResult {
    private int code;
    private String message;
    private WeiXinOrderInfo orderInfo;

    public WeiXinPayResult(int code , String message , WeiXinOrderInfo orderInfo){
        this.code=code;
        this.message = message;
        this.orderInfo = orderInfo;
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

    public WeiXinOrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(WeiXinOrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
