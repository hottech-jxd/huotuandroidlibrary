package com.huotu.android.library.libcommon.utils;

import android.text.format.DateUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/4/28.
 */
public class DateTimeUtils {
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat TIME_FORMAT_DATE    = new SimpleDateFormat("HH:mm:ss");

    private DateTimeUtils() {
        throw new AssertionError();
    }

    /**
     *
     *@方法描述：格式化获取的时间
     *@方法名：formatDate yyyy-MM-dd HH:mm:ss
     *@参数：@param currentTime
     *@参数：@return
     *@返回：String
     *@exception
     */
    public static String formatDate(long timeInMillis ){
        try{
            Date date = new Date(timeInMillis);
            return DEFAULT_DATE_FORMAT.format(date);
        } catch(Exception e){
            //发现异常时，返回当前时间
            Log.e( DateTimeUtils.class.getName() ,e.getMessage());
            return DEFAULT_DATE_FORMAT.format(new Date());
        }
    }

    public static String formatDate(long timeInMillis , String formatStr ){
        DateFormat format = null;
        try{
            format = new SimpleDateFormat(formatStr);
            Date date = new Date(timeInMillis);
            return format.format(date);
        } catch(Exception e){
            e.printStackTrace();
            //发现异常时，返回当前时间
            Log.e( DateTimeUtils.class.getName() ,e.getMessage());
            return format.format(new Date());
        }
    }

    /***
     * 返回时间
     * @param timeInMillis
     * @return
     */
    public static String formatTime(long timeInMillis){
        return TIME_FORMAT_DATE.format(new Date(timeInMillis));
    }
    /**
     * 获得当前时间
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }
    /**
     * 获得当前时间的字符串 {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return formatDate(getCurrentTimeInLong());
    }
    /**
     * 返回指定格式的当前时间
     *
     * @return
     */
    public static String getCurrentTimeInString( String dateFormat) {
        return formatDate(getCurrentTimeInLong(), dateFormat);
    }

    /***
     * 返回日期字符串的Long类型
     * @param date
     * @param formatStr
     * @return
     */
    public static long getDateInLong(String date , String formatStr ){
        DateFormat format;
        try{
            format = new SimpleDateFormat(formatStr);
            return format.parse(date).getTime();
        } catch (ParseException e){
            e.printStackTrace();
            Log.e(DateTimeUtils.class.getName(),e.getMessage());
            return 0L;
        }

    }

    /***
     * 判断当前时间是否是今天
     * @param timeInMillis
     * @return
     */
    public static boolean isToday(long timeInMillis){
        return DateUtils.isToday(timeInMillis);
    }
}
