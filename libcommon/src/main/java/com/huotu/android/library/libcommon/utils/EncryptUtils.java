package com.huotu.android.library.libcommon.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
    private static class Holder{
        private static final EncryptUtils instance = new EncryptUtils();
    }

    private EncryptUtils(){
    }

    public static final EncryptUtils getInstance(){
        return Holder.instance;
    }

    public String encryptMd532(String source){
        if (null == source || "".equals(source.trim())){
            return null;
        } else{
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(source.getBytes("utf-8"));
                byte[] s1 = messageDigest.digest();
                String tem = new String( HexUtil.encodeHex(s1) );
                Log.i("test>>>>>>>>", tem);
                return tem;
            }catch (UnsupportedEncodingException ex){
                return "";
            }catch (NoSuchAlgorithmException ex2){
                return "";
            }
        }
    }
}
