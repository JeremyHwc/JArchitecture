package com.jeremy.jarch;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiariesRepository implements DataSource<Diary> {
    private static volatile DiariesRepository sInstance;
    private final DataSource<Diary> mLocalDataSource;
    private final Map<String, Diary> mMemoryCache;

    private DiariesRepository() {
        mLocalDataSource = DiariesLocalDataSource.get();
        mMemoryCache = new LinkedHashMap<>();
    }

    public static DiariesRepository getInstance() {
        if (sInstance == null) {
            synchronized (DiariesRepository.class) {
                if (sInstance == null) {
                    sInstance = new DiariesRepository();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void getAll(@NonNull DataCallback<List<Diary>> callback) {
        if (!CollectionUtils.isEmpty(mMemoryCache)) {
            callback.onSuccess(new ArrayList<>(mMemoryCache.values()));
            return;
        }

        mLocalDataSource.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onError() {
                callback.onError();
            }

            @Override
            public void onSuccess(List<Diary> data) {
                updateMemoryCache(data);
                callback.onSuccess(new ArrayList<>(mMemoryCache.values()));
            }
        });
    }

    private void updateMemoryCache(List<Diary> diaryList) {
        mMemoryCache.clear();
        /*data.stream().peek(new Consumer<Diary>() {
            @Override
            public void accept(Diary diary) {
                mMemoryCache.put(diary.getId(),diary);
            }
        });*/
        for (Diary diary : diaryList) {
            mMemoryCache.put(diary.getId(), diary);
        }
    }

    @Override
    public void get(@NonNull String id, @NonNull DataCallback<Diary> callback) {
        Diary cacheDiary = getDiaryByIdFromMemory(id);
        if (cacheDiary != null) {
            callback.onSuccess(cacheDiary);
            return;
        }
        mLocalDataSource.get(id, new DataCallback<Diary>() {
            @Override
            public void onError() {
                callback.onError();
            }

            @Override
            public void onSuccess(Diary diary) {
                mMemoryCache.put(diary.getId(), diary);
                callback.onSuccess(diary);
            }
        });
    }

    private Diary getDiaryByIdFromMemory(@NonNull String id) {
        if (CollectionUtils.isEmpty(mMemoryCache)) {
            return null;
        }
        return mMemoryCache.get(id);
    }


    @Override
    public void update(@NonNull Diary diary) {
        mLocalDataSource.update(diary);
        mMemoryCache.put(diary.getId(), diary);
    }

    @Override
    public void clear() {
        mLocalDataSource.clear();
        mMemoryCache.clear();
    }

    @Override
    public void delete(@NonNull String id) {
        mLocalDataSource.delete(id);
        mMemoryCache.remove(id);
    }
}
