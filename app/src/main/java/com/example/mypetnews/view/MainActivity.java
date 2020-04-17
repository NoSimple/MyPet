package com.example.mypetnews.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.example.mypetnews.R;
import com.example.mypetnews.util.ThemeHelper;


public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ThemeHelper.applyTheme(this);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = setupToolbar();

        /*getFragmentManager().addOnBackStackChangedListener(this);

        mCurrentItem = FrontPage;

        if (savedInstanceState != null) {
            mCurrentItem = HexDrawer.Item.valueOf(savedInstanceState.getString(ITEM_KEY));
        }

        mDrawer = new HexDrawer(this, toolbar, mCurrentItem, this);
        mDrawer.build();

        Bundle bundle = new Bundle();
        bundle.putString(StoryListFragment.CollectionKey, itemToCollection.get(mCurrentItem).toString());
        StoryListFragment storyListFragment = new StoryListFragment();
        storyListFragment.setArguments(bundle);*/

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //transaction.replace(R.id.content_wrapper, storyListFragment);

        transaction.commit();

       // updateDrawer();
        //updateToolbarTitle();
    }

    @Override
    public void onResume() {
        super.onResume();
        //ThemeHelper.updateTheme(this);
        //mDrawer.selectItem(mCurrentItem);
    }

    /*@Override
    public void onItemSelectedHandler(HexDrawer.Item item) {
        String collection;

        switch (item) {
            case Settings:
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingsIntent);
                break;
            case About:
                Intent aboutIntent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(aboutIntent);
                break;
            default:
                mCurrentItem = item;

                collection = itemToCollection.get(item).toString();
                Bundle bundle = new Bundle();
                bundle.putString(StoryListFragment.CollectionKey, collection);
                StoryListFragment storyListFragment = new StoryListFragment();
                storyListFragment.setArguments(bundle);

                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStackImmediate();
                }

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_wrapper, storyListFragment);
                transaction.addToBackStack(item.toString());

                transaction.commit();

                break;
        }
    }

    private Toolbar setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.frontPageTitle);
        }

        return toolbar;
    }

    private void updateToolbarTitle() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(itemToToolbarTitle.get(mCurrentItem));
        }
    }

    private void updateDrawer() {
        mDrawer.selectItem(mCurrentItem);
    }

    @Override
    public void onBackStackChanged() {

        if (getFragmentManager().getBackStackEntryCount() < 1) {
            mCurrentItem = FrontPage;
        } else {
            FragmentManager.BackStackEntry bse = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1);
            mCurrentItem = HexDrawer.Item.valueOf(bse.getName());
        }

        updateToolbarTitle();
        updateDrawer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(ITEM_KEY, mCurrentItem.toString());
    }*/
}