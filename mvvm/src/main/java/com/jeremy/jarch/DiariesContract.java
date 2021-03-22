package com.jeremy.jarch;

import androidx.annotation.NonNull;

public interface DiariesContract {
    interface Presenter extends BasePresenter {
        void loadDiaries();

        void addDiary();

        void updateDiary(@NonNull Diary diary);

        void onInputDialogClick(String desc);

        void onResult(int requestCode, int resultCode);
    }

    interface View extends BaseView<Presenter> {
        void gotoWriteDiary();

//        void showInputDialog(final String title, final String desc);
        void gotoUpdateDiary(String diaryId);

        void showSuccess();

        void showError();

        boolean isActive();

        void setListAdapter(DiariesAdapter mListAdapter);
    }
}
