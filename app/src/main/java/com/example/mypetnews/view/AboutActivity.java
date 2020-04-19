package com.example.mypetnews.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mypetnews.R;
import com.example.mypetnews.BuildConfig;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class AboutActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    protected Toolbar toolBar;

    @BindView(R.id.text_version_name)
    protected TextView versionNameText;

    @BindView(R.id.text_version_code)
    protected TextView versionCodeText;

    @BindView(R.id.text_year)
    protected TextView yearText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);
        initToolbar();

        versionNameText.setText("Version name: " + BuildConfig.VERSION_NAME);
        versionCodeText.setText("Version code: " + BuildConfig.VERSION_CODE);
        yearText.setText(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
    }

    private void initToolbar() {
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.about_title_text);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }
}