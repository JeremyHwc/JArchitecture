package com.jeremy.jarch;

import android.app.Activity;

import java.util.List;

public class DiariesPresenter implements DiariesContract.Presenter {
    private final DiariesContract.View mView;
    private final DiariesRepository mDiariesRepository;
    private DiariesAdapter mListAdapter;
    private Diary mDiary;

    public DiariesPresenter(DiariesContract.View view) {
        this.mView = view;
        // 获取数据仓库的实例
        mDiariesRepository = DiariesRepository.getInstance();
    }

    @Override
    public void start() {
        initAdapter();
        loadDiaries();
    }

    private void initAdapter() {
        mListAdapter = new DiariesAdapter();
        mListAdapter.setmOnLongClickListener(new DiariesAdapter.OnLongClickListener<Diary>() {
            @Override
            public boolean onLongClick(android.view.View v, Diary data) {
                updateDiary(data);
                return false;
            }
        });
        mView.setListAdapter(mListAdapter);
    }

    @Override
    public void updateDiary(Diary diary) {
        mDiary = diary;
        mView.gotoUpdateDiary(diary.getId());
    }

    @Override
    public void loadDiaries() {
        mDiariesRepository.getAll(new DataCallback<List<Diary>>() {
            @Override
            public void onError() {
                if (!mView.isActive()) {
                    return;
                }
                mView.showError();
            }

            @Override
            public void onSuccess(List<Diary> diaryList) {
                if (!mView.isActive()) {
                    return;
                }
                updateDiaries(diaryList);
            }
        });
    }

    private void updateDiaries(List<Diary> diaryList) {
        mListAdapter.update(diaryList);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onInputDialogClick(String desc) {
        mDiary.setDescription(desc);
        mDiariesRepository.update(mDiary);
        loadDiaries();// 重新加载列表
    }

    @Override
    public void onResult(int requestCode, int resultCode) {
        if (resultCode == Activity.RESULT_OK) {
            return;
        }
        mView.showSuccess();
    }

    @Override
    public void addDiary() {
        mView.gotoWriteDiary();
    }
}
