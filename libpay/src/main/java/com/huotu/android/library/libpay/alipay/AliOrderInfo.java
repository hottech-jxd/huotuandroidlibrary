package com.huotu.android.library.libpay.alipay;

/**
 * Created by Administrator on 2016/8/1.
 */
public class AliOrderInfo {
    /**
     * 商户网站唯一订单号
     * 支付宝合作商户网站唯一订单号。
     */
    private String orderNo;
    /**
     * 商品名称
     * 商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。
     */
    private String subject;
    /**
     * 总金额
     * 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。
     */
    private Number totalfee;
    /**
     * 商品详情
     * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
     */
    private String body;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Number getTotalfee() {
        return totalfee;
    }

    public void setTotalfee(Number totalfee) {
        this.totalfee = totalfee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
