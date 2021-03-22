package com.jeremy.jarch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncListUtil;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiariesLocalDataSource implements DataSource<Diary> {
    private static final String DIARY_DATA = "diary_data";
    private static final String ALL_DIARY = "all_diary";
    private static Map<String, Diary> LOCAL_DATA = new LinkedHashMap<>();
    private final SharedPreferencesUtils mSpUtils;
    private static volatile DiariesLocalDataSource sInstance;

    private DiariesLocalDataSource() {
        // 获取SharedPreferences,以管理本地缓存信息
        mSpUtils = SharedPreferencesUtils.getInstance(DIARY_DATA);
        String diaryStr = mSpUtils.get(ALL_DIARY);
        Map<String, Diary> diariesObj = json2Obj(diaryStr);
        if (diariesObj == null || diariesObj.isEmpty()) {
            LOCAL_DATA = MockDiaries.mock();
        } else {
            LOCAL_DATA = diariesObj;
        }
    }

    @Nullable
    private Map<String, Diary> json2Obj(final String diaryStr) {
        return GsonUtils.fromJson(diaryStr, new TypeToken<LinkedHashMap<String, Diary>>() {
        }.getType());
    }


    public static DiariesLocalDataSource get() {
        if (sInstance == null) {
            synchronized (DiariesLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new DiariesLocalDataSource();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void getAll(@NonNull DataCallback<List<Diary>> callback) {
        if (LOCAL_DATA.isEmpty()) {
            callback.onError();
        } else {
            callback.onSuccess(new ArrayList<>(LOCAL_DATA.values()));
        }
    }

    @Override
    public void get(@NonNull String id, @NonNull DataCallback<Diary> callback) {
        Diary diary = LOCAL_DATA.get(id);
        if (diary != null) {
            callback.onSuccess(diary);
        } else {
            callback.onError();
        }
    }

    @Override
    public void update(@NonNull Diary diary) {
        LOCAL_DATA.put(diary.getId(), diary);
        mSpUtils.put(ALL_DIARY, obj2Json());
    }

    @Override
    public void clear() {
        LOCAL_DATA.clear();
        mSpUtils.remove(ALL_DIARY);
    }

    @Override
    public void delete(@NonNull String id) {
        LOCAL_DATA.remove(id);
        mSpUtils.put(ALL_DIARY, obj2Json());
    }

    private String obj2Json() {
        return GsonUtils.toJson(LOCAL_DATA);
    }
}
