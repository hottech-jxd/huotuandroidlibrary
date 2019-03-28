package com.huotu.android.library.libpay.weixin;

/**
 * Created by Administrator on 2016/7/27.
 */
public class WeiXinOrderInfo {
    /**
     * 商户订单号
     */
    public String orderNo;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 总金额 (订单总金额，单位为分)
     */
    private int total_fee;

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }
}
