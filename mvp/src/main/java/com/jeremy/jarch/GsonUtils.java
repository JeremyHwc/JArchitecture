package com.jeremy.jarch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class GsonUtils {
    private static final Gson GSON = createGson();

    private static Gson createGson() {
        final GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        return builder.create();
    }

    public static String toJson(final Object object) {
        return GSON.toJson(object);
    }

    public static <T> T fromJson(final String json, final Type type) {
        return GSON.fromJson(json, type);
    }
}
