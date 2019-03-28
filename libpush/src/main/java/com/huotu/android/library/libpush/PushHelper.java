package com.huotu.android.library.libpush;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Administrator on 2016/4/21.
 */
public class PushHelper {
    private static String TAG = PushHelper.class.getName();
    private static String BaseUrl;
    private static Context Context;

    public static void init(Context context ){
        init(context,false,"");
    }

    public static  void init(Context context , boolean isDebug , String baseUrl ){
        // 设置开启日志,发布时请关闭日志
        if(isDebug){
            JPushInterface.setDebugMode(true);
        }
        // 初始化 JPush
        JPushInterface.init(context);

        BaseUrl = baseUrl;
        Context = context;
    }

    public static void bindingUserId(String userId , String userKey, String userRandom,String userSecure){
        bindingUserId(userId, "", userKey, userRandom,userSecure);
    }

    public static void bindingUserId(final String userId , final String alias , final String userKey , final String userRandom , final String userSecure) {
        //setJPushAlias(userId , alias , userKey , userRandom, userSecure);
        setJPushAlias(alias, new OnJPushCallback() {
            @Override
            public void callback() {
                asyncBindingUser( userId , alias , userKey ,userRandom , userSecure );
            }
        });
    }

    protected static void asyncBindingUser( final String userId , final String alias , final String userKey , final String userRandom , final String userSecure ){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty( BaseUrl ))return;
                String urlstr = String.format("%suserBinding/bindingTokenOrAlias/%s?alias=%s", BaseUrl ,userId , alias);
                httpPut( urlstr , userKey , userRandom ,userSecure );
            }
        }).start();
    }


//    private static void setJPushAlias(final String userId , String alias , final String userKey, final String userRandom, final String userSecure ){
//        JPushInterface.setAlias(Context, alias, new TagAliasCallback() {
//            @Override
//            public void gotResult(int code, String alias , Set<String> set) {
//                String logs ;
//                switch (code) {
//                    case 0:
//                        logs = "设置别名成功";
//                        Log.i(TAG, logs);
//
//                        asyncBindingUser(userId , alias,userKey,userRandom,userSecure);
//
//                        break;
//                    case 6002:
//                        logs = "设置别名超时！";
//                        Log.i(TAG, logs);
//                        //if (ExampleUtil.isConnected(getApplicationContext())) {
//                        //    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
//                        //} else {
//                        //    Log.i(TAG, "No network");
//                        //}
//                        EventBus.getDefault().post( new SetAliasEvent(SetAliasEvent.SET_ALIAS_FAIL, logs));
//                        break;
//                    default:
//                        logs = "设置别名失败，错误码 = " + code;
//                        Log.e(TAG, logs);
//                        EventBus.getDefault().post(new SetAliasEvent(SetAliasEvent.SET_ALIAS_FAIL, logs));
//                }
//
//            }
//        });
//    }


    private static void setJPushAlias( String alias , final OnJPushCallback onJPushCallback){
        JPushInterface.setAlias(Context, alias, new TagAliasCallback() {
            @Override
            public void gotResult(int code, String alias , Set<String> set) {
                String logs ;
                switch (code) {
                    case 0:
                        logs = "设置别名成功";
                        Log.i(TAG, logs);

                        onJPushCallback.callback();

                        break;
                    case 6002:
                        logs = "设置别名超时！";
                        Log.i(TAG, logs);
                        EventBus.getDefault().post( new SetAliasEvent(SetAliasEvent.SET_ALIAS_FAIL, logs));
                        break;
                    default:
                        logs = "设置别名失败，错误码 = " + code;
                        Log.e(TAG, logs);
                        EventBus.getDefault().post(new SetAliasEvent(SetAliasEvent.SET_ALIAS_FAIL, logs));
                }
            }
        });
    }

        /**
         * 绑定用户Id
         * @param userId
         * @param alias
         */
//    private static void asyncBindingUserId(String userId , String alias , String userKey , String userRandom , String userSecure){
//        if(TextUtils.isEmpty(alias)){
//            throw  new IllegalArgumentException("alias参数空");
//        }

