package com.jeremy.jarch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder<T> extends RecyclerView.ViewHolder {
    private T mData; // ViewHolder需要的数据

    public RecyclerViewHolder(ViewGroup parent, @LayoutRes int resource) {
        // 在构造函数中加载布局文件
        super(LayoutInflater.from(parent.getContext()).inflate(resource, parent, false));
    }

    @CallSuper // 提示调用父类方法
    public void onBindView(T data) {
        mData = data;// 绑定数据
    }


    public T getData() {
        return mData;// 获得数据
    }

}
