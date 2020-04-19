package com.example.mypetnews.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mypetnews.R;
import com.example.mypetnews.adapter.ItemClickListener;
import com.example.mypetnews.adapter.StoriesListAdapter;
import com.example.mypetnews.data.DataPreferences;
import com.example.mypetnews.model.Story;
import com.example.mypetnews.network.PathType;
import com.example.mypetnews.util.Constants;
import com.example.mypetnews.viewmodel.MainViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;


public final class MainActivity extends AppCompatActivity implements ItemClickListener {

    private List<Story> storiesList;
    private StoriesListAdapter adapter;

    private MainViewModel mainViewModel;

    private BottomSheetDialog bottomDialog;

    private String parameterName = PathType.TOP.getPathName();

    @BindView(R.id.tool_bar)
    protected Toolbar toolBar;

    @BindView(R.id.refresh_stories)
    protected SwipeRefreshLayout storiesRefresh;

    @BindView(R.id.recycler_stories)
    protected RecyclerView storiesRecycler;

    @BindView(R.id.fab_menu_dialog)
    protected FloatingActionButton menuDialogButton;

    @BindView(R.id.text_error)
    protected TextView errorText;

    @BindView(R.id.block_error)
    protected LinearLayout errorBlock;

    @BindView(R.id.button_connect)
    protected Button connectButton;

    @BindBool(R.bool.is_tablet)
    protected boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initToolbar();

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            storiesList = intent.getParcelableArrayListExtra(Constants.RESPONSE);
        } else {
            parameterName = savedInstanceState.getString(Constants.PARAMETER_REQUEST);
            storiesList = savedInstanceState.getParcelableArrayList(Constants.RESPONSE);
        }

        menuDialogButton.setOnClickListener(view -> {
            initBottomDialog();
        });

        connectButton.setOnClickListener(view -> {
            requestApi(parameterName);
        });

        if (storiesList == null) {
            storiesRefresh.setRefreshing(true);
            requestApi(parameterName);
        } else {
            updateToolbarTitle(parameterName);
            initAdapter(storiesList);
        }

        setupRefresh();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.PARAMETER_REQUEST, parameterName);
        outState.putParcelableArrayList(Constants.RESPONSE, (ArrayList<? extends Parcelable>) storiesList);
    }

    @Override
    public void onClick(int position) {

        if (isTablet) {
            initFragment(0);
        } else {
            DataPreferences.setRecipePreference(this, storiesList.get(position));

            Intent intent = new Intent(this, StoryActivity.class);
            intent.putExtra(Constants.ID, storiesList.get(position).getId());
            intent.putExtra(Constants.TITLE, storiesList.get(position).getDomain());
            startActivity(intent);
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(parameterName);
        }
    }

    private void updateToolbarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    private void initAdapter(List<Story> storiesList) {

        if (adapter == null) {
            adapter = new StoriesListAdapter(storiesList, this);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        storiesRecycler.setLayoutManager(layoutManager);
        storiesRecycler.setHasFixedSize(true);
        storiesRecycler.setAdapter(adapter);

        if (isTablet) {
            initFragment(0);
        }
    }

    private void initBottomDialog() {

        bottomDialog = new BottomSheetDialog(this);
        View viewDialog = getLayoutInflater().inflate(R.layout.bottom_dialog_parameter, null);

        viewDialog.findViewById(R.id.item_top).setOnClickListener(view -> {
            clickItemDialog(PathType.TOP.getPathName());
        });
        viewDialog.findViewById(R.id.item_new).setOnClickListener(view -> {
            clickItemDialog(PathType.NEW.getPathName());
        });
        viewDialog.findViewById(R.id.item_ask).setOnClickListener(view -> {
            clickItemDialog(PathType.ASK.getPathName());
        });
        viewDialog.findViewById(R.id.item_show).setOnClickListener(view -> {
            clickItemDialog(PathType.SHOW.getPathName());
        });
        viewDialog.findViewById(R.id.item_jobs).setOnClickListener(view -> {
            clickItemDialog(PathType.JOBS.getPathName());
        });

        bottomDialog.setContentView(viewDialog);
        bottomDialog.show();
    }

    private void clickItemDialog(String type) {
        storiesRefresh.setRefreshing(true);
        parameterName = type;
        requestApi(parameterName);
        bottomDialog.cancel();
    }

    private void setupRefresh() {
        storiesRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        storiesRefresh.setOnRefreshListener(() -> requestApi(parameterName));
    }

    private void requestApi(String type) {

        mainViewModel.getStoriesListLiveData(type).observe(this, response -> {

            if (response.getStories() != null) {
                storiesList = response.getStories();
                updateToolbarTitle(type);

                if (adapter == null) {
                    initAdapter(storiesList);
                } else {
                    adapter.updateData(storiesList);
                }

                storiesRecycler.setVisibility(View.VISIBLE);
                menuDialogButton.setVisibility(View.VISIBLE);
                errorBlock.setVisibility(View.GONE);
            } else {
                storiesRecycler.setVisibility(View.GONE);
                menuDialogButton.setVisibility(View.GONE);
                errorText.setText(response.getErrorType().getErrorName());
                errorBlock.setVisibility(View.VISIBLE);
            }
            storiesRefresh.setRefreshing(false);
        });
    }

    private void initFragment(int position) {

//        androidx.fragment.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        StepFragment stepFragment = new StepFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(Constants.STEPS, stepsList.get(position));
//        stepFragment.setArguments(bundle);
//        transaction.replace(R.id.fragment_container, stepFragment);
//        transaction.commit();
    }
}