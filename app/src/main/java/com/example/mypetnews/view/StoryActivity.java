package com.example.mypetnews.view;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.mypetnews.R;
import com.example.mypetnews.model.Story;
import com.example.mypetnews.util.Constants;
import com.example.mypetnews.viewmodel.StoryViewModel;
import com.example.mypetnews.widget.AppWidget;

import butterknife.BindView;
import butterknife.ButterKnife;


public final class StoryActivity extends AppCompatActivity {

    private int storyId;
    private String storyTitle;

    private StoryViewModel storyViewModel;

    private NewsFragment isNewsFragment;

    @BindView(R.id.tool_bar)
    protected Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        ButterKnife.bind(this);
        storyViewModel = new ViewModelProvider(this).get(StoryViewModel.class);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            storyId = intent.getIntExtra(Constants.ID, 0);
            storyTitle = intent.getStringExtra(Constants.TITLE);
        } else {
            storyId = savedInstanceState.getInt(Constants.ID);
            storyTitle = savedInstanceState.getString(Constants.TITLE);
        }

        initToolbar();
        requestApi(storyId);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.ID, storyId);
        outState.putString(Constants.TITLE, storyTitle);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_widget, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_widget: {
                addWidget();
                break;
            }
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        boolean isBack = false;

        if (isNewsFragment != null) {

            isBack = isNewsFragment.handleBack();
        }

        if (!isBack) {
            super.onBackPressed();
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(storyTitle);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    private void setFragment(Story story, String error) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        StoryFragment stepFragment = new StoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ID, storyId);
        bundle.putParcelable(Constants.STORY, story);
        bundle.putString(Constants.ERROR, error);
        stepFragment.setArguments(bundle);
        transaction.replace(R.id.fragment_container, stepFragment);
        transaction.commit();
    }

    public void requestApi(int id) {

        storyViewModel.getStoriesListLiveData(id).observe(this, response -> {

            if (response.getStory() != null) {
                setFragment(response.getStory(), null);
            } else {
                setFragment(null, response.getErrorType().getErrorName());
            }
        });
    }

    public void setNewsFragment(NewsFragment newsFragment) {
        isNewsFragment = newsFragment;
    }

    private void addWidget() {

        Context context = getApplicationContext();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context, AppWidget.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_stories_list);

        Toast.makeText(this, getString(R.string.save_widget_text), Toast.LENGTH_LONG).show();
    }
}