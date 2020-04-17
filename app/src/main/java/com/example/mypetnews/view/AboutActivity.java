package com.example.mypetnews.view;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mypetnews.R;
import com.example.mypetnews.util.ThemeHelper;

public final class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ThemeHelper.applyTheme(this);
        setContentView(R.layout.activity_about);
        setupToolbar();

        TextView aboutText = (TextView) findViewById(R.id.aboutText);
        aboutText.setMovementMethod(LinkMovementMethod.getInstance());

        TextView feedbackText = (TextView) findViewById(R.id.feedbackText);
        feedbackText.setMovementMethod(LinkMovementMethod.getInstance());

        TextView contributingText = (TextView) findViewById(R.id.contributingText);
        contributingText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onResume() {
        super.onResume();
        //ThemeHelper.updateTheme(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.aboutTitle);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
