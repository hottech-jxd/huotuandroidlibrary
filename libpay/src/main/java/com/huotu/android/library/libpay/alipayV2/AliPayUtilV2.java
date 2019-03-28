package com.huotu.android.library.libpay.alipayV2;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.huotu.android.library.libpay.alipay.AliOrderInfo;

import java.util.Map;

/**
 * 支付宝支付工具类
 */
public class AliPayUtilV2 {
    public static final int  SDK_Ali_PAY_V2_FLAG = 6001;
    private Handler  handler = null;
    private Activity context = null;
    private AliPayInfoV2 aliPayInfoV2;

    public AliPayUtilV2(Activity context, Handler handler , AliPayInfoV2 aliPayInfoV2 ) {
        this.handler = handler;
        this.context = context;
        this.aliPayInfoV2 = aliPayInfoV2;
    }

    public void payV2(final AliOrderInfo aliOrderInfo) {
        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = ( aliPayInfoV2.getRsa2_private() !=null && aliPayInfoV2.getRsa2_private().length() > 0);
        Map<String, String> params = OrderInfoUtilV2.buildOrderParamMap(aliPayInfoV2 , rsa2 , aliOrderInfo );
        String orderParam = OrderInfoUtilV2.buildOrderParam(params);

        String privateKey = rsa2 ? aliPayInfoV2.getRsa2_private() : aliPayInfoV2.getRsa_private();
        String sign = OrderInfoUtilV2.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(context );
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_Ali_PAY_V2_FLAG;
                AliPayResultV2 aliPayResultV2 = new AliPayResultV2(result , aliOrderInfo );
                msg.obj = aliPayResultV2;
                handler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
