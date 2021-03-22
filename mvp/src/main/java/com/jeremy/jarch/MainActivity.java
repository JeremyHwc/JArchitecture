package com.jeremy.jarch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initFragment();
    }

    private void initFragment() {
        DiariesFragment diariesFragment = getDiariesFragment();
        if (diariesFragment == null) {
            diariesFragment = new DiariesFragment();
            // 将日记Fragment添加到Activity中
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), diariesFragment, R.id.content);
        }
        diariesFragment.setPresenter(new DiariesPresenter(diariesFragment));
    }

    private DiariesFragment getDiariesFragment() {
        return (DiariesFragment) getSupportFragmentManager().findFragmentById(R.id.content);
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}