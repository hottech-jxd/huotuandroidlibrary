package com.huotu.android.library.libcommon.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * SharedPreferences操作工具包<br>
 * 
 * <b>说明</b> 本工具包只能在单进程项目下使用，多进程共享请使用如下demo的两行代码重写: <br>
 * Context otherContext = c.createPackageContext( "com.android.contacts",
 * Context.CONTEXT_IGNORE_SECURITY); <br>
 * SharedPreferences sp = otherContext.getSharedPreferences( "my_file",
 * Context.MODE_MULTI_PROCESS);<br>
 *
 * 
 * @author
 */
public class PreferenceUtils {
    public static void writeInt(Context context, String fileName, String k, int v) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.putInt(k, v);
        editor.commit();
    }

    public static void writeBoolean(Context context, String fileName, String k, boolean v) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.putBoolean(k, v);
        editor.commit();
    }

    public static void writeString(Context context, String fileName, String k, String v) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.putString(k, v);
        editor.commit();
    }

    public static void writeString(Context context, String fileName, Map<String, String> maps) {
        if (maps == null) return;
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = preference.edit();

        Iterator iter = maps.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = entry.getKey().toString();
            String val = entry.getValue() ==null ? "" : entry.getValue().toString();
            editor.putString(key, val);
        }

        editor.commit();
    }

    public static void writeLong(Context context, String fileName, String k, Long v) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.putLong(k, v);
        editor.commit();
    }

    public static int readInt(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preference.getInt(k, 0);
    }

    public static int readInt(Context context, String fileName, String k, int defv) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preference.getInt(k, defv);
    }

    public static boolean readBoolean(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preference.getBoolean(k, false);
    }

    public static boolean readBoolean(Context context, String fileName, String k, boolean defBool) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preference.getBoolean(k, defBool);
    }

    public static String readString(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preference.getString(k, null);
    }

    public static String readString(Context context, String fileName, String k, String defV) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preference.getString(k, defV);
    }

    public static Long readLong(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preference.getLong(k, 0L);
    }

    public static void remove(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.remove(k);
        editor.commit();
    }

    public static void clean(Context cxt, String fileName) {
        SharedPreferences preference = cxt.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = preference.edit();
        editor.clear();
        editor.commit();
    }
}
