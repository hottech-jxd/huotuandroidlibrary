package com.huotu.android.library.libcommon.utils;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

public class GsonUtil<T>{
    private static Gson gson = null;

    public static Gson getGson() {
        if (null == gson) {
            gson = new GsonBuilder()
                    .registerTypeAdapter( Date.class , new GsonDateDeserializer())
                    .registerTypeAdapter( Date.class , new GsonDateDeserializer())
                    .setDateFormat(DateFormat.LONG)
                    .serializeNulls().create();
        }
        return gson;
    }

    public String toJson(T t){
        return getGson().toJson(t);
    }

    @SuppressWarnings("unchecked")
    public T toBean(String msg, T t){
        try {
            // 这里起初使用
            // Type type = TypeToken<T>() {}.getType());
            // return (T) gson.fromJson(msg,type);
            // 貌似java泛型不具有传递性。只能采用Class参数的方法。有大神可以解决Type的问题请留言告知。THX。
            return (T) getGson().fromJson(msg, t.getClass());
        }catch (Exception ex){
            ex.printStackTrace();
            Log.e( GsonUtil.class.getName() , ex.getMessage());
            return null;
        }
    }

    public static class GsonDateDeserializer implements JsonDeserializer<Date>,JsonSerializer<Date> {
        @Override
        public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return new Date( jsonElement.getAsJsonPrimitive().getAsLong() );
        }
        @Override
        public JsonElement serialize(Date date , Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive( date.getTime() );
        }
    }
}
