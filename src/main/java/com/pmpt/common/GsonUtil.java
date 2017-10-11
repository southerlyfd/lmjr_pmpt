package com.pmpt.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonUtil {
    private static Gson gson;

    static {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public static <T> T jsonToObj(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T> List<T> jsonToList(String json, TypeToken typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    public static String objToJson(Object object) {
        return gson.toJson(object);
    }

}
