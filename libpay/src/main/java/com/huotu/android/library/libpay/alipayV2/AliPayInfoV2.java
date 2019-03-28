package com.huotu.android.library.libpay.alipayV2;

/**
 *
 * Created by Administrator on 2016/8/1.
 */
public class AliPayInfoV2 {

    /**
     * 支付宝支付业务：入参app_id
     */
    private String appId = "";
    /** *
     * 支付宝账户登录授权业务：入参pid值
     */
    private String pId = "";
    /** *
     * 支付宝账户登录授权业务：入参target_id值
     */
    private String TargetId = "";
    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    private String rsa2_private = "";
    private String rsa_private = "";
    /**
     * 服务器异步通知页面路径
     * 支付宝服务器主动通知商户网站里指定的页面http路径。
     */
    private String notifyUrl;

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getAppId() {
        return appId;
    }

    public String getpId() {
        return pId;
    }

    public String getTargetId() {
        return TargetId;
    }

    public  String getRsa2_private() {
        return rsa2_private;
    }

    public  String getRsa_private() {
        return rsa_private;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public void setTargetId(String targetId) {
        TargetId = targetId;
    }

    public void setRsa2_private(String rsa2_private) {
        this.rsa2_private = rsa2_private;
    }

    public void setRsa_private(String rsa_private) {
        this.rsa_private = rsa_private;
    }
}
