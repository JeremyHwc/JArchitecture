package com.jeremy.jarch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DiariesController implements Observer<Diary>{

    private final DiariesRepository mDiariesRepository;
    private final Fragment mView;
    private DiariesAdapter mListAdapter;

    public DiariesController(@NonNull Fragment fragment) {
        // 获取数据仓库的实例
        mDiariesRepository = DiariesRepository.getInstance();
        // 将页面对象传入，赋值给日记的成员变量
        mView = fragment;
        // 开启页面的菜单功能
        mView.setHasOptionsMenu(true);
        // 创建日记列表的适配器，Android中的Adapter主要用来填充View的数据，在MVC模式中，我们也可以将Adapter作为一种特殊的Controller来李产销
        initAdapter();
    }

    private void initAdapter() {
        // 创建日记列表的适配器
        mListAdapter = new DiariesAdapter(new ArrayList<>());
        mListAdapter.setmOnLongClickListener(new DiariesAdapter.OnLongClickListener<Diary>() {
            @Override
            public boolean onLongClick(View v, Diary data) {
                showInputDialog(data); // 弹出输入对话框
                return false;
            }
        });
    }

    private void showInputDialog(Diary data) {
        final EditText editText = new EditText(mView.getContext());
        editText.setText(data.getDescription());
        new AlertDialog.Builder(mView.getContext()).setTitle(data.getTitle())
                .setView(editText)
                .setPositiveButton(EnApplication.get().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.setDescription(editText.getText().toString());
                        mDiariesRepository.update(data);
                        loadDiaries();
                    }
                }).setNegativeButton(EnApplication.get().getString(R.string.cancel), null)
                .show();
    }

    public void setDiariesList(RecyclerView diariesRv) {
        diariesRv.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        diariesRv.setAdapter(mListAdapter);
        diariesRv.addItemDecoration(new DividerItemDecoration(mView.getContext(), DividerItemDecoration.VERTICAL));
        diariesRv.setItemAnimator(new DefaultItemAnimator());
    }

    public void loadDiaries() {
        mDiariesRepository.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onError() {
                showError();
            }

            @Override
            public void onSuccess(List<Diary> diaryList) {
                processDiaries(diaryList);
            }
        });
    }

    private void showError() {
        showMessage(mView.getString(R.string.error));
    }

    private void showMessage(String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void processDiaries(List<Diary> diaryList) {

        mListAdapter.update(diaryList);
    }

    public void gotoWriteDiary() {
        showMessage(mView.getString(R.string.developing));
    }

    @Override
    public void update(Diary diary) {
        mDiariesRepository.update(diary);
        loadDiaries();
    }
}
