package com.jeremy.jarch;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DiariesHolder extends RecyclerViewHolder<Diary> {

    private View.OnLongClickListener mOnLongClickListener;

    public DiariesHolder(ViewGroup parent) {
        super(parent, R.layout.list_diary_item);
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
    }

    @Override
    public void onBindView(Diary data) {
        super.onBindView(data);
        TextView title = itemView.findViewById(R.id.title);
        title.setText(data.getTitle());
        TextView desc = itemView.findViewById(R.id.desc);
        desc.setText(data.getDescription());
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mOnLongClickListener != null && mOnLongClickListener.onLongClick(v);
            }
        });
    }
}
