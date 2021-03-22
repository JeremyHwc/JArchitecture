package com.jeremy.jarch;

import android.text.TextUtils;

public class DiaryEditPresenter implements DiariesEditContract.Presenter {
    private final DataSource<Diary> mDiariesRepository;
    private final String mDiaryId;
    private final DiariesEditContract.View mView;

    public DiaryEditPresenter(String diaryId, DiariesEditContract.View view) {
        mDiaryId = diaryId;
        mView = view;
        mDiariesRepository = DiariesRepository.getInstance();
    }

    @Override
    public void start() {
        requestDiary();
    }

    private void requestDiary() {
        if (isAddDiary()) {
            return;
        }
        mDiariesRepository.get(mDiaryId, new DataCallback<Diary>() {
            @Override
            public void onError() {
                if (!mView.isActive()) {
                    return;
                }
                mView.showError();
            }

            @Override
            public void onSuccess(Diary data) {
                if (!mView.isActive()){
                    return;
                }
                mView.setTitle(data.getTitle());
                mView.setDescription(data.getDescription());
            }
        });
    }

    private boolean isAddDiary() {
        return TextUtils.isEmpty(mDiaryId);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void saveDiary(String title, String desc) {
        if (isAddDiary()) {
            createDiary(title,desc);
        } else {
            updateDiary(title,desc,mDiaryId);
        }
    }

    private void updateDiary(String title, String desc, String mDiaryId) {
        Diary diary = new Diary(mDiaryId, title, desc);
        mDiariesRepository.update(diary);
        mView.showDiariesList();
    }

    private void createDiary(String title, String desc) {
        Diary diary = new Diary(title,desc);
        mDiariesRepository.update(diary);
        mView.showDiariesList();
    }
}