//        HttpURLConnection httpURLConnection=null;
//        String urlstr = String.format("%suserBinding/bindingTokenOrAlias/%s?alias=%s", BaseUrl ,userId , alias);
//        httpPut( urlstr , userKey , userRandom ,userSecure );

//        try {
//            URL uri = new URL(urlstr);
//            httpURLConnection = (HttpURLConnection) uri.openConnection();
//            httpURLConnection.setConnectTimeout(15*1000);
//            httpURLConnection.setRequestProperty("_user_key",userKey);
//            httpURLConnection.setRequestProperty("_user_random",userRandom);
//            httpURLConnection.setRequestProperty("_user_secure",userSecure);
//            httpURLConnection.setDoInput(true);
//            httpURLConnection.setDoOutput(true);
//            httpURLConnection.setUseCaches(false);
//            httpURLConnection.setRequestMethod("PUT");
//            httpURLConnection.setRequestProperty("Charset","UTF-8");
//            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//
//            int statusCode = httpURLConnection.getResponseCode();
//            if( statusCode == 200 || statusCode==202 ){
//                EventBus.getDefault().post(new SetAliasEvent( SetAliasEvent.SET_ALIAS_SUCCESS ,"设置别名成功"));
//                return;
//            }
//        }catch(MalformedURLException ex){
//            ex.printStackTrace();
//            EventBus.getDefault().post(new SetAliasEvent(SetAliasEvent.SET_ALIAS_FAIL,"设置别名失败"));
//        }catch (IOException ex2){
//            ex2.printStackTrace();
//            EventBus.getDefault().post(new SetAliasEvent(SetAliasEvent.SET_ALIAS_FAIL,"设置别名失败"));
//        }finally {
//            if(null != httpURLConnection){
//                httpURLConnection.disconnect();
//            }
//        }
//    }

    /**
     * 绑定 设备号
     * @param customerId
     * @param alias
     */
    public static void bindingTokenOrAlias(final String customerId, final String alias , final String userKey, final String userRandom, final String userSecure ){
        setJPushAlias( alias , new OnJPushCallback(){
            @Override
            public void callback() {
                asyncBindingDevice( customerId , alias , userKey , userRandom ,userSecure );
            }
        });
    }

    private static void asyncBindingDevice(final String customerId , final String alias ,final String userKey, final String userRandom, final String userSecure){
        new Thread(new Runnable() {
            @Override
            public void run() {

                String url = String.format("%sdeviceBinding/bindingTokenOrAlias/%s?alias=%s", BaseUrl, customerId, alias);
                httpPut(url, userKey, userRandom, userSecure);
            }
        }).start();
    }

    private static void httpPut( String urlStr , String userKey, String userRandom,String userSecure){
        HttpURLConnection httpURLConnection=null;
        //String urlstr = String.format("%suserBinding/bindingTokenOrAlias/%s?alias=%s", BaseUrl ,userId , alias);
        try {
            URL uri = new URL(urlStr);
            httpURLConnection = (HttpURLConnection) uri.openConnection();
            httpURLConnection.setConnectTimeout(15*1000);
            httpURLConnection.setRequestProperty("_user_key",userKey);
            httpURLConnection.setRequestProperty("_user_random",userRandom);
            httpURLConnection.setRequestProperty("_user_secure",userSecure);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setRequestProperty("Charset","UTF-8");
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            int statusCode = httpURLConnection.getResponseCode();
            if( statusCode == 200 || statusCode==202 ){
                EventBus.getDefault().post(new SetAliasEvent( SetAliasEvent.SET_ALIAS_SUCCESS ,"设置别名成功"));
                return;
            }
        }catch(MalformedURLException ex){
            ex.printStackTrace();
            EventBus.getDefault().post(new SetAliasEvent(SetAliasEvent.SET_ALIAS_FAIL,"设置别名失败"));
        }catch (IOException ex2){
            ex2.printStackTrace();
            EventBus.getDefault().post(new SetAliasEvent(SetAliasEvent.SET_ALIAS_FAIL,"设置别名失败"));
        }finally {
            if(null != httpURLConnection){
                httpURLConnection.disconnect();
            }
        }
    }

    interface OnJPushCallback{
        void callback();
    }
}
