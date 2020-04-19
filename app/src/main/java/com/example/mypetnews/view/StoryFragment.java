package com.example.mypetnews.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mypetnews.R;
import com.example.mypetnews.adapter.StoryViewPagerAdapter;
import com.example.mypetnews.model.Story;
import com.example.mypetnews.util.Constants;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class StoryFragment extends Fragment implements ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {

    private int storyId;
    private Story story;
    private String error;

    @BindView(R.id.tab_layout)
    protected TabLayout tabLayout;

    @BindView(R.id.view_pager)
    protected ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            storyId = bundle.getInt(Constants.ID);
            story = bundle.getParcelable(Constants.STORY);
            error = bundle.getString(Constants.ERROR);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_story, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        tabLayout.getTabAt(position).select();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    private void init() {

        if (story != null) {

            initTabLayout();
            initViewPager(createViewPageAdapter(getActivity().getSupportFragmentManager(), storyId, story, error));
        }
    }

    private StoryViewPagerAdapter createViewPageAdapter(FragmentManager fragmentManager, int storyId, Story story, String error) {
        return new StoryViewPagerAdapter(fragmentManager, storyId, story, error);
    }

    private void initTabLayout() {

        tabLayout.addTab(tabLayout.newTab().setText(R.string.news_title_text));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.comments_title_text));
        tabLayout.addOnTabSelectedListener(this);
    }

    private void initViewPager(PagerAdapter pagerAdapter) {

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }
}