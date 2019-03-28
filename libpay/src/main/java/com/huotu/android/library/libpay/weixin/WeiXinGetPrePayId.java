package com.huotu.android.library.libpay.weixin;

import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

import com.huotu.android.library.libpay.EncryptUtil;
import com.huotu.android.library.libpay.HttpUtil;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * 获得 预支付订单id
 * Created by jinxiangdong on 2016/7/27.
 */
class WeiXinGetPrePayId {
    static String TAG = WeiXinGetPrePayId.class.getName();
    //微信统一下单接口
    static final String WX_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    WeiXinPayInfo weiXinPayInfo;

    public WeiXinGetPrePayId(WeiXinPayInfo weiXinPayInfo){
        this.weiXinPayInfo=weiXinPayInfo;
    }

    /**
     *
     * @param weiXinOrderInfo
     * @return
     */
    public Map<String,String> getPrePayId(WeiXinOrderInfo weiXinOrderInfo) {
        try {
            String xmlString = genProductArgs(weiXinOrderInfo);
            byte[] buffer = HttpUtil.httpPost(WX_ORDER_URL, xmlString);
            if (buffer == null) return null;
            String content = new String(buffer);
            Map<String, String> result = decodeXml(content);
            return result;
        } catch (UnsupportedEncodingException unex) {
            unex.printStackTrace();
            return null;
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return null;
        } catch (XmlPullParserException xmlex) {
            xmlex.printStackTrace();
            return null;
        }
    }

    public Map<String, String> decodeXml(String content) throws XmlPullParserException,IOException {

        Map<String, String> xml = new HashMap<>();
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(new StringReader(content));
        int event = parser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            String nodeName = parser.getName();
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    if (!"xml".equals(nodeName)) {
                        xml.put(nodeName, parser.nextText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    break;
            }
            event = parser.next();
        }
        return xml;
    }

    /**
     *
     * @return
     */
    private String genProductArgs( WeiXinOrderInfo weiXinOrderInfo) throws UnsupportedEncodingException {
        //String nonceStr = genNonceStr();
        //out_trade_no=genOutTradNo();

        TreeMap<String, String> packageParams = new TreeMap<>();

        packageParams.put("appid", validateParameter(weiXinPayInfo.getAppId(), 32));
        if (!TextUtils.isEmpty(weiXinOrderInfo.getAttach())) {
            packageParams.put("attach", validateParameter(weiXinOrderInfo.getAttach(), 127));
        }
        packageParams.put("body", validateParameter(weiXinOrderInfo.getBody(), 128));
        packageParams.put("mch_id", validateParameter(weiXinPayInfo.getPartnerId(), 32));
        packageParams.put("nonce_str", validateParameter(genNonceStr(), 32));
        packageParams.put("notify_url", validateParameter(weiXinPayInfo.getNotifyUrl(), 256));
        packageParams.put("out_trade_no", validateParameter(weiXinOrderInfo.getOrderNo(), 32));
        packageParams.put("spbill_create_ip", "192.168.1.8");
        packageParams.put("total_fee", String.valueOf(weiXinOrderInfo.getTotal_fee()));
        packageParams.put("trade_type", "APP");
        packageParams.put("sign", genPackageSign(packageParams));

        String xmlString = toXml(packageParams);
        xmlString = new String(xmlString.getBytes(), "ISO8859-1");
        return xmlString;
    }

    /**
     *
     * @param params
     * @return
     */
    private String toXml( TreeMap<String,String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (String key : params.keySet()){
            sb.append("<" + key + ">");
            sb.append(params.get(key));
            sb.append("</" + key + ">");
        }
        sb.append("</xml>");
        Log.i( TAG , sb.toString() );
        return sb.toString();
    }

    /**
     * 生成签名
     */
    private String genPackageSign( TreeMap<String,String> params){
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet() ){
            sb.append( key + "=" + params.get(key) + "&" );
        }
        sb.append("key=" + weiXinPayInfo.getAppSecret());

        String packageSign;
        packageSign = EncryptUtil.getInstance().encryptMd532( sb.toString()).toUpperCase();

        //String packageSign22 = getMessageDigest(sb.toString().getBytes()).toUpperCase( Locale.getDefault());

        Log.i( TAG , packageSign);

        return packageSign;
    }

    /**
     * 32位内的随机串，防重发
    */
    public static String genNonceStr(){
        String nonceStr = UUID.randomUUID().toString().replace("-","");
        nonceStr = nonceStr.length() > 32 ? nonceStr.substring(0,32):nonceStr;
        return nonceStr;
    }

    /**
     *
     * @param parameter
     * @param maxLen
     * @return
     */
    private String validateParameter(String parameter , int maxLen){
        if(TextUtils.isEmpty(parameter)) return parameter;
        if( parameter.length() > maxLen ){
            return parameter.substring(0, maxLen);
        }
        return parameter;
    }

//    public String getMessageDigest(byte[] buffer) {
//        char hexDigits[] =
//                { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
//                        'e', 'f' };
//        try{
//            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
//            mdTemp.update(buffer);
//            byte[] md = mdTemp.digest();
//            int j = md.length;
//            char str[] = new char[j * 2];
//            int k = 0;
//            for (int i = 0; i < j; i++)
//            {
//                byte byte0 = md[i];
//                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
//                str[k++] = hexDigits[byte0 & 0xf];
//            }
//            return new String(str);
//        } catch (Exception e)
//        {
//            return null;
//        }
//    }
}
