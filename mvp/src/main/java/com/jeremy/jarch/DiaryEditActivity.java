package com.jeremy.jarch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.widget.Toolbar;

public class DiaryEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_edit);
        String diaryId = getIntent().getStringExtra(DiaryEditFragment.DIARY_ID);
        initToolbar(diaryId);
        initFragment(diaryId);
    }

    private void initFragment(String diaryId) {
        DiaryEditFragment addEditDiaryFragment = getEditDiaryFragment();
        if (addEditDiaryFragment == null) {
            addEditDiaryFragment = initEditDiaryFragment(diaryId);
        }
        DiaryEditPresenter diaryEditPresenter = new DiaryEditPresenter(diaryId, addEditDiaryFragment);
        addEditDiaryFragment.setPresenter(diaryEditPresenter);
    }

    private DiaryEditFragment initEditDiaryFragment(String diaryId) {
        DiaryEditFragment addEditDiaryFragment = new DiaryEditFragment();
        if (getIntent().hasExtra(DiaryEditFragment.DIARY_ID)) {
            Bundle bundle = new Bundle();
            bundle.putString(DiaryEditFragment.DIARY_ID, diaryId);
            addEditDiaryFragment.setArguments(bundle);
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addEditDiaryFragment, R.id.content);
        return addEditDiaryFragment;
    }

    private DiaryEditFragment getEditDiaryFragment() {
        return (DiaryEditFragment) getSupportFragmentManager().findFragmentById(R.id.content);
    }

    private void initToolbar(String diaryId) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbarTitle(TextUtils.isEmpty(diaryId));

    }

    private void setToolbarTitle(boolean isAdd) {
        if (isAdd) {
            getSupportActionBar().setTitle(R.string.add);
        } else {
            getSupportActionBar().setTitle(R.string.edit);
        }
    }
}