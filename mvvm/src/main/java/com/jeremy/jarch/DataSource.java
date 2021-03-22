package com.jeremy.jarch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListUtil;

import java.util.List;

public interface DataSource<T> {
    void getAll(@NonNull DataCallback<List<T>> callback);

    void get(@NonNull String id, @NonNull DataCallback<T> callback);

    void update(@NonNull T diary);

    void clear();

    void delete(@NonNull String id);
}
