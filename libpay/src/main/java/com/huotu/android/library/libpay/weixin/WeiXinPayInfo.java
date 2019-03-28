package com.huotu.android.library.libpay.weixin;

/**
 *
 * Created by Administrator on 2016/7/27.
 */
public class WeiXinPayInfo {
    /**
     * 微信开放平台的 appid
     */
    private String appId;
    /**
     * 微信商户平台的 id
     */
    private String partnerId;
    /**
     * 签名密钥
     */
    private String appSecret;
    /**
     * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
     */
    private String notifyUrl;

    public WeiXinPayInfo(String appId , String partnerId , String appSecret, String notifyUrl){
        this.appId=appId;
        this.partnerId = partnerId;
        this.appSecret = appSecret;
        this.notifyUrl=notifyUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
