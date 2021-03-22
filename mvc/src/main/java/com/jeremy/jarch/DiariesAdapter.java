package com.jeremy.jarch;

import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DiariesAdapter extends RecyclerView.Adapter<DiariesHolder> {
    private static final String TAG = "DiariesAdapter";
    private List<Diary> mDiaries;// 日记数据
    private OnLongClickListener<Diary> mOnLongClickListener;// 长按事件监听器

    public DiariesAdapter(List<Diary> mDiaries) {
        this.mDiaries = mDiaries;
    }

    public void update(List<Diary> diaries) {
        mDiaries = diaries;
        notifyDataSetChanged();
    }

    public void setmOnLongClickListener(OnLongClickListener<Diary> mOnLongClickListener) {
        this.mOnLongClickListener = mOnLongClickListener;
    }

    public interface OnLongClickListener<T> {
        boolean onLongClick(View v, T data);
    }

    @NonNull
    @Override
    public DiariesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiariesHolder(parent); // 创建日记Holder
    }

    @Override
    public void onBindViewHolder(@NonNull DiariesHolder holder, int position) {
        Log.d(TAG, "position: " + position);
        Diary diary = mDiaries.get(position); // 根据位置获取日记数据
        holder.onBindView(diary);// holder绑定日记数据
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mOnLongClickListener != null && mOnLongClickListener.onLongClick(v, diary);
            }
        });
    }

    @Override
    public int getItemCount() {
        int size = mDiaries.size();
        Log.d(TAG, "SIZE: " + size);
        return size;// 获取列表条目总数
    }
}
