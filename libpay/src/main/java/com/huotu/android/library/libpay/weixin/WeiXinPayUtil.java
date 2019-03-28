package com.huotu.android.library.libpay.weixin;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.huotu.android.library.libpay.EncryptUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * 微信App支付 包装类
 * Created by jinxiangdong on 2016/7/27.
 */
public class WeiXinPayUtil {
    static String TAG= WeiXinPayUtil.class.getName();
    public static int SUCCESS=1;
    public static int FAIL = 0;
    Context context;
    WeiXinPayInfo weiXinPayInfo;
    Handler handler;
    public static final int  SDK_WX_PAY_FLAG = 8001;

    public WeiXinPayUtil(Context context , Handler handler , WeiXinPayInfo weiXinPayInfo){
        this.context = context;
        this.handler = handler;
        this.weiXinPayInfo=weiXinPayInfo;
    }


    public void pay(final WeiXinOrderInfo weiXinOrderInfo){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                payAsync(weiXinOrderInfo);
            }
        };
        new Thread(runnable).start();
    }

    protected void payAsync( WeiXinOrderInfo weiXinOrderInfo){
        WeiXinPayResult result;
        Message message = handler.obtainMessage(SDK_WX_PAY_FLAG);

        //调用微信统一订单接口，获得预支付订单id
        Map<String, String> prePayXml = getPrePayId( weiXinOrderInfo);

        if (prePayXml == null) {
            result = new WeiXinPayResult(FAIL,"请求微信预支付订单接口失败" , weiXinOrderInfo);
            message.obj = result;
            handler.sendMessage(message);
            return;
        }

        if (prePayXml.containsKey("return_code") && prePayXml.containsKey("return_msg")){
            String returnCode = prePayXml.get("return_code");
            if (returnCode.equals("FAIL")){
                result = new WeiXinPayResult(FAIL, "return_msg=" + prePayXml.get("return_msg"), weiXinOrderInfo );
                message.obj= result;
                handler.sendMessage(message);
                return;
            }
            if( prePayXml.containsKey("result_code") ){
                String resultCode = prePayXml.get("result_code");
                if( resultCode.equals("FAIL")){
                    String errCode = prePayXml.get("err_code");
                    String errMsg = prePayXml.get("err_code_des");
                    result = new WeiXinPayResult(FAIL, "err_code=" + errCode + ",errMsg=" + errMsg , weiXinOrderInfo );
                    message.obj= result;
                    handler.sendMessage(message);
                    return;
                }else{
                    String prepayId = prePayXml.get("prepay_id");
                    sendPayReq( weiXinOrderInfo, prepayId );
                    result=new WeiXinPayResult(SUCCESS,"返回1，不能代表支付成功。", weiXinOrderInfo );
                    message.obj= result;
                    handler.sendMessage(message);
                    return;
                }
            }
        }

        result = new WeiXinPayResult(FAIL,"微信支付失败", weiXinOrderInfo);
        message.obj= result;
        handler.sendMessage(message);
        return;
    }

    private  Map<String, String> getPrePayId( WeiXinOrderInfo weiXinOrderInfo) {
        Map<String, String> prePayXml = new WeiXinGetPrePayId(weiXinPayInfo).getPrePayId(weiXinOrderInfo);
        return prePayXml;
    }

    /**
     * 调起支付接口
     * @param prePayId
     */
    private void sendPayReq( WeiXinOrderInfo weiXinOrderInfo , String prePayId ){
//        PayReq req = new PayReq();
//        req.appId = weiXinPayInfo.getAppId();
//        req.partnerId = weiXinPayInfo.getPartnerId();
//        req.prepayId = prePayId;
        String packageValue = "Sign=WXPay";
        String nonceStr = WeiXinGetPrePayId.genNonceStr();
        String timeStamp = String.valueOf(genTimeStamp());

        TreeMap<String,String> signParams = new TreeMap<>();
        signParams.put("appid",  weiXinPayInfo.getAppId());
        signParams.put("noncestr",nonceStr );
        signParams.put("package", packageValue);
        signParams.put("partnerid",  weiXinPayInfo.getPartnerId());
        signParams.put("prepayid",prePayId);
        signParams.put("timestamp", timeStamp );

        String sign = genAppSign(signParams);
//
        String extData = "{orderNo:"+ weiXinOrderInfo.getOrderNo() +"}";
//        req.extData = extData;
//
//        IWXAPI wxApi = WXAPIFactory.createWXAPI(context, null);
//        wxApi.registerApp( weiXinPayInfo.getAppId());
//        wxApi.sendReq( req );

        sendPayReq( prePayId , packageValue, nonceStr , timeStamp , sign , extData);
    }


    /**
     * 调起支付接口
     * @param prePayId
     */
    public void sendPayReq( String prePayId , String packageValue, String nonceStr , String timeStamp , String sign ,String extData ){
        PayReq req = new PayReq();
        req.appId = weiXinPayInfo.getAppId();
        req.partnerId = weiXinPayInfo.getPartnerId();
        req.prepayId = prePayId;
        req.packageValue = packageValue; //"Sign=WXPay";
        req.nonceStr = nonceStr; //WeiXinGetPrePayId.genNonceStr();
        req.timeStamp = timeStamp; //String.valueOf(genTimeStamp());
        req.sign = sign; //genAppSign(signParams);
        req.extData = extData;

        IWXAPI wxApi = WXAPIFactory.createWXAPI(context, null);
        wxApi.registerApp( weiXinPayInfo.getAppId());
        wxApi.sendReq( req );
    }


    private long genTimeStamp(){
        return System.currentTimeMillis() / 1000;
    }

    private String genAppSign( TreeMap<String,String> params) {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()){
            sb.append( key + "=" + params.get(key) + "&");
        }
        sb.append("key="+ weiXinPayInfo.getAppSecret());
        String appSign = EncryptUtil.getInstance().encryptMd532( sb.toString() );
        Log.e( TAG , appSign );
        return appSign;
    }
}
