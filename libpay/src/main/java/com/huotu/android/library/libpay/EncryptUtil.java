package com.huotu.android.library.libpay;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
    private static class Holder{
        private static final EncryptUtil instance = new EncryptUtil();
    }

    private EncryptUtil(){
    }

    public static final EncryptUtil getInstance(){
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
                //String tem = new String( Hex.encodeHex(s1) );
                String tem = new String( encodeHex(s1) );
                //Log.i("test>>>>>>>>", tem);
                return tem;
            }catch (UnsupportedEncodingException ex){
                return "";
            }catch (NoSuchAlgorithmException ex2){
                return "";
            }
        }
    }


    protected static char[] encodeHex(final byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return out;
    }

    /**
     * Used to build output as Hex
     */
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd','e', 'f'};


}
