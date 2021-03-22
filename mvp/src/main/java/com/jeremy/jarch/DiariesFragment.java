package com.jeremy.jarch;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DiariesFragment extends Fragment implements DiariesContract.View {
    private static final String TAG = "DiariesFragment";
    private DiariesContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        android.view.View root = inflater.inflate(R.layout.fragment_diaries, container, false);
        initDiariesList(root);
        setHasOptionsMenu(true);
        return root;
    }

    private void initDiariesList(android.view.View root) {
        mRecyclerView = root.findViewById(R.id.diaries_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_write, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                mPresenter.addDiary();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setListAdapter(DiariesAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showError() {
        showMessage(getString(R.string.error));
    }

    @Override
    public void showSuccess() {
        showMessage(getString(R.string.success));
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void gotoWriteDiary() {
        Intent intent = new Intent(getContext(), DiaryEditActivity.class);
        startActivity(intent);
    }

//    @Override
//    public void showInputDialog(String title, String description) {
//        final EditText editText = new EditText(getContext());
//        editText.setText(description);
//        new AlertDialog.Builder(getContext()).setTitle(title)
//                .setView(editText)
//                .setPositiveButton(EnApplication.get().getString(R.string.ok), (dialog, which) -> mPresenter.onInputDialogClick(editText.getText().toString()))
//                .setNegativeButton(EnApplication.get().getString(R.string.cancel), null)
//                .show();
//    }

    @Override
    public void gotoUpdateDiary(String diaryId) {
        Intent intent = new Intent(getContext(), DiaryEditActivity.class);
        intent.putExtra(DiaryEditFragment.DIARY_ID, diaryId);
        startActivity(intent);
    }

    public void addWriteDiary() {
        showMessage(getString(R.string.developing));
    }

    @Override
    public void setPresenter(DiariesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mPresenter.onResult(requestCode, resultCode);
    }
}