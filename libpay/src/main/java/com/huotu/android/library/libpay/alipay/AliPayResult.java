package com.huotu.android.library.libpay.alipay;

/**
 * Created by Administrator on 2016/8/1.
 */
public class AliPayResult {
    private AliOrderInfo aliOrderInfo;
    private String result;
    private PayResult payResult;

    public AliPayResult(AliOrderInfo aliOrderInfo, String result){
        this.aliOrderInfo = aliOrderInfo;
        this.result=result;
        this.payResult = new PayResult(result);
    }

    public AliOrderInfo getAliOrderInfo() {
        return aliOrderInfo;
    }

    public void setAliOrderInfo(AliOrderInfo aliOrderInfo) {
        this.aliOrderInfo = aliOrderInfo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public PayResult getPayResult() {
        return payResult;
    }

    public void setPayResult(PayResult payResult) {
        this.payResult = payResult;
    }
}
