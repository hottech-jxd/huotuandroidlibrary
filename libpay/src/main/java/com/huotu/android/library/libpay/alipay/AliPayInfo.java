package com.huotu.android.library.libpay.alipay;

/**
 *
 * Created by Administrator on 2016/8/1.
 */
public class AliPayInfo {
    /**
     * 合作者身份ID
     * 签约的支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
     */
    private String partner;
    /**
     * 卖家支付宝账号
     * 卖家支付宝账号（邮箱或手机号码格式）或其对应的支付宝唯一用户号（以2088开头的纯16位数字）。
     */
    private String sellerId;
    /**
     * 服务器异步通知页面路径
     * 支付宝服务器主动通知商户网站里指定的页面http路径。
     */
    private String notifyUrl;
    /**
     * 商户私钥，pkcs8格式
     *
     */
    private String rsaPrivate;

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getRsaPrivate() {
        return rsaPrivate;
    }

    public void setRsaPrivate(String rsaPrivate) {
        this.rsaPrivate = rsaPrivate;
    }
}
