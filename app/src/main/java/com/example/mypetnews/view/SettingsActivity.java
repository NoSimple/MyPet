package com.example.mypetnews.view;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mypetnews.R;
import com.example.mypetnews.util.ThemeHelper;

public final class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ThemeHelper.applyTheme(this);
        setContentView(R.layout.activity_settings);

        setupToolbar();

        getFragmentManager().beginTransaction()
                .replace(R.id.settingsContent, new SettingsFragment())
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
       // ThemeHelper.updateTheme(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.settingsTitle);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}