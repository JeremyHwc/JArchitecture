package com.jeremy.jarch;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DiaryEditFragment extends Fragment implements DiariesEditContract.View {

    public static final String DIARY_ID = "diary_id";
    private DiariesEditContract.Presenter mPresenter;
    private TextView mTitle;
    private TextView mDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_diary_edit, container, false);
        mTitle = root.findViewById(R.id.edit_title);
        mDescription = root.findViewById(R.id.edit_description);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_done:
                mPresenter.saveDiary(mTitle.getText().toString(),mDescription.getText().toString());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_done,menu);
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(),getString(R.string.error),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setDescription(String description) {
        mDescription.setText(description);
    }

    @Override
    public void showDiariesList() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void setPresenter(DiariesEditContract.Presenter presenter) {
        mPresenter = presenter;
    }
}